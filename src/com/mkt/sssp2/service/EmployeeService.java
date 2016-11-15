package com.mkt.sssp2.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkt.sssp2.entities.Employee;
import com.mkt.sssp2.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Transactional
	public void delete(Integer id){
		employeeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Employee get(Integer id){
		return employeeRepository.findOne(id);
	}
		
	
	/***
	 * Save or Update employee
	 * @param employee
	 */
	@Transactional
	public void save(Employee employee){
		// Set the createdTime;
		if(employee.getId()==null){
			employee.setCreateTime(new Date());	
		}
		
		employeeRepository.saveAndFlush(employee);
	}
	
	
	/**
	 * Query by lastName to check if the lastName exists
	 * @param lastName
	 * @return
	 */
	@Transactional(readOnly=true)
	public Employee getByLastName(String lastName){
		return employeeRepository.getByLastName(lastName);
	}
	
	
/**
 * List All employees
 * @param pageNo
 * @param pageSize
 * @return
 */
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo,int pageSize){
		PageRequest pageable=new PageRequest(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
		
	} 
}
