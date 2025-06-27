package com.office_dress_shop.shopbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {
    public String email;
    public String password;
}
