package com.eagle.hotelmanagementservice.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.hotelmanagementservice.business.abstracts.CustomerService;
import com.eagle.hotelmanagementservice.business.request.CustomerCreateRequest;
import com.eagle.hotelmanagementservice.business.request.CustomerUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.CustomerGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.CustomersGetAllResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CustomerController {
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public List<CustomersGetAllResponse> getAllCustomer() {
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping("/getByActiveCustomers")
	public List<CustomersGetAllResponse> getByActiveCustomers(@RequestParam boolean active) {
		return this.customerService.getByActiveCustomers(active);
	}
	
	@GetMapping("/getByIdCustomer")
	public CustomerGetByIdResponse getByIdCustomer(@RequestParam long id) {
		return this.customerService.getByIdCustomer(id);
	}
	
	@PostMapping("/addCustomer")
	public boolean customerAdd(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
		return this.customerService.addCustomer(customerCreateRequest);
	}
	
	@PutMapping("/updateCustomer")
	public boolean customerUpdate(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
		return this.customerService.updateCustomer(customerUpdateRequest);
	}
	
	@PutMapping("/makePassiveCustomer")
	public boolean makePassiveCustomer(@RequestParam long id) {
		return this.customerService.makePassiveCustomer(id);
	}
	
	@PutMapping("/makeActiveCustomer")
	public boolean makeActiveCustomer(@RequestParam long id) {
		return this.customerService.makeActiveCustomer(id);
	}
}