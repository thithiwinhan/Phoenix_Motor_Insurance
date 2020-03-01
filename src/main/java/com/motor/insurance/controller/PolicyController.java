package com.motor.insurance.controller;

import java.util.ArrayList;
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
import com.motor.insurance.model.ProposalModel;
import com.motor.insurance.service.PolicyListingService;
import com.motor.insurance.service.ProposalListingService;
import com.motor.insurance.service.ProposalService;

@Named
@ViewScoped
public class PolicyController {

	ProposalModel policyModel = new ProposalModel(); // create propsal model object for save proposal data from ui
	List<ProposalModel> policyList = new ArrayList<ProposalModel>(); // retrieve propsal data from database

	
	@Autowired
	ProposalService proposalService;

	
	@Autowired
	PolicyListingService policylistingService;

	public void proposalSave() {
		System.out.println("user" + policyModel.getpID());
		System.out.println("arrive");
		proposalService.save(policyModel);
	}

    public void policyList() {
	 System.out.println("policy List Controller arrive");
	 User user = new User(); FacesContext facesContext =
	 FacesContext.getCurrentInstance();
	 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
		try {
				if (session!=null) {
					int id = (int) session.getAttribute("id");
					user.setId(id);
					try {
						this.policyList = policylistingService.policyListing(user);
					    } 
						catch (IndexOutOfBoundsException e) {
							this.policyList= new ArrayList<ProposalModel>();
					    }
				}
				else{
					this.policyList = new ArrayList<ProposalModel>();

				    }
		   } 
			catch (Exception e) {
				System.out.println("Null In Policy Listing!!!!");

			}
		

	}
	 
   public String getPolicyListForm() {
   	System.out.println("*******getPolicyListForm()********");
	   policyList();
	return "policyList.xhtml?faces-redirect=true";
	
} 
	 
	
	public void search() {
		policyList = new ArrayList<ProposalModel>();
		if (policyModel.getpID() != 0) {
			System.out.println(" Plicy Search ID>>>>>>>>>>" + policyModel.getpID());
			this.policyList = policylistingService.findPolicyById(policyModel.getpID());
		}

		else policyList(); // if nothing choose ,show all
	}

	public ProposalModel getPolicyModel() {
		return policyModel;
	}

	public void setPolicyModel(ProposalModel policyModel) {
		this.policyModel = policyModel;
	}

	public List<ProposalModel> getPolicyList() {
		return policyList;
	}

	public void setPolicyList(List<ProposalModel> policyList) {
		this.policyList = policyList;
	}

	
}
