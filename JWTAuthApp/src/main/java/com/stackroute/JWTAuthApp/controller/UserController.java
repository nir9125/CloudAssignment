package com.stackroute.JWTAuthApp.controller;

import com.stackroute.JWTAuthApp.model.UserProfile;
import com.stackroute.JWTAuthApp.services.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl service ;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserProfile profile){
        return new ResponseEntity<UserProfile>(service.registerUser(profile), HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody UserProfile profile){
        boolean result = service.validateUser(profile);
        if(result){
       String  token = generateToken(profile);
            HashMap hashMap = new HashMap();
            hashMap.put("token",token);
            return new ResponseEntity<HashMap>(hashMap,HttpStatus.OK);

        }
        else {
            return new ResponseEntity<String>("Invalid Credentails", HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateToken(UserProfile profile){
        return Jwts.builder().setSubject(profile.getUserName())
                .setAudience(profile.getMailId())
                .setAudience("sachin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+30000))
                .signWith(SignatureAlgorithm.HS256,"cgidemo").compact();
    }

}
