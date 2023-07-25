package com.stackroute.JWTAuthApp.services;

import com.stackroute.JWTAuthApp.model.UserProfile;

public interface UserServiceDao {
    UserProfile registerUser(UserProfile profile);
    boolean validateUser(UserProfile profile);
}
