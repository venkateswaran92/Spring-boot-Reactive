package com.venkat.resource;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.model.Employee;
import com.venkat.model.EmployeeEvent;
import com.venkat.repositry.EmployeeRepositry;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employee")
public class ReactiveController {

	@Autowired
	private EmployeeRepositry employeeRepositry;

	public ReactiveController(EmployeeRepositry employeeRepositry) {
		this.employeeRepositry = employeeRepositry;
	}

	@GetMapping("/all")
	private Flux<Employee> allEmployee() {
		return employeeRepositry.findAll();
	}

	@GetMapping("/{id}")
	private Mono<Employee> getId(@PathVariable("id") final String empId) {
		return employeeRepositry.findById(empId);
	}

	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String empId) {
		return employeeRepositry.findById(empId).flatMapMany(emplpyee -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<EmployeeEvent> employeeEventFlux = Flux
					.fromStream(Stream.generate(() -> new EmployeeEvent(emplpyee, new Date())));
			return Flux.zip(interval, employeeEventFlux).map(data -> data.getT2());
		});
	}
}
