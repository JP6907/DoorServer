package com.neo.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;

/**
 * Mybatis 方式
 * 访问数据库类
 * @author Administrator
 *
 */
public class DBAccess {
	/**
	 * 获取数据库会话对象 SqlSession
	 * @return SqlSession对象
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException{
		//通过配置文件获取数据库连接信息
		Reader reader = Resources.getResourceAsReader("com/neo/config/Configuration.xml");
		//通过配置文件构建SqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过SqlSessionFactory获取SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
