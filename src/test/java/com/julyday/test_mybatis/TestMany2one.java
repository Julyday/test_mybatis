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

import com.julyday.test_mybatis.entity.Order;
import com.julyday.test_mybatis.entity.Product;

public class TestMany2one {
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
		Product product = new Product();
		product.setId(3);
		List<Product> list = sqlSession.selectList("Product.findAll",product);
		for(Product p : list){
			System.out.println(p.toString());
			Order o  = p.getOrder();
			System.out.println(o.toString());
		}
	}
	
	@Test
	public void testFind() {
		Product product = new Product();
		product.setId(3);
		Product p = sqlSession.selectOne("Product.find", product);
		System.out.println(p.toString());
		if(p.getOrder() != null){
			System.out.println(p.getOrder().toString());
		}else{
			System.out.println("order null ...");
		}
		
	}
	
	@After
	public void destory() {
		sqlSession.close();
	}
}
