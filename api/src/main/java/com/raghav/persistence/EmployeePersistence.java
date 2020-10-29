package com.raghav.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghav.model.Employee;
import com.raghav.repository.APIRepository;

@Service
public class EmployeePersistence {

	@Autowired
	APIRepository repo;
	
	public Employee saveEmployee(Employee emp) {
		return repo.save(emp);
	}
	
	public List<Employee> getEmployees(){
		return repo.findAll();
	}
	
	public Employee getEmployee(long empID) {
		return repo.findById(empID).get();
	}
	
	public void deleteEmployee(Employee emp) {
		 repo.delete(emp);
	}
	
}
