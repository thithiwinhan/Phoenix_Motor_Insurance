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
@Table(name = "driver")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "driving_licene")
	private String drivingLicene;
	
	
	@Column(name = "phno")
	private String phno;
	
	@Column(name = "licene_expired_date")
	private Date liceneExpiredDate;
	
	

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="proposal_id")
	private Proposal proposal;
	
	
	
	
	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDrivingLicene() {
		return drivingLicene;
	}
	public void setDrivingLicene(String drivingLicene) {
		this.drivingLicene = drivingLicene;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Date getLiceneExpiredDate() {
		return liceneExpiredDate;
	}
	public void setLiceneExpiredDate(Date liceneExpiredDate) {
		this.liceneExpiredDate = liceneExpiredDate;
	}
	
	
	
}
