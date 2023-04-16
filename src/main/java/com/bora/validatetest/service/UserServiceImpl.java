package com.bora.validatetest.service;

import com.bora.validatetest.model.Request;
import com.bora.validatetest.model.User;
import com.bora.validatetest.repository.RequestRepository;
import com.bora.validatetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RequestRepository requestRepository;
    @Override
    public void saveUser(User user){
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    @Override
    public List<Object> isUserPresent(User user){
        boolean userExist = false;
        String message = null;
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserEmail.isPresent()){
            userExist = true;
            message = "Email already present!";
        }
        Optional<User> existingUserMobile = userRepository.findByMobile(user.getMobile());
        if(existingUserMobile.isPresent()){
            userExist = true;
            message = "Mobile already present!";
        }
        if (existingUserEmail.isPresent()&&existingUserMobile.isPresent()){
            message = "Email and Mobile Number both already present!";
        }
        System.out.println("existingUserEmail.isPresent() - "+existingUserEmail.isPresent()+"existingUserMobile.isPresent() - "+existingUserMobile.isPresent());
        return Arrays.asList(userExist,message);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND", email)));
    }

}
