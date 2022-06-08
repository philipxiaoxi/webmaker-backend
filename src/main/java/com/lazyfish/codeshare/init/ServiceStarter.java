package com.lazyfish.codeshare.init;

import com.lazyfish.codeshare.service.DockerClientService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceStarter implements ApplicationRunner {
    @Resource
    private DockerClientService dockerClientService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // dockerClientService.init();
    }
}
