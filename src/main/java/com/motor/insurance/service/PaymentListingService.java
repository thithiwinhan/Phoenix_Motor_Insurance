package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.entity.User;
import com.motor.insurance.model.ClaimModel;
import com.motor.insurance.model.PaymentModel;
import com.motor.insurance.model.ProposalModel;

@Service
public interface PaymentListingService {

	List<PaymentModel> paymentListing(User user);

	List<PaymentModel> findPaymentAmountByProposalId(int proposalId);

}
