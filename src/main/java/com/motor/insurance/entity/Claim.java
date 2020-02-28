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

import org.springframework.lang.Nullable;

import com.motor.insurance.model.ClaimModel;

@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "loss_of_date")
	private Date lossOfDate;
	
	
	@Column(name = "accident_location")
	private String accidentLocation;
	@Nullable
	@Column(name = "accident_detail")
	private String accidentDetail;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "createdDate")
	private Date createClaimDate;
	

	@Column(name="other_party_name") //add new input field  for other involved party name
	private String otherPartyName;
	

	@Column(name="other_party_phone") //add new  input field  for other involved party phone
	private String otherPartyPhone;
	

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
	public Date getCreateClaimDate() {
		return createClaimDate;
	}
	public void setCreateClaimDate(Date createClaimDate) {
		this.createClaimDate = createClaimDate;
	}
	
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
	
	
	public String getAccidentDetail() {
		return accidentDetail;
	}
	public void setAccidentDetail(String accidentDetail) {
		this.accidentDetail = accidentDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAccidentLocation() {
		return accidentLocation;
	}
	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}
	public Date getLossOfDate() {
		return lossOfDate;
	}
	public void setLossOfDate(Date lossOfDate) {
		this.lossOfDate = lossOfDate;
	}
	
	
}
