package com.eagle.hotelmanagementservice.business.abstracts;

import java.util.List;

import com.eagle.hotelmanagementservice.business.request.CustomerCreateRequest;
import com.eagle.hotelmanagementservice.business.request.CustomerUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.CustomerGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.CustomersGetAllResponse;

public interface CustomerService {
	public List<CustomersGetAllResponse> getAllCustomers();
	public List<CustomersGetAllResponse> getByActiveCustomers(boolean active);
	public CustomerGetByIdResponse getByIdCustomer(long id);
	public boolean addCustomer(CustomerCreateRequest customerCreateRequest);
	public boolean updateCustomer(CustomerUpdateRequest customerUpdateRequest);
	public boolean makePassiveCustomer (long id);
	public boolean makeActiveCustomer (long id);
}