package com.raghav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.Model.Employee;

public interface APIRepository extends JpaRepository<Employee, Integer>{

}
