package com.alexa.oms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the person database table.
 */
@Entity
@Table(name="customer_order_detail")
@NamedQuery(name="CustomerOrderDetail.findAll", query="SELECT d FROM CustomerOrderDetail d")
public class CustomerOrderDetail extends AbstractTimestampEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, name="line_item_id")
	private int lineItemId;
	
	@Column(name="product_code",nullable=false,length=25)
	private String productCode;

	@Column(name="product_desc",nullable=false,length=100)
	private String product_desc;
		
	@Column(name="quantity",nullable=false)
	private int quantity;	
	
	@Column(name="price",nullable=false)	
	private int price;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderNumber")
    @JsonIgnore
	private CustomerOrder customerOrder;	
	
	public CustomerOrderDetail() {
	}
	

    public int getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId)
    {
        this.lineItemId = lineItemId;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProduct_desc()
    {
        return product_desc;
    }

    public void setProduct_desc(String product_desc)
    {
        this.product_desc = product_desc;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public CustomerOrder getCustomerOrder()
    {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder)
    {
        this.customerOrder = customerOrder;
    }


	
}