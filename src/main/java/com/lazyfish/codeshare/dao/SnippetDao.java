package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.SnippetList;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
@Primary
public interface SnippetDao {

    @Select("SELECT COUNT(*) FROM snippet")
    int getTotal();

    @Select("SELECT * FROM snippet WHERE id = #{id}")
    Snippet getSnippet(@Param("id")int id);
    @Select("SELECT img FROM snippet WHERE id = #{id}")
    Snippet getSnippetImg(@Param("id")int id);

    @Select("select `user`.name,`user`.identity,`user`.email,snippet.id,snippet.type,snippet.title from user,snippet where `user`.id=snippet.userid AND snippet.userid=#{userid} order by id desc")
    List<SnippetList>  getSnippetByUserid(int userid);

    @Select("select `user`.name,`user`.identity,`user`.email,snippet.id,snippet.type,snippet.title from user,snippet where `user`.id=snippet.userid order by id desc")
    List<SnippetList>  getAllSnippet();

    @Select("select `user`.name,`user`.identity,`user`.email,snippet.id,snippet.type,snippet.title from user,snippet where `user`.id=snippet.userid AND snippet.title like \"%\"#{title}\"%\" order by id desc")
    List<SnippetList>  getSearchSnippet(String title);

    @Insert("INSERT INTO snippet (userid, title ,type, content) VALUES (#{userid}, #{title}, #{type}, #{content})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertSnippet(Snippet snippet);

    @Update("UPDATE snippet SET content=#{content},title=#{title},note=#{note},img=#{img} WHERE id= #{id}")
    int updateSnippet(Snippet snippet);
}