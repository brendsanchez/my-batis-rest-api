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
            "SELECT id, nombre, apellido, email, fechaNacimiento " +
            "FROM Usuario " +
            "<if test='id != null'> WHERE id = #{id, jdbcType=VARCHAR} </if>" +
            "<if test='email != null'> WHERE email = #{email, jdbcType=VARCHAR} </if>" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "nombre", property = "name"),
            @Result(column = "apellido", property = "surname"),
            @Result(column = "email", property = "email"),
            @Result(column = "fechaNacimiento", property = "date")
    })
    List<User> getUsers(User user);


    @Insert("INSERT INTO Usuario " +
            "(nombre, " +
            " apellido, " +
            " email, " +
            " fechaNacimiento) " +
            "VALUES (" +
            "#{name, jdbcType=VARCHAR}, " +
            "#{surname, jdbcType=VARCHAR}, " +
            "#{email, jdbcType=VARCHAR}, " +
            "#{date, jdbcType=DATE} " +
            " ) ")
    @Options(timeout = 10)
    int insertUser(UserInsertRequest request);

    @Update("<script>" +
            "UPDATE Usuario " +
            "<trim prefix='SET' suffixOverrides=','> " +
            "   <if test='name != null'> nombre = #{name} , </if>" +
            "   <if test='surname != null'> apellido = #{surname} , </if>" +
            "   <if test='email != null'> email = #{email} , </if>" +
            "   <if test='date != null'> fechaNacimiento = #{date} , </if>" +
            "</trim>" +
            "WHERE id = #{id}" +
            "</script>")
    @Options(timeout = 10)
    int updateUser(UserUpdateRequest request);

    @Delete("DELETE FROM Usuario " +
            "WHERE id = #{userId, jdbcType=VARCHAR}")
    int deleteUser(String userId);
}
