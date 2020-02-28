
package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.ClaimDao;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.exception.ResourceNotFoundException;
import com.motor.insurance.model.ClaimModel;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService{
	@Autowired
	ClaimDao claimDao;
	

	@Override
	public void save(ClaimModel claimModel) {
		Claim claim = new Claim();
		
		System.out.println("----------------Claim Save----------------- PID"+claimModel.getProposal().getProposalId());
		//System.out.println("Proposal ID"+claimModel.getProposalId());

		/*
		 * Proposal p = new Proposal(); p.setProposalId(claimModel.getProposalId());
		 * claim.setProposal(p.getProposalId());
		 */
		
		/*
		 * Proposal p = new Proposal();
		 * p.setProposalId(claimModel.getProposalModel().getpID());
		 */
		
		System.out.println("----------------Claim Save----------------- PID"+claimModel.getProposal().getProposalId()+claimModel.getProposal().getCoverageType());
		claim.setProposal(claimModel.getProposal());

		claim.setAccidentDetail(claimModel.getClaimAccidentDetail());
		claim.setAccidentLocation(claimModel.getClaimAccidentLocation());
		claim.setCreateClaimDate(new Date());
		claim.setLossOfDate(claimModel.getClaimLossOfDate());
		claim.setOtherPartyName(claimModel.getOtherPartyName());
		claim.setOtherPartyPhone(claimModel.getOtherPartyPhone());

		claim.setStatus("pending");
		//claim.setProposal(claimModel.getProposalModel());


		claimDao.save(claim);
		
	}


	//claim satus checking ,includes show claim list 
	@Override
	public List<ClaimModel> findClaimbyProposalId(int proposalId) {
		
		  List<ClaimModel> calimList= new ArrayList<ClaimModel>();
		  Optional<Claim> calimEntity = claimDao.findById(proposalId);
		
		if( calimEntity.isPresent()){
			 Claim claimObj=calimEntity.get();
			 ClaimModel claim = new ClaimModel();
			 claim.setClaimAccidentLocation(claimObj.getAccidentDetail());
			 claim.setClaimLossOfDate(claimObj.getLossOfDate());
			 claim.setClaimStatus(claimObj.getStatus());
			// claim.setProposalId(claimObj.getProposal().getProposalId());
			calimList.add(claim);
		 }
		
		else {
			throw new ResourceNotFoundException ("Not found claim by given id");
  
		}
		return calimList.isEmpty() ? null :calimList ;
	}
	
	
	
	

}
