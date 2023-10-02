package com.eagle.hotelmanagementservice.business.rules;

import org.springframework.stereotype.Service;

import com.eagle.hotelmanagementservice.core.exception.BadRequestException;
import com.eagle.hotelmanagementservice.core.exception.NotFoundException;
import com.eagle.hotelmanagementservice.dataaccess.abstracts.CustomerRepository;
import com.eagle.hotelmanagementservice.entities.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
	private CustomerRepository customerRepository;
	
	public void checkIfCustomerPhoneExists(String phone) {
		if(this.customerRepository.existsByPhone(phone)) {
			Customer customer = this.customerRepository.findByPhone(phone);
			throw new BadRequestException("Die Telefon- oder Handynummer existiert bereits. Gastname: " + customer.getFirstName()+" "+customer.getLastName());
		}
	}
	
	public void checkIfCustomerNull(Customer customer, long id) {
		if(customer==null) {
			throw new NotFoundException("Der Gast nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfCustomerIdNotExists(long id) {
		if(!this.customerRepository.existsById(id)) {
			throw new NotFoundException("Der Gast nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfCustomerPassiv(long id) {
		Customer customer = this.customerRepository.findById(id).orElse(null);
		if(!customer.isActive()) {
			throw new BadRequestException("Der Gast ist pasiv. Ausgewählte ID: " + id);
		}
	}

}
