package com.mkt.sssp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkt.sssp2.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//Check if the the reviesed lastname exists.
	Employee getByLastName(String lastName);
}
