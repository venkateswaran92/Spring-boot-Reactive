package com.venkat;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.venkat.model.Employee;
import com.venkat.repositry.EmployeeRepositry;
/**
 * 
 * @author venkateswaran.T
 *
 */

@SpringBootApplication
public class SpringBootRectiveApplication {

	@Bean
	CommandLineRunner employee(EmployeeRepositry employeeRepositry) {
		return args -> {
			employeeRepositry.deleteAll().subscribe(null, null, () -> {
				Stream.of(new Employee(UUID.randomUUID().toString(), "venkat", 15000L),
						new Employee(UUID.randomUUID().toString(), "Raja", 16000L),
						new Employee(UUID.randomUUID().toString(), "sumo", 18000L),
						new Employee(UUID.randomUUID().toString(), "lol", 19000L)).forEach(data -> {
							employeeRepositry.save(data)
							.subscribe(emp->System.out.println(emp));
						});
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRectiveApplication.class, args);
	}

}
