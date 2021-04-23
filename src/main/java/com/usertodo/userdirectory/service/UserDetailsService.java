package com.usertodo.userdirectory.service;

import com.usertodo.userdirectory.model.UserDetails;
import com.usertodo.userdirectory.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    //Method to get all user details enter by logged user
    public List<UserDetails> getAllUserDetails(Integer userId){

        return userDetailsRepository.getAllUserDetails(userId);
    }

    //Method to create a new entry
    public void createDetails(UserDetails newEntry){
        userDetailsRepository.createDetails(newEntry);
    }

    //Method to delete a entry
    public void deleteDetail(Integer userId){
        userDetailsRepository.deleteDetails(userId);
    }

    //Method to get a entry
    public UserDetails getPost(Integer userId) {
        return userDetailsRepository.getPost(userId);
    }


}
