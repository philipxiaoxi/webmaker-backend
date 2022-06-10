package com.lazyfish.codeshare.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 具体请查看相关服务提供商的配置方式
 * 您还可以实现MailSender使用其他的邮件发送服务
 */
public class AliMailSenderTemplete implements MailSender{
    @Override
    public void sendMail(String alias, String address, String subject, String body) {
        IClientProfile profile = DefaultProfile.getProfile("区域", "KeyId", "密钥");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("发信名称");
            request.setFromAlias(alias);//发信人昵称，长度小于15个字符。
            request.setAddressType(1);//0：为随机账号 1：为发信地址
            request.setReplyToAddress(true);// 是否启用管理控制台中配置好回信地址（状态须验证通过），取值范围是字符串true或者false
            request.setToAddress(address);
            request.setSubject(subject);
            request.setHtmlBody(body);
            request.setMethod(MethodType.POST);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
        catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
    }
}
