package com.mkt.sssp2.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkt.sssp2.entities.Department;
import com.mkt.sssp2.repository.DepartmentRepository;



public class TestConnection {
	private ApplicationContext ctx=null;
	private DepartmentRepository departmentRepository;
	{
	 	ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	 	departmentRepository= ctx.getBean(DepartmentRepository.class);
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testRepositorySecondLevelCache(){
		List<Department> departments=departmentRepository.findAll();
		departments=departmentRepository.getAll();
	}
	
}
