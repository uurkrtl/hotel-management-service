package com.eagle.hotelmanagementservice.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.hotelmanagementservice.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByIsActive(boolean active, Sort by);
	boolean existsByPhone(String name);
	Customer findByPhone(String phone);
	List<Customer> findAllByOrderByFirstNameAsc();

}