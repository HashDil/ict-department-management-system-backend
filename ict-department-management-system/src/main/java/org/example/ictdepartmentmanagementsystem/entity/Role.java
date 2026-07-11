package org.example.ictdepartmentmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    STUDENT,
    ADMIN;

    @JsonCreator
    public static Role fromString(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}
