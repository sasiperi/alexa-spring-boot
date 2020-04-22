package com.alexa.oms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexa.oms.model.CustomerOrder;
/**
 * Created by sp on 03/15/2017.
 */
@Repository
@Transactional
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>
{    
	
    CustomerOrder findByCustomerCustomerIdAndOrderNumber(@Param("customerId") int customerId, @Param("orderNumber") int orderNumber);   
    
    //List<CustomerOrder> findByCustomerCustomerIdOrderByCreatedDesc(@Param("customerId")int customerId, PageRequest pageRequest);

    //List<CustomerOrder> findByCustomerCustomerIdOrderByCreatedAsc(@Param("customerId")int customerId, PageRequest pageRequest);

    List<CustomerOrder> findByCustomerCustomerIdOrderByCreatedDesc(int customerId, Pageable pageRequest);

    List<CustomerOrder> findByCustomerCustomerIdOrderByCreatedAsc(int customerId, Pageable pageRequest);

    
}
