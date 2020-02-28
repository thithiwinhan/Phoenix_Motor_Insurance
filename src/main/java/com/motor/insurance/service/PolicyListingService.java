package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.User;
import com.motor.insurance.model.ProposalModel;

@Service
public interface PolicyListingService {

	List<ProposalModel> policyListing(User user);
	List<ProposalModel> findPolicyById(int getpID);

}
