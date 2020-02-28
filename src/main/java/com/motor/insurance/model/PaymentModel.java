package com.motor.insurance.model;

import java.util.Date;

import com.motor.insurance.entity.Proposal;

import lombok.Data;

@Data
public class PaymentModel {
	private int paymentId;
	private Double amount;
	private String bank;
	private Date paydate;
	
	private Proposal proposal = new Proposal() ; //for saving proposal obj in payment table to get proposal Id

	
	
	public PaymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public PaymentModel(int paymentId, Double amount, String bank, Date paydate, Proposal proposal) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.bank = bank;
		this.paydate = paydate;
		this.proposal = proposal;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	
}
