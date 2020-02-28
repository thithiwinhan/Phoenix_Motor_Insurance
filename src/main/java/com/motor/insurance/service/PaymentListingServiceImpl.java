package com.motor.insurance.service;

import java.util.ArrayList;
import java.util.List;

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

import com.motor.insurance.entity.Claim;
import com.motor.insurance.entity.Payment;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.model.ClaimModel;
import com.motor.insurance.model.PaymentModel;
import com.motor.insurance.model.ProposalModel;

@Service
@Transactional
public class PaymentListingServiceImpl implements PaymentListingService {

		@Autowired
		EntityManager em;

		

		@Override
		public List<PaymentModel> paymentListing(User user) {
			System.out.println("------------------Arrive claim dao-----------"+user.getId());
			
			  CriteriaBuilder cb = em.getCriteriaBuilder();
		        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
		        Root<Proposal> proposal = cq.from(Proposal.class);
		        Join<Proposal, Payment> payments = proposal.join("payments");
		        cq.select(payments).where(cb.equal(proposal.get("user"), user.getId()));

		        TypedQuery<Payment> tq = em.createQuery(cq);
		        List<Payment>paymentList = new ArrayList<Payment>();
		        try {
					paymentList = tq.getResultList();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		        catch (IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			List<PaymentModel> paymentModelList = new ArrayList<PaymentModel>();

			for (Payment entity : paymentList) {
				PaymentModel model = new PaymentModel();
				System.out.println("------------------Enity Return  Payment List>>-----------");
				System.out.println("------------------1>>-----------"+entity.getPayentMethod());
				System.out.println("------------------2>>-----------"+entity.getAmount());
				System.out.println("------------------3>>-----------"+entity.getPayDate());
				System.out.println("------------------4>>-----------"+entity.getProposal().getProposalId());
				model.setAmount(entity.getAmount());	
				model.setBank(entity.getPayentMethod());
				model.setProposal(entity.getProposal());
				model.setPaydate(entity.getPayDate());
				model.setProposal(entity.getProposal());
	            paymentModelList.add(model);

			}

			return paymentModelList;

		
		}



		@Override
		public List<PaymentModel> findPaymentAmountByProposalId(int proposalId) {
			System.out.println("------------------Arrive claim dao-----------"+proposalId);
			
			  CriteriaBuilder cb = em.getCriteriaBuilder();
		        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
		        Root<Proposal> proposal = cq.from(Proposal.class);
		        Join<Proposal, Payment> payments = proposal.join("payments");
		        cq.select(payments).where(cb.equal(proposal.get("proposalId"), proposalId));

		        TypedQuery<Payment> tq = em.createQuery(cq);
		        List<Payment>paymentList = new ArrayList<Payment>();
		        try {
					paymentList = tq.getResultList();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		        catch (IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			List<PaymentModel> paymentModelList = new ArrayList<PaymentModel>();

			for (Payment entity : paymentList) {
				PaymentModel model = new PaymentModel();
				System.out.println("------------------Enity Return  Payment List>>-----------");
				System.out.println("------------------1>>-----------"+entity.getPayentMethod());
				System.out.println("------------------2>>-----------"+entity.getAmount());
				System.out.println("------------------3>>-----------"+entity.getPayDate());
				System.out.println("------------------4>>-----------"+entity.getProposal().getProposalId());
				model.setAmount(entity.getAmount());	
				model.setBank(entity.getPayentMethod());
				model.setProposal(entity.getProposal());
				model.setPaydate(entity.getPayDate());
				model.setProposal(entity.getProposal());
	            paymentModelList.add(model);

			}

			return paymentModelList;

		}

			
		}
		
		
		
		


	

	
	
	


