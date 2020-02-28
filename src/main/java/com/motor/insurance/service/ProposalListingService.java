package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.User;
import com.motor.insurance.model.ProposalModel;

@Service
public interface ProposalListingService {

	List<ProposalModel> proposalListing(User user);
	List<ProposalModel> findProposalById(int getpID);

}
