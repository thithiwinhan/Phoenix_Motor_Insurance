package com.motor.insurance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.model.PaymentModel;
import com.motor.insurance.model.ProposalModel;
import com.motor.insurance.service.PaymentListingService;
import com.motor.insurance.service.Paymentservice;
import com.motor.insurance.service.ProposalListingService;
import com.motor.insurance.service.ProposalService;
import com.motor.insurance.service.VehicleService;

@ViewScoped
@Named
public class PaymentController {

	@Autowired
	PaymentListingService paymentListingService;

	@Autowired
	Paymentservice paymentservice;

	@Autowired
	ProposalService proposalService;

	PaymentModel paymentModel = new PaymentModel();

	List<PaymentModel> paymentList = new ArrayList<PaymentModel>();

	Proposal proposal = new Proposal();
	
	List<ProposalModel> proposalList = new ArrayList<ProposalModel>(); // retrieve propsal data from database

	@Autowired
	ProposalListingService listingService;
	@Autowired
	ProposalController p;

	HashMap<Integer, Integer> proposalIdList = new HashMap<Integer, Integer>(); // for show proposal list in combobox
	
	Double amount ;
	
	

	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount=amount;
	}


	public void save() {

		System.out.println("--------------Payment Save Controller ---------"+ paymentModel.getProposal().getProposalId());
		boolean status = paymentservice.findStatusbyProposalId(paymentModel.getProposal().getProposalId());
		System.out.println("status of payment----->" + status);
		if (status == true) {
			    proposal = proposalService.findProposalById(paymentModel.getProposal().getProposalId());
			if (proposal.getStatus().equalsIgnoreCase("paid")) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"You Already paid \n" + "", ""));

			}
			else if (proposal.getStatus().equalsIgnoreCase("accept")) {
					paymentModel.setAmount(amount);
					paymentModel.setProposal(proposal);
					paymentservice.save(paymentModel);
					paymentModel = new PaymentModel();
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Your Payment is Successfully  " + "", ""));
			}
		} 
		else  {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Sorry! Your Payment is Failed " + "", ""));
		}
		 paymentModel = new PaymentModel();

	}

	
	public void paymentList() {
		User user = new User();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		try {
			   if (session!=null) {
				   int id = (int) session.getAttribute("id");
				   user.setId(id);
					try {
						this.paymentList = paymentListingService.paymentListing(user);
					    }
					catch (IndexOutOfBoundsException e) {
                          System.out.println( "Index Out of bound payment list");
                          e.printStackTrace();
					    }
			    }
			  else {
             System.out.println( "session is null" );
			   }
		} 
		catch (NullPointerException e) {
			System.out.println("payment list null arrive");
			this.paymentList= new ArrayList<PaymentModel>();
            
		}
        

	}
	
	public String  getPaymentListForm() {
    	System.out.println("*******getPaymentListForm()********");
		 paymentList();
		 return "paymentListing.xhtml?faces-redirect=true";
		
	}
	
	

	public String  getPaymentForm() {
		 p.proposalList();//add for get proposal id list
    	System.out.println("*******getPaymentForm()********");
		 paymentList();
		 return "payment.xhtml?faces-redirect=true";
		
	}
	
	
	/*
	 * public void search() {
	 * System.out.println("====Proposal Search Controller======" +
	 * paymentModel.getProposal().getProposalId()); paymentList = new
	 * ArrayList<PaymentModel>(); if (paymentModel.getProposal().getProposalId() !=
	 * 0) { paymentList = paymentListingService
	 * .findPaymentAmountByProposalId(paymentModel.getProposal().getProposalId());
	 * //paymentList=proposalService.findProposalById(paymentModel.getProposal().
	 * getProposalId()) try { if (!paymentList.isEmpty()) {
	 * amount=paymentList.get(0).getProposal().getPremium(); } else {
	 * System.out.println("empty payment list"); } } catch
	 * (IndexOutOfBoundsException e) {
	 * System.out.println("cannot find!.index out od bound!!!!!");
	 * e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 */
	

	public void search() {
		System.out.println("**********serach arrive in payment Controller**********");
		
		if (paymentModel.getProposal().getProposalId() != 0) {
			Proposal proposal = proposalService.findProposalById(paymentModel.getProposal().getProposalId());
			try {
					if (proposal!=null) {
						this.amount=proposal.getPremium();
					}
					else {
						System.out.println(" not found proposal by id");
					}
			} 
			catch (NullPointerException e) {
				this.amount=0.0;
					e.printStackTrace();
			}
		}
		else {
			this.amount=new Double(0.0);
		}
				
	}
	
	
	
	
	public List<ProposalModel> getProposalList() {
		return proposalList;
	}

	public void setProposalList(List<ProposalModel> proposalList) {
		this.proposalList = proposalList;
	}

	public HashMap<Integer, Integer> getProposalIdList() {
		return proposalIdList;
	}

	public void setProposalIdList(HashMap<Integer, Integer> proposalIdList) {
		this.proposalIdList = proposalIdList;
	}

	public PaymentModel getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(PaymentModel paymentModel) {
		this.paymentModel = paymentModel;
	}

	public List<PaymentModel> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentModel> paymentList) {
		this.paymentList = paymentList;
	}

}
