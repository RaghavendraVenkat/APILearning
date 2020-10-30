package com.raghav.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public Optional<Employee> getEmployee(@PathVariable(value = "id") long empID) {
		Optional<Employee> emp = dao.getEmployee(empID);
		return emp;

	}

	@PostMapping(path = "/employee",produces = {"application/xml","application/json"},consumes = {"application/xml","application/json"})
	public Employee createEmployee(@RequestBody Employee emp) {
		return dao.saveEmployee(emp);
	}
	
	@PutMapping(path = "/employee/{id}",produces = {"application/xml","application/json"},consumes = {"application/xml","application/json"})
	public Optional<Employee> updateEmployee(@PathVariable(value = "id") long empID, @RequestBody Employee emp) {
		
		Optional<Employee> updateEmployee = dao.getEmployee(empID);
		
		if(updateEmployee.isPresent()) {
			updateEmployee.get().setEmpID(emp.getEmpID());
			updateEmployee.get().setEname(emp.getEname());
			updateEmployee.get().setSpecialization(emp.getSpecialization());
			
			dao.saveEmployee(updateEmployee.get());
		}
		
		return updateEmployee;		
		
	}
	
	@DeleteMapping(path = "/employee/{id}")
	public Optional<Employee> deleteEmployee(@PathVariable(value = "id") long empID) {
			
		Optional<Employee> delEmp = dao.getEmployee(empID);
		
		if(delEmp.isPresent()) {
			dao.deleteEmployee(delEmp.get());
		}
		
		return delEmp;
	}
	

}
