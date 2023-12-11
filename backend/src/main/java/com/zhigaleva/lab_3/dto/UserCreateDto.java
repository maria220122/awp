package com.zhigaleva.lab_3.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String username;
    private String name;
    private String password;
}
