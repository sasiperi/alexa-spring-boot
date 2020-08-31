package com.alexa.oms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alexa.oms.model.CustomerOrder;
import com.alexa.oms.model.CustomerOrderDetail;
import com.alexa.oms.repository.CustomerOrderDetailRepository;
import com.alexa.oms.repository.CustomerOrderRepository;

/**
 * @author sasi.peri
 * @version1.0
 *  
 */
@Service
public class AlexaOrderService
{
    private static final Logger LOG = LoggerFactory.getLogger(AlexaOrderService.class);

    @Autowired(required = true)
    CustomerOrderRepository customerOrderRepository;
    
    @Autowired(required = true)
    CustomerOrderDetailRepository customerOrderDetailRepository;

    public String getOrderStatusString(int customerId, int orderNumber)
    {
        
        LOG.debug("get order status custId={} orderNumber={} " , customerId , orderNumber);
        
        CustomerOrder order = customerOrderRepository.findByCustomerCustomerIdAndOrderNumber(customerId, orderNumber);
        
        StringBuffer sb = new StringBuffer("<speak>");
        if(order == null)
        {
            sb.append("the requested order number " + orderNumber + "was not found </speak>");
        }
        else
        {   
            sb.append("Order Status <break time=\"0.2s\" />");
            sb.append(order.getOrderStatus());
            sb.append("<break time=\"0.2s\" />");
            sb.append(order.getOrderStatusReason());
            sb.append("</speak>");
        }
        
        
        LOG.debug(sb.toString());
        
        return sb.toString();
    }

    public String getTopOrders(int customerId, int numberOfOrders)
    {
        LOG.debug("get top orders custId={} numberOfOrders={} " , customerId , numberOfOrders);
        
        List<CustomerOrder> topOrders = customerOrderRepository.findByCustomerCustomerIdOrderByCreatedDesc(customerId, new PageRequest(0,numberOfOrders));
        String response = getResponseFromOrders(topOrders);
        
        LOG.trace(response);
        
        return response;
    }

    public String getBottomOrders(int customerId, int numberOfOrders)
    {
        LOG.debug("get bottom orders custId={} numberOfOrders={} " , customerId , numberOfOrders);
        
        List<CustomerOrder> bottomOrders = customerOrderRepository.findByCustomerCustomerIdOrderByCreatedAsc(customerId, new PageRequest(0,numberOfOrders));
        String response = getResponseFromOrders(bottomOrders);
        
        LOG.trace(response);
        
        return response;
    }

    public String getOrderDetailsString(int customerId, int orderNumber)
    {
        LOG.debug("get order details custId={} orderNumber={} " , customerId , orderNumber);
        
        List<CustomerOrderDetail> orderDetails = customerOrderDetailRepository.findByCustomerOrderCustomerCustomerIdAndCustomerOrderOrderNumber(customerId, orderNumber);
        
        StringBuffer sb = new StringBuffer("<speak>");
        if(CollectionUtils.isEmpty(orderDetails))
        {
            sb.append("There are no orders found.</speak>");
        }
        else
        {
            sb.append("The following line items were found for the order <break time=\"0.1s\" /> "+ orderNumber +" <break time=\"0.2s\" />");
            for(CustomerOrderDetail orderDetail : orderDetails)
            {                
                sb.append("Product <break time=\"0.2s\" />"+ orderDetail.getProduct_desc() + "<break time=\"0.2s\" />");
                sb.append("Quantity  <break time=\"0.2s\" />"+ orderDetail.getQuantity() +"<break time=\"0.2s\" />");               
            }
            
            sb.append("</speak>");
        }
        
        LOG.trace(sb.toString());
        
        return sb.toString();
    }

   
    private String getResponseFromOrders(List<CustomerOrder> orders)
    {
        StringBuffer sb = new StringBuffer("<speak>");
        if(CollectionUtils.isEmpty(orders))
        {
            sb.append("There are no orders found.</speak>");
        }
        else
        {
            for(CustomerOrder order : orders)
            {                
                sb.append("Order Number <break time=\"0.2s\" />");
                sb.append(order.getOrderNumber() + "<break time=\"0.2s\" />");
                sb.append("Placed on <break time=\"0.2s\" />");
                sb.append(order.getCreated().toString().substring(0,10)+ "<break time=\"0.2s\" />");
                sb.append("By <break time=\"0.2s\" />");
                sb.append(order.getLastUpdatedBy()+ "<break time=\"0.2s\" />");
                sb.append("<break time=\"0.2s\" />");
            }
            
            sb.append("</speak>");
        }
        return sb.toString();
    }
    
    
}
