package com.mkt.sssp2.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.mkt.sssp2.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	//Because the default findAll method could not support the second cache, the special method shall be created.
	@QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("FROM Department d")
	List<Department> getAll();
}
