package com.lazyfish.codeshare.dao;


import com.lazyfish.codeshare.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
@Primary
public interface UserDao {
    @Select("SELECT COUNT(*) FROM user")
    int getTotal();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);

    @Select("SELECT id,name,sex,email,phone,type,grade,sign,identity FROM user ")
    List<User> getAllUser();

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User getUserByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("SELECT * FROM user WHERE phone = #{phone} AND pwd=#{pwd}")
    User getUserByLogin(@Param("phone") String phone, @Param("pwd") String pwd);

    @Update("UPDATE user SET name=#{name},sex=#{sex},email=#{email},sign=#{sign},phone=#{phone} WHERE id= #{id}")
    int updateUser(User user);

    @Insert("INSERT INTO `user` (`email`, `phone`, `pwd`) VALUES (#{email}, #{phone}, #{pwd})")
    int insertUser(@Param("phone") String phone, @Param("pwd") String pwd, @Param("email") String email);
}
