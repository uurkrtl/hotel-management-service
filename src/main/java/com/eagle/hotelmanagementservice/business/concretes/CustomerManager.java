package com.eagle.hotelmanagementservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.business.abstracts.CustomerService;
import com.eagle.hotelmanagementservice.business.request.CustomerCreateRequest;
import com.eagle.hotelmanagementservice.business.request.CustomerUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.CustomerGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.CustomersGetAllResponse;
import com.eagle.hotelmanagementservice.business.rules.CustomerBusinessRules;
import com.eagle.hotelmanagementservice.core.utilities.mappers.ModelMapperService;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.CustomerRepository;
import com.eagle.hotelmanagementservice.entities.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
	private CustomerRepository customerRepository;
	private ModelMapperService modelMapperService;
	private CustomerBusinessRules customerBusinessRules;

	@Override
	public List<CustomersGetAllResponse> getAllCustomers() {
		List<Customer> customers = this.customerRepository.findAllByOrderByFirstNameAsc();
		List<CustomersGetAllResponse> customersGetAllRespons = customers.stream().map(customer->this.modelMapperService.forResponse().map(customer, CustomersGetAllResponse.class)).collect(Collectors.toList());
		return customersGetAllRespons;
	}

	@Override
	public List<CustomersGetAllResponse> getByActiveCustomers(boolean active) {
		List<Customer> customers = this.customerRepository.findByIsActive(active, Sort.by(Sort.Direction.ASC, "firstName"));
		List<CustomersGetAllResponse> customersGetAllRespons = customers.stream().map(customer->this.modelMapperService.forResponse().map(customer, CustomersGetAllResponse.class)).collect(Collectors.toList());
		return customersGetAllRespons;
	}

	@Override
	public CustomerGetByIdResponse getByIdCustomer(long id) {
		Customer customer = this.customerRepository.findById(id).orElse(null);
		this.customerBusinessRules.checkIfCustomerNull(customer, id);
		CustomerGetByIdResponse customerGetByIdResponse = this.modelMapperService.forResponse().map(customer, CustomerGetByIdResponse.class);
		return customerGetByIdResponse;
	}

	@Override
	public boolean addCustomer(CustomerCreateRequest customerCreateRequest) {
		this.customerBusinessRules.checkIfCustomerPhoneExists(customerCreateRequest.getPhone());
		Customer customer = this.modelMapperService.forRequest().map(customerCreateRequest, Customer.class);
		customer.setActive(true);
		this.customerRepository.save(customer);
		return true;
	}

	@Override
	public boolean updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
		long selectedId = customerUpdateRequest.getId();
		this.customerBusinessRules.checkIfCustomerIdNotExists(selectedId);
		Customer customer = this.modelMapperService.forRequest().map(customerUpdateRequest, Customer.class);
		Customer selectedCustomer = this.customerRepository.findById(selectedId).orElse(null);
		customer.setActive(selectedCustomer.isActive());
		this.customerRepository.save(customer);
		return true;
	}

	@Override
	public boolean makePassiveCustomer(long id) {
		this.customerBusinessRules.checkIfCustomerIdNotExists(id);
		Customer customer = this.customerRepository.findById(id).orElse(null);
		customer.setActive(false);
		this.customerRepository.save(customer);
		return true;
	}

	@Override
	public boolean makeActiveCustomer(long id) {
		this.customerBusinessRules.checkIfCustomerIdNotExists(id);
		Customer customer = this.customerRepository.findById(id).orElse(null);
		customer.setActive(true);
		this.customerRepository.save(customer);
		return true;
	}
}