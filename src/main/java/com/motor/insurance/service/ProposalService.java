package com.motor.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.PolicyHolder;
import com.motor.insurance.entity.Proposal;
import com.motor.insurance.model.ProposalModel;

@Service
public interface ProposalService {

	void save(ProposalModel proposal);

	//Proposal findProposalById(int i);

	ProposalModel updateProposal(ProposalModel proposal);

	boolean findStatusbyProposalId(int getpID);
	//Proposal findProposalById(ProposalModel proposal);



	Proposal findProposalById(int proposalId);

	boolean delete(ProposalModel proposal);


	List<PolicyHolder> findAllProposaLOwnerActive();

	String findId();

	


}
