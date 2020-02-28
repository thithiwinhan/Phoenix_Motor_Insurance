package com.motor.insurance.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.insurance.dao.PaymentDao;
import com.motor.insurance.dao.ProposalDao;
import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.Payment;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.model.PaymentModel;

@Service
@Transactional
public class PaymentServiceImpl implements Paymentservice {
       
	@Autowired
	EntityManager em;
	
	@Autowired
	PaymentDao paymentDao;
	
	
	@Autowired
	ProposalDao proposalDao;
	@Override
	public void save(PaymentModel paymentModel) {
        Payment payment = new Payment();
        Proposal p = new Proposal();
		System.out.println("----------------Payment Save-----------------");
		//System.out.println("Proposal ID"+paymentModel.getProposalId());

		payment.setPayDate(new Date());
		payment.setAmount(paymentModel.getAmount());
		payment.setPayentMethod(paymentModel.getBank());
		payment.setProposal(paymentModel.getProposal());
		p.setStatus("paid");
        Optional<Proposal> proposalList=this.proposalDao.findById(paymentModel.getProposal().getProposalId());
        if(proposalList.isPresent())
        {
        	p=proposalList.get();
        	p.setStatus("paid");
    		proposalDao.save(p);

        }
		paymentDao.save(payment);
		
			
	}

	@Override
	public Boolean findStatusbyProposalId(int proposalId) {
  boolean flag = false;
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Proposal> cq=cb.createQuery(Proposal.class);
		System.out.println("---------payment Repo-------------"+proposalId);
		Root<Proposal> proposal=cq.from(Proposal.class);
		
		
			Predicate p=cb.equal(proposal.get("proposalId"), proposalId);
			//Predicate p2=cb.equal(proposal.get("status"), "accept");
			Predicate p2=cb.notEqual(proposal.get("status"), "pending");

			cq.where(cb.and(p,p2)).distinct(true);
		
			TypedQuery<Proposal> typedQuery =em.createQuery(cq);
			List<Proposal> resultList = typedQuery.getResultList();
			
	        if (resultList.isEmpty()||resultList.size()==0) {
	        	System.out.println("pending is found");
				 return flag; //proposal staus is pending
			}


		
	        else {
	        	System.out.println("accept is found");
	        	return flag =true; //propsal status is accept or paid}
		
		
	}

}
}
