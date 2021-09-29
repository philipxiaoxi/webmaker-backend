package com.lazyfish.codeshare.service;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DockerClientService  {
    private DockerClient dockerClient;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    public DockerClientService() {
        log.info("docker服务已就绪，准备连接……");
        this.connectDocker();
    }
    public DockerClient connectDocker() {
        //使用DockerClientBuilder创建链接
        try {
            dockerClient = DockerClientBuilder.getInstance("tcp://192.168.217.134:2233").build();
            Info info = dockerClient.infoCmd().exec();
            log.info("docker的环境信息如下：=================");
            log.info(String.valueOf(info));
        }catch (Exception e) {
            log.info("docker服务连接失败，将无法提供容器服务。");
        }
        return dockerClient;
    }
    public void stopAndRmContainer(String containerId){
        dockerClient.stopContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();
    }
    public Object createContainer(String name,String codeId,String passWord,String port) throws Exception {
        HashMap hashMap = new HashMap();
        try {
            //创建容器
            CreateContainerResponse container1 = dockerClient.createContainerCmd("codexiaoxi/code-server")
                    .withName(name) //给容器命名
                    .withPortBindings(PortBinding.parse(port + ":8080"))
                    .withBinds(Bind.parse("/home/xiaoxi/codeshare_res/" + codeId + ":/home/coder")) //目录挂载
                    .withEnv("PASSWORD=" + passWord)
                    .withUser("root")
                    .exec();
            redisTemplate.opsForValue().set("docker" + codeId,container1.getId(),60*30, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set("docker"+ codeId+"_bak",container1.getId());
            redisTemplate.opsForValue().set("docker"+ codeId+"_time",new Date().getTime());
            hashMap.put("containerId",container1.getId());
            hashMap.put("port",port);
            //运行容器
            dockerClient.startContainerCmd(container1.getId()).exec();
        }catch (Exception e) {
            throw new Exception("容器创建失败，您可能有一个正在运行的容器正在使用。");
        }
        return hashMap;
    }
}
