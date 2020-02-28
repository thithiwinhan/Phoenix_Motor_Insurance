package com.motor.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motor.insurance.model.PaymentModel;

@Service
public interface Paymentservice {

	void save(PaymentModel paymentModel);
  Boolean findStatusbyProposalId(int proposalId); //check status is accept in proposal table to allow payment


	
}
