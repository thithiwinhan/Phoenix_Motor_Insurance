package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.Proposal;
import com.motor.insurance.entity.User;
import com.motor.insurance.model.ClaimModel;
import com.motor.insurance.model.ProposalModel;

@Service
public interface ClaimListingService {

	List<ClaimModel> claimListing(User user);

	List<ClaimModel> findClaimById(Proposal proposal);

}
