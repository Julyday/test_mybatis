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

import com.julyday.test_mybatis.entity.Grade;
import com.julyday.test_mybatis.entity.Student;

public class TestOne2many {
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
	public void testFindall() {
		Grade grade = new Grade();
		grade.setId(1);
		List<Grade> list = sqlSession.selectList("Grade.findAll",grade);
		for(Grade g : list){
			System.out.println(g.toString());
			List<Student> ls = g.getStudents();
			for(Student s : ls){
				System.out.println(s.toString());
			}
		}
	}
	
	@Test
	public void testFind() {
		Grade grade = new Grade();
		grade.setId(1);
		Grade g = sqlSession.selectOne("Grade.find", grade);
		System.out.println(g.toString());
		List<Student> ls = g.getStudents();
		for(Student s : ls){
			System.out.println(s.toString());
		}
	}
	
	@After
	public void destory() {
		sqlSession.close();
	}
}
