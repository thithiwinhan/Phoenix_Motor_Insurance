package com.motor.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.motor.insurance.model.UserModel;

@Service
public interface UserService {

	void createUser(UserModel userModel);

	List<UserModel> findUserById(int userId);

	void updateProfile(UserModel userModel);

	boolean searchByUserEmail(UserModel userModel);


	List<UserModel> checkAuthentication(UserModel userModel);

}
