package com.motor.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.insurance.entity.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer>{

}
