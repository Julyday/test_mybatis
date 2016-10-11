package com.julyday.test_mybatis.dao;

import java.util.List;

import com.julyday.test_mybatis.entity.User;

public interface UserDao {
	public void insert(User user);
	
	public User find(int id);
	
	public List<User> selectOrder(String order);
	
	public List<User> getList(User user);
	
	public List<User> sqlInject(User user);
	
	public void update(User user);
	
	public void delete(int id);
}
