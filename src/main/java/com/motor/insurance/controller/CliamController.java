package com.motor.insurance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.model.ClaimModel;
import com.motor.insurance.service.ClaimListingService;
import com.motor.insurance.service.ClaimService;
import com.motor.insurance.service.ProposalListingService;
import com.motor.insurance.service.ProposalService;

@Named
@ViewScoped
public class CliamController {

	ClaimModel claimModel = new ClaimModel(); // create claim model object for save claim data
	Proposal proposal = new Proposal();
	HashMap<Integer, Integer> proposalIdList = new HashMap<Integer, Integer>(); // for show proposal list in combobox
	List<ClaimModel> claimList = new ArrayList<ClaimModel>();

	@Autowired
	ClaimListingService claimListingService;

	@Autowired
	ProposalListingService listingService; // FOR CHECK USER ID HAS PROPOSAL

	@Autowired
	ClaimService claimService;

	@Autowired
	ProposalService proposalService;


	    public void save() {
		System.out.println(	"--------------Claim Save Controller -------" + claimModel.getProposal().getProposalId());

		try {
			proposal = proposalService.findProposalById(claimModel.getProposal().getProposalId());
		} catch (NullPointerException e) {
			System.out.println("Null Claim Controller");
			e.printStackTrace();
		}
		if (proposal.getStatus().equalsIgnoreCase("paid")) {
			Date d1 = new Date();
			if (d1.after(proposal.getEndDate())) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Claim  is failed! " + "Your policy is expired ", ""));
			} else {
				claimModel.setProposal(proposal);
				claimService.save(claimModel);
				claimModel = new ClaimModel();
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Claim is  " + "Successfully Save", ""));

			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Sorry You cannot make a claim !  " + "You have not paid Yet!", ""));

		}
		claimModel = new ClaimModel();

	}
	    public String getClaimForm() {
	    	System.out.println("*******getClaimForm()********");
	    	claimList();
			return "claim.xhtml?faces-redirect=true";

		}
	    
	    public String  getClaimStatusCheckingForm() {
	    	System.out.println("*******getClaimStatusCheckingForm()********");
	    	claimList();
			return "claimStatusChecking.xhtml?faces-redirect=true";

		}
	   

	public  void claimList() {
		System.out.println("---------------Claim List Arrive------------");
		User user = new User();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		try {
			if (session != null) {
				int id = (int) session.getAttribute("id");
				user.setId(id);
				try {
					this.claimList = claimListingService.claimListing(user);
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				if (!claimList.isEmpty()) {
					proposalIdList = new HashMap<Integer, Integer>();
					for (int i = 0; i < claimList.size(); i++) {
						this.proposalIdList.put(claimList.get(i).getProposal().getProposalId(),
						claimList.get(i).getProposal().getProposalId());
					}
				}
			}

			else {
				claimList= new ArrayList<ClaimModel>();
			}
			
		} catch (NullPointerException e) {
			System.out.println("null pointer in cliam listing");
			claimList= new ArrayList<ClaimModel>();

		}

	}

	public String clear() {
		System.out.println("CLAEAR=================");
		claimModel = new ClaimModel();
		return "claim.xhtml?faces-redirect=true";
	}

	public void search() {
		System.out.println("search  Claim rrive =========>" + claimModel.getProposal().getProposalId());
		claimList = new ArrayList<ClaimModel>();
		if (claimModel.getProposal().getProposalId() != 0) {
			System.out.println("ID>>>>>>>>>>" + claimModel.getProposal().getProposalId());
			this.claimList = claimListingService.findClaimById(claimModel.getProposal());
		}
		else claimList();
	}

	public HashMap<Integer, Integer> getProposalIdList() {
		return proposalIdList;
	}

	public void setProposalIdList(HashMap<Integer, Integer> proposalIdList) {
		this.proposalIdList = proposalIdList;
	}

	public ClaimModel getClaimModel() {
		return claimModel;
	}

	public void setClaimModel(ClaimModel claimModel) {
		this.claimModel = claimModel;
	}

	public List<ClaimModel> getClaimList() {
		return claimList;
	}

	public void setClaimList(List<ClaimModel> claimList) {
		this.claimList = claimList;
	}

}
