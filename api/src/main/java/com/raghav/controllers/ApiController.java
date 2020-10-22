package com.raghav.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghav.model.Employee;
import com.raghav.persistence.APIdao;

@RestController
@RequestMapping("/employeedetails")
public class ApiController {

	@Autowired
	private APIdao dao;

	@GetMapping("/employees")
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return dao.getEmployees();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable(value = "id") int empID) {
		Optional<Employee> emp = dao.getEmployee(empID);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);

	}

	@PostMapping("/employee")
	public Employee createEmployee(Employee emp) {
		return dao.saveEmployee(emp);
	}

}
