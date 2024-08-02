package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.model.User;
import com.gl.mybatisapirest.request.UserInsertRequest;
import com.gl.mybatisapirest.request.UserUpdateRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("<script>" +
            "SELECT id, name, surname, email, birthday " +
            "FROM users " +
            "<if test='id != null'> WHERE id = #{id} </if>" +
            "<if test='email != null'> WHERE email = #{email} </if>" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "surname", property = "surname"),
            @Result(column = "email", property = "email"),
            @Result(column = "birthday", property = "birthday")
    })
    List<User> getUsers(User user);


    @Insert("INSERT INTO users " +
            "(name, " +
            " surname, " +
            " email, " +
            " birthday) " +
            "VALUES (" +
            "#{name}, " +
            "#{surname}, " +
            "#{email}, " +
            "#{date} " +
            " ) ")
    @Options(timeout = 10)
    int insertUser(UserInsertRequest request);

    @Update("<script>" +
            "UPDATE users " +
            "<trim prefix='SET' suffixOverrides=','> " +
            "   <if test='name != null'> name = #{name} , </if>" +
            "   <if test='surname != null'> surname = #{surname} , </if>" +
            "   <if test='email != null'> email = #{email} , </if>" +
            "   <if test='date != null'> birthday = #{date} , </if>" +
            "</trim>" +
            "WHERE id = #{id}" +
            "</script>")
    @Options(timeout = 10)
    int updateUser(UserUpdateRequest request);

    @Delete("DELETE FROM users " +
            "WHERE id = #{userId}")
    @Options(timeout = 10)
    int deleteUser(Integer userId);
}
