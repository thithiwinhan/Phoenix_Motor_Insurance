package com.motor.insurance.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id ;
	
	@Column(name = "payent_method")
	private String payentMethod;
	
	@Column(name = "pay_date")
	private Date payDate;
	
	
	@Column(name = "amount")
	private Double amount;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="proposal_id")
	private Proposal proposal;
	
	
	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayentMethod() {
		return payentMethod;
	}

	public void setPayentMethod(String payentMethod) {
		this.payentMethod = payentMethod;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
	
	

}
