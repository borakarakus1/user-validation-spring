package com.bora.validatetest.service;

import com.bora.validatetest.model.Request;
import com.bora.validatetest.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
    //public ResponseEntity<?> createRequest(Request request);
}
