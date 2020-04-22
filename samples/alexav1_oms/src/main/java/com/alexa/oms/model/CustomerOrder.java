package com.alexa.oms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the person database table.
 */
@Entity
@Table(name="customer_order")
@NamedQuery(name="CustomerOrder.findAll", query="SELECT o FROM CustomerOrder o")
public class CustomerOrder extends AbstractTimestampEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, name="order_number")
	private int orderNumber;
	
	@Column(name="order_status",nullable=false,length=25)
	private String orderStatus;

	@Column(name="order_status_reason",nullable=true,length=200)
	private String orderStatusReason;
	
	@Column(name="order_total",nullable=false)
	private int orderTotal;
		
	//bi-directional many-to-one association to PatientFlow
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
    @JsonIgnore
	private Customer customer;	
	
	//bi-directional many-to-one association to PatientFlowFile
	@OneToMany(mappedBy="customerOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerOrderDetail> customerOrderDetail;

	
	public CustomerOrder() {
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getOrderStatusReason() {
		return orderStatusReason;
	}


	public void setOrderStatusReason(String orderStatusReason) {
		this.orderStatusReason = orderStatusReason;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<CustomerOrderDetail> getCustomerOrderDetail() {
		return customerOrderDetail;
	}


	public void setCustomerOrderDetail(List<CustomerOrderDetail> customerOrderDetail) {
		this.customerOrderDetail = customerOrderDetail;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
}