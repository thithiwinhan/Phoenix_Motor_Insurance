package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.Payment;
import com.motor.insurance.entity.Proposal;

@Repository
public interface ProposalDao extends JpaRepository<Proposal, Integer> {


}
