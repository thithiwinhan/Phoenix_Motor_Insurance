package com.motor.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "benificial")
public class Benificial {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;



	@Column(name = "name")
	private String name;

	@Column(name = "phno")
	private String phno;

	@Column(name = "nrc")
	private String nrc;

	@Column(name = "address")
	private String address;

	
	@ManyToOne
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

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
