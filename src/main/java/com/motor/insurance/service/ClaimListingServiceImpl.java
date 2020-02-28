package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.ClaimListingDao;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.model.ClaimModel;
import com.motor.insurance.model.ProposalModel;

@Service
@Transactional
public class ClaimListingServiceImpl implements ClaimListingService {

	@Autowired
	EntityManager em;

	@Autowired
	ClaimListingDao claimDao;

	// for show claim list in claim status checking ;join two table claims and
	// proposal
	@Override
	public List<ClaimModel> claimListing(User user) {
		
		
			
		System.out.println("------------------Arrive claim dao-----------" + user.getId());

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
		Root<Proposal> proposal = cq.from(Proposal.class);
		Join<Proposal, Claim> claims = proposal.join("claims");
		//cq.select(claims).where(cb.equal(proposal.get("user"), user.getId()));

		Predicate p = cb.equal(proposal.get("user"),user.getId());
		Predicate p2=cb.equal(proposal.get("active"), 1);
		cq.select(claims).where(cb.and(p,p2));
		
		
		TypedQuery<Claim> tq = em.createQuery(cq);
		List<Claim> calimList = new ArrayList<Claim>();
		try {
			calimList = tq.getResultList();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ClaimModel> claimModelList = new ArrayList<ClaimModel>();

		for (Claim entity : calimList) {
			ClaimModel model = new ClaimModel();
			System.out.println("------------------Enity Return  Claim List>>-----------");
			System.out.println("-----" + "-------------1>>-----------" + entity.getStatus());
			System.out.println("------------------2>>-----------" + entity.getCreateClaimDate());
			System.out.println("------------------3>>-----------" + entity.getProposal().getCoverageType());
			System.out.println("------------------4>>-----------" + entity.getProposal().getBenificials());
			System.out.println("------------------5>>-----------" + entity.getProposal().getPolicyHolder().getName());
			model.setClaimcreateDate(entity.getCreateClaimDate());
			model.setClaimStatus(entity.getStatus());
			model.setProposal(entity.getProposal());
			claimModelList.add(model);

		}

		return claimModelList;

	}
	/*
	 * @Override public List<ClaimModel> findClaimById(int proposalId) {
	 * System.out.println("------------Claim Listing  search Dao -----------");
	 * 
	 * Optional<Claim> claimList = claimDao.findById(proposalId);
	 * 
	 * List<ClaimModel> claimModelList = new ArrayList<ClaimModel>(); if
	 * (claimList.isPresent()) {
	 * 
	 * Claim entity = claimList.get(); ClaimModel model = new ClaimModel();
	 * model.setClaimcreateDate(entity.getCreateClaimDate());
	 * model.setClaimStatus(entity.getStatus());
	 * model.setProposal(entity.getProposal()); claimModelList.add(model); }
	 * System.out.println("Claim search list size>>>>>>>>>>>>>>>>.."+claimModelList.
	 * size());
	 * 
	 * //else System.out.println("Resource not found id");
	 * 
	 * return claimModelList; }
	 */

	@Override
	public List<ClaimModel> findClaimById(Proposal proposalModel) {

		//System.out.println("------------------Arrive claim dao-----------" + user.getId());

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
		Root<Proposal> proposal = cq.from(Proposal.class);
		Join<Proposal, Claim> claims = proposal.join("claims");
		//cq.select(claims).where(cb.equal(proposal.get("user"), user.getId()));

		//Predicate p = cb.equal(proposal.get("user"),user.getId());
		Predicate p2=cb.equal(proposal.get("proposalId"), proposalModel.getProposalId());
		cq.select(claims).where(cb.and(p2));
		
		
		TypedQuery<Claim> tq = em.createQuery(cq);
		List<Claim> calimList = new ArrayList<Claim>();
		try {
			calimList = tq.getResultList();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ClaimModel> claimModelList = new ArrayList<ClaimModel>();

		for (Claim entity : calimList) {
			ClaimModel model = new ClaimModel();
			System.out.println("------------------Enity Return  Claim List>>-----------");
			System.out.println("-----" + "-------------1>>-----------" + entity.getStatus());
			System.out.println("------------------2>>-----------" + entity.getCreateClaimDate());
			System.out.println("------------------3>>-----------" + entity.getProposal().getCoverageType());
			System.out.println("------------------4>>-----------" + entity.getProposal().getBenificials());
			System.out.println("------------------5>>-----------" + entity.getProposal().getPolicyHolder().getName());
			model.setClaimcreateDate(entity.getCreateClaimDate());
			model.setClaimStatus(entity.getStatus());
			model.setProposal(entity.getProposal());
			claimModelList.add(model);

		}

		return claimModelList;

	}


}
