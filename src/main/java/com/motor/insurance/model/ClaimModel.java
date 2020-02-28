package com.motor.insurance.model;

import java.util.Date;

import com.motor.insurance.entity.Proposal;

import lombok.Data;

@Data
public class ClaimModel {
	private String claimAccidentLocation;
	private String claimAccidentDetail;
	private Date claimcreateDate;
	private String claimStatus;
	private Date   claimLossOfDate;
	private String otherPartyName;
	private String otherPartyPhone;
	

	private Proposal proposal = new Proposal() ; //for saving proposal obj in claim table to get proposal Id
	

	
	public ClaimModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ClaimModel(String claimAccidentLocation, String claimAccidentDetail, Date claimcreateDate,
			String claimStatus, Date claimLossOfDate, Proposal proposal) {
		super();
		this.claimAccidentLocation = claimAccidentLocation;
		this.claimAccidentDetail = claimAccidentDetail;
		this.claimcreateDate = claimcreateDate;
		this.claimStatus = claimStatus;
		this.claimLossOfDate = claimLossOfDate;
		this.proposal = proposal;
	}



	public Proposal getProposal() {
		return proposal;
	}



	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}



	public Date getClaimLossOfDate() {
		return claimLossOfDate;
	}
	public void setClaimLossOfDate(Date claimLossOfDate) {
		this.claimLossOfDate = claimLossOfDate;
	}
	public String getClaimAccidentLocation() {
		return claimAccidentLocation;
	}
	public void setClaimAccidentLocation(String claimAccidentLocation) {
		this.claimAccidentLocation = claimAccidentLocation;
	}
	public String getClaimAccidentDetail() {
		return claimAccidentDetail;
	}
	public void setClaimAccidentDetail(String claimAccidentDetail) {
		this.claimAccidentDetail = claimAccidentDetail;
	}
	public Date getClaimcreateDate() {
		return claimcreateDate;
	}
	public void setClaimcreateDate(Date claimcreateDate) {
		this.claimcreateDate = claimcreateDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getOtherPartyName() {
		return otherPartyName;
	}

	public void setOtherPartyName(String otherPartyName) {
		this.otherPartyName = otherPartyName;
	}

	public String getOtherPartyPhone() {
		return otherPartyPhone;
	}

	public void setOtherPartyPhone(String otherPartyPhone) {
		this.otherPartyPhone = otherPartyPhone;
	}
	
	
	
}
