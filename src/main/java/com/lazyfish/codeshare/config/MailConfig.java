package com.lazyfish.codeshare.config;


import com.lazyfish.codeshare.utils.AliMailSender;
import com.lazyfish.codeshare.utils.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    @Bean
    public MailSender mailSender () {
        return  new AliMailSender();
    }
}
