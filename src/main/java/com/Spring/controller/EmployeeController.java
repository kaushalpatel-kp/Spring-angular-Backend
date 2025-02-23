package com.Spring.controller;

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

import com.Spring.auth.AuthService;
import com.Spring.exception.ResourceNotFoundException;
import com.Spring.model.Employee;
import com.Spring.model.User;
import com.Spring.repository.EmployeeRepository;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private  AuthService authService;
	
//	@PostMapping("/register")
//	public String register(@RequestBody User user) {
//		return authService.register(user);
//		
//	}
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = authService.register(user);

        if (result.equals("User already exists!")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST); // 400 Bad Request
        }

        return new ResponseEntity<>(result, HttpStatus.OK); // 200 OK
    }
	
//get all employee 
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
		
	}
	
	//create emp rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	//get employee by ID rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>   getEmployeeById(@PathVariable Long id) {
	Employee employee  =employeeRepository.findById(id)
			.orElseThrow(()->
			new ResourceNotFoundException("Employee not exist With id:"+ id));
		return ResponseEntity.ok(employee) ;
		
	}
	
	//update employee 
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeedetails){
		Employee employee  =employeeRepository.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException("Employee not exist With id:"+ id));
			
		employee.setFirstName(employeedetails.getFirstName());
		employee.setLastName(employeedetails.getLastName());
		employee.setEmailId(employeedetails.getEmailId());
		
	Employee updatedEmployee =  	employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee) ;
	}
	
	
	//delete employee id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		
		Employee employee  =employeeRepository.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException("Employee not exist With id:"+ id));
		
	 	employeeRepository.delete(employee);
	 	Map<String,Boolean> response = new HashMap<>();
	 	response.put("Deleted",Boolean.TRUE );
	 	return ResponseEntity.ok(response);
	
		
	}
}
