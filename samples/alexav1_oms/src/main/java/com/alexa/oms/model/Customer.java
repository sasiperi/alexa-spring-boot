package com.alexa.oms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the person database table.
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer extends AbstractTimestampEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, name="customer_id")
	private int customerId;
	
	@Column(name="first_name",nullable=false,length=25)
	private String firstName;

	@Column(name="last_name",nullable=false,length=25)
	private String lastName;
		
	@Column(name="dob",nullable=false)
	private Date dob;	
	
	@Column(name="email",nullable=false,length=50, unique=true)
	private String email;
	
	@Column(name="password",nullable=false,length=100)
    private String password;
    
	
	//bi-directional many-to-one association to PatientFlowFile
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerOrder> customerOrder;
	
	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
		this.customerOrder = customerOrder;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	public Customer() {
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	
}