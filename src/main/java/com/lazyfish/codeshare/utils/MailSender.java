package com.lazyfish.codeshare.utils;

public interface MailSender {
    void sendMail(String alias,String address, String subject,String body);
}
