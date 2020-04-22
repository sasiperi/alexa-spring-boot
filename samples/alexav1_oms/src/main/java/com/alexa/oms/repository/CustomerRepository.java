package com.alexa.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexa.oms.model.Customer;
/**
 * Created by sp on 03/15/2017.
 */
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

    Customer findByEmail(@Param("email")String email);    
	
	
}
