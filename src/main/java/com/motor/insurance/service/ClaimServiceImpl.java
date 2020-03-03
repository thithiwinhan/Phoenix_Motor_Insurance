
package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.ClaimDao;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.exception.ResourceNotFoundException;
import com.motor.insurance.model.ClaimModel;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService{
	@Autowired
	ClaimDao claimDao;
	
	@Autowired
	EntityManager em;
	
	@Override
	public void save(ClaimModel claimModel) {
		Claim claim = new Claim();
		
		System.out.println("----------------Claim Save----------------- PID"+claimModel.getProposal().getProposalId());
		
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


	@Override
	public int countClaimNumber(int id) {
		  int count=0;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
		Root<Proposal> proposal = cq.from(Proposal.class);
		Join<Proposal, Claim> claims = proposal.join("claims");
		//cq.select(claims).where(cb.equal(proposal.get("user"), user.getId()));

		Predicate p = cb.equal(proposal.get("vehicle"),id);
		Predicate p2=cb.equal(proposal.get("active"), 1);
		Predicate p3=cb.equal(proposal.get("status"), "paid");
        
		cq.select(claims).where(cb.and(p,p2,p3));
		
		
		TypedQuery<Claim> tq = em.createQuery(cq);
		List<Claim> calimList = new ArrayList<Claim>();	
		try {
			calimList = tq.getResultList();
		}
		catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Claim entity : calimList) {
			
		      Date today = new Date();
			if (today.after(entity.getProposal().getEndDate())) {
				  count++;
			}
			else {System.out.println("policy is not expired");	
			}
	}
		return count;
	
	}

	@Override
	public List<Claim> findAllClaimStatus() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
		Root<Proposal> proposal = cq.from(Proposal.class);
		Join<Proposal, Claim> claim = proposal.join("claims");

		Predicate p1 = cb.equal(proposal.get("active"), 1);
		Predicate p2 = cb.equal(claim.get("status"), "accept");

		cq.select(claim).where(cb.and(p1, p2));
		TypedQuery<Claim> tq = em.createQuery(cq);
		List<Claim> cliamList = new ArrayList<Claim>();
		try {
			cliamList = tq.getResultList();
			System.out.println("claim accept for email >>>>>>>>>>>"+cliamList.size());
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (cliamList.isEmpty() || cliamList.size() <= 0) {
			System.out.println("empty of claim that is accept");
		}

		else {
			return cliamList;
		}
		return cliamList;
	}

}
