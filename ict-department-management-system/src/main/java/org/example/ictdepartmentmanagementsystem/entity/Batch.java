package org.example.ictdepartmentmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Batch {
    BATCH_2021,
    BATCH_2022,
    BATCH_2023,
    BATCH_2024;

    @JsonCreator
    public static Batch fromString(String value) {
        return Batch.valueOf(value.toUpperCase());
    }
}
