package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo.entity.User;

@Mapper
public interface UserMapper {

	@Select("SELECT count(*) FROM user")
	int count();
	
	@Select("<script>SELECT * FROM user where 1 = 1"
	            +"<if test='password != \"\"'> and password = #{password} </if>"
	            +"<if test='account != \"\"'> and account = #{account} </if></script>")
	List<User> findBySearchWithPublic(@Param("account") String account,@Param("password") String password);
}
