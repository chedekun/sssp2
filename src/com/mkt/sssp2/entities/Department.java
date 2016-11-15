package com.mkt.sssp2.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="SSSP_DEPARTMENTS")
@Entity
public class Department {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	private String department_name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", department_name=" + department_name + "]";
	}
	
	
	
}
