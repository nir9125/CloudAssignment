package com.stackroute.JWTAuthApp.services;

import com.stackroute.JWTAuthApp.model.UserProfile;
import com.stackroute.JWTAuthApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceDao {
@Autowired
    UserRepo repo;

    @Override
    public UserProfile registerUser(UserProfile profile) {
        return repo.save(profile);
    }

//    checking the user mail and password matched or not in db
    @Override
    public boolean validateUser(UserProfile profile) {
        UserProfile profile1 = repo.findByMailIdAndPassword(profile.getMailId(), profile.getPassword());
        if(profile1 == null){
            return false;
        }
        else{
            return true;
        }
    }
}
