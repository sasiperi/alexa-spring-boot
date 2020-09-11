package com.alexa.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexa.oms.model.CustomerOrderDetail;
/**
 * Created by sp on 03/15/2017.
 */
@Repository
@Transactional
public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail, Long>
{

    List<CustomerOrderDetail> findByCustomerOrderCustomerCustomerIdAndCustomerOrderOrderNumber(int customerId, int orderNumber);    
	
	
}
