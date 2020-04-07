package com.venkat.repositry;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.venkat.model.Employee;

public interface EmployeeRepositry extends ReactiveMongoRepository<Employee, String>{

}
