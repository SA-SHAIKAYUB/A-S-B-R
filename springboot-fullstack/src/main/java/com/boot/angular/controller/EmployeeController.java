package com.boot.angular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.angular.exception.ResourceNotFoundException;
import com.boot.angular.pojo.Employee;
import com.boot.angular.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	
	@PostMapping("employee")
	public void saveAllEmployees(@RequestBody Employee employee) {
		employeeRepository.save(employee);

	}

	
	@PostMapping("employees")
	public Employee creatEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);

	}

	
	@GetMapping("/employees/{id}")
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeNotExist"+ id));
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee update,@PathVariable Long id){
		
	
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("EmployeeNotExist"+ id));
			
		employee.setFirstName(update.getFirstName());
		employee.setLastName(update.getLastName());
		employee.setEmail(update.getEmail());
		Employee updateEmployee = employeeRepository.save(employee);
		return new ResponseEntity<>( updateEmployee,HttpStatus.OK);
		
		
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
	    Map<String, Boolean> response = new HashMap<>();
	    try {
	        Employee employee = employeeRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	        employeeRepository.delete(employee);
	        response.put("deleted", true);
	    } catch (ResourceNotFoundException e) {
	        response.put("deleted", false);
	    }
	    return ResponseEntity.ok(response);	
	}	
	
	
	
	

}
