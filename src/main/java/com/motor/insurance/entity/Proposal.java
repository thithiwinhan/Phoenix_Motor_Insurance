package com.motor.insurance.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "proposal")
public class Proposal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int proposalId;
	
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "sum_insure")
	private Double sumInsure;
	
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "coverage_type")
	private String coverageType;
	
	@Column(name = "premium")
	private Double premium;

	@Column(name = "start_date")
	private Date startDate;
	
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "create_date")
	private Date createDate;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="policy_holder_id")
	private PolicyHolder policyHolder;
	
	
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="payment_id") private Payment payment;
	 * 
	 */
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proposal",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Payment>payments;
	
	
	
	@OneToMany(mappedBy = "proposal",fetch=FetchType.EAGER,cascade = CascadeType.ALL)//add cascade type all
	private List<Benificial>benificials;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "proposal",cascade = CascadeType.ALL)
	private List<Driver>drivers;
	
	
	@OneToMany(mappedBy = "proposal",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Claim>claims;
	
	
	
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}
	
	
	
	
	public List<Benificial> getBenificials() {
		return benificials;
	}
	public void setBenificials(List<Benificial> benificials) {
		this.benificials = benificials;
	}
	
	
	public List<Driver> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	
	

	public List<Claim> getClaims() {
		return claims;
	}
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public int getProposalId() {
		return proposalId;
	}
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	public Double getSumInsure() {
		return sumInsure;
	}
	public void setSumInsure(Double sumInsure) {
		this.sumInsure = sumInsure;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
}


