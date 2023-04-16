package com.bora.validatetest.model;

import lombok.Getter;

@Getter
public enum Role {
    USER("User"),
    ADMIN("Admin");
    private final String value;
    private Role(String value){
        this.value = value;
    }

}
