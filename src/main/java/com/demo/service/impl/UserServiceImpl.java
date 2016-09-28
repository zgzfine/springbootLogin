package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int count(){
		return userMapper.count();
	}
	
	@Transactional
	@Override
	public boolean findRepeat(String account,String password){
		List<User> list = userMapper.findBySearchWithPublic(account,password);
		if(list!=null&&list.size()>0){
			return true;
		}else
			return false;
	}
}
