package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserDao;
import com.lazyfish.codeshare.entity.User;
import com.lazyfish.codeshare.templetes.HtmlBody;
import com.lazyfish.codeshare.utils.MailSender;
import com.lazyfish.codeshare.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private MailSender mailSender;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    final static private String SALT = "1145141919810233";
    @Override
    public int getTotal() {
        return userDao.getTotal();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUserByLogin(String phone, String password) {
        password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        System.out.println(password);
        return userDao.getUserByLogin(phone, password);
    }

    @Override
    public int updateUser(User user) throws Exception {
        try {
            int id = userDao.updateUser(user);
            return id;
        }catch (Exception e) {
            throw new Exception("修改失败，可能手机号和邮箱已存在。");
        }
    }

    @Override
    public int insertUser(String phone, String password, String email, String code) throws Exception {
        String RealCode = (String) redisTemplate.opsForValue().get(email);
        if( RealCode==null || !RealCode.equals(code)) {
            throw new Exception("验证码不正确。");
        }
        redisTemplate.delete(email);
        password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        return userDao.insertUser(phone, password, email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void getRandomString(int len,String email,String ip) throws Exception {
        String code = StringUtils.getRandomString(4);
        if(redisTemplate.opsForValue().get(ip) != null) {
            throw new Exception("短时间大量发送邮件。");
        }
        redisTemplate.opsForValue().set(ip, code, 60, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(email, code, 60, TimeUnit.SECONDS);
        mailSender.sendMail(
                "CodeShare",
                email,
                "CodeShare注册验证邮件",
                HtmlBody.getCodeHtmlBody(code));
    }
}
