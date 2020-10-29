package com.raghav.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghav.model.Employee;
import com.raghav.persistence.EmployeePersistence;

@RestController
@RequestMapping("/employeedetails")
public class EmployeeController {

	@Autowired
	private EmployeePersistence dao;

	@GetMapping(path = "/employees",produces = {"application/xml","application/json"})
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return dao.getEmployees();
	}

	@GetMapping(path = "/employee/{id}",produces = {"application/xml","application/json"})
	public Employee getEmployee(@PathVariable(value = "id") long empID) {
		Employee emp = dao.getEmployee(empID);
		return emp;

	}

	@PostMapping(path = "/employee",produces = {"application/xml","application/json"},consumes = {"application/xml","application/json"})
	public Employee createEmployee(@RequestBody Employee emp) {
		return dao.saveEmployee(emp);
	}
	
	@PutMapping(path = "/employee/{id}",produces = {"application/xml","application/json"},consumes = {"application/xml","application/json"})
	public Employee updateEmployee(@PathVariable(value = "id") long empID, @RequestBody Employee emp) {
		
		Employee updateEmployee = dao.getEmployee(empID);
		
		if(updateEmployee!=null) {
			updateEmployee.setEmpID(emp.getEmpID());
			updateEmployee.setEname(emp.getEname());
			updateEmployee.setSpecialization(emp.getSpecialization());
			
			dao.saveEmployee(updateEmployee);
		}
		
		return updateEmployee;		
		
	}
	
	@DeleteMapping(path = "/employee/{id}")
	public void deleteEmployee(@PathVariable(value = "id") long empID) {
			
		Employee delEmp = dao.getEmployee(empID);
		
		if(delEmp!=null) {
			dao.deleteEmployee(delEmp);
		}
	}
	

}
