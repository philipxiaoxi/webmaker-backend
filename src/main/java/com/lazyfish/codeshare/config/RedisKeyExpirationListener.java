package com.lazyfish.codeshare.config;

import com.lazyfish.codeshare.service.DockerClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Resource
    DockerClientService dockerClientService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
    /**
     * redis key失效，监听
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 业务处理 , 注意message.toString()可以获取失效的key
        String id = (String) redisTemplate.opsForValue().get(message.toString() + "_bak");
        dockerClientService.stopAndRmContainer(id);
        redisTemplate.delete(message.toString() + "_bak");
        redisTemplate.delete(message.toString() + "_time");
        log.info(id + "失效,开始执行清除。");
    }
}
