package com.mkt.sssp2.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="SSSP_EMPLOYEES")
@Entity
public class Employee {
	@GeneratedValue
	@Id
	private Integer id;
	private String lastName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")   //to match adding the string date from the add page. by james.
	private Date birth;
	
	private String email;
	private Date createTime;
	
	@JoinColumn(name="DEPARTMENT_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Department department;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirth() {
		return birth;
	}
	@Temporal(TemporalType.DATE)
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/*@JoinColumn(name="DEPARTMENT_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}*/
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
