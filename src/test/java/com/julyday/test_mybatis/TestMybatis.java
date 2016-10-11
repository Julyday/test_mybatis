package com.julyday.test_mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.julyday.test_mybatis.entity.User;

public class TestMybatis {
	private SqlSession sqlSession;

	@Before
	public void init() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
			sqlSession = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {
		User u = new User("julyday", 18);
		sqlSession.insert("User.insert", u);
		sqlSession.commit();
	}
	
	@Test
	public void testFind() {
		User u = sqlSession.selectOne("User.find", 1);
		System.out.println(u);
	}
	
	@Test
	public void testGetList() {
		User user = new User();
		//user.setAge(18);
		//user.setName("julyday");
		user.setName("'' or 1=1 ");
		//List<User> list = sqlSession.selectList("User.getList", user);
		List<User> list = sqlSession.selectList("User.sqlInject", user);
		System.out.println(list.size());
		for(User u : list){
			System.out.println(u);
		}
	}
	
	@Test
	public void testSelectOrder() {
		List<User> list = sqlSession.selectList("User.selectOrder", "age desc");
		System.out.println(list.size());
		for(User u : list){
			System.out.println(u);
		}
	}
	
	
	
	@Test
	public void testUpdate() {
		User u = sqlSession.selectOne("User.find", 1);
		u.setAge(12);
		sqlSession.update("User.update", u);
		sqlSession.commit();
	}
	
	@Test
	public void testDelete() {
		sqlSession.delete("User.delete",1);
		sqlSession.commit();
	}

	@After
	public void destory() {
		sqlSession.close();
	}
}
