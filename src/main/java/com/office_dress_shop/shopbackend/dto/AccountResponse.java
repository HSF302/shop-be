package com.office_dress_shop.shopbackend.dto;

import com.office_dress_shop.shopbackend.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountResponse {
    public String email;
    public String password;
    public String name;
    public String phoneNumber;
    public Role role;
    public String address;
    public String token;

}
