package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer>{

}
