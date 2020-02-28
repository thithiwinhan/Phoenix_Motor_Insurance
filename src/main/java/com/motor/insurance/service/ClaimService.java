package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.Claim;
import com.motor.insurance.model.ClaimModel;


@Service
public interface ClaimService {

	void save(ClaimModel claimModel);

	List<ClaimModel> findClaimbyProposalId(int proposalId);

}
