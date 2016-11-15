package com.mkt.sssp2.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkt.sssp2.entities.Employee;
import com.mkt.sssp2.service.DepartmentService;
import com.mkt.sssp2.service.EmployeeService;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		System.out.println("deleting " + id  +" ...");
		employeeService.delete(id);
		
		return "redirect:/emps";
	}
	
	
	
	
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id !=null){
			Employee employee=employeeService.get(id);
			//set the department to null to cut off the connection to the old department id
			employee.setDepartment(null);
			map.put("employee", employee);
		}
	}
	
	
	
	/***
	 * Recall-display after choosing the item for updating.
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		Employee employee = employeeService.get(id);
		map.put("employee", employee);
		map.put("departments", departmentService.getAll());

		System.out.println("选中雇员后回显，准备修改...");
		return "emp/input";
	}

	/**
	 * UPDATE	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT) // put:revise
	public String update(Employee employee) {
		employeeService.save(employee);
		return "redirect:/emps";
	}

	/**
	 * SAVE 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee employee) {
		employeeService.save(employee);
		return "redirect:/emps";
	}

	/***
	 * Check if the lastName repeats, for being used in Adding function.
	 * 
	 * @param lastName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ajaxValidateLastName", method = RequestMethod.POST)
	public String validateLastName(@RequestParam(value = "lastName", required = true) String lastName) {

		Employee employee = employeeService.getByLastName(lastName);
		if (employee == null) {
			return "0";
		} else {
			return "1";
		}

	}

	/**
	 * Get the Adding-New-Employee page.
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "emp/input";
	}

	/***
	 * List All Employees
	 * 
	 * @param PageNo
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;

		try {

			// check pageNo ;
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Page<Employee> page = employeeService.getPage(pageNo, 5);
		map.put("page", page);

		return "emp/list";
	}

	

}
