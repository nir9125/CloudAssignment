package com.stackroute.JWTAuthApp.repository;

import com.stackroute.JWTAuthApp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserProfile,String> {
    UserProfile findByMailIdAndPassword(String mailid, String password);
}
