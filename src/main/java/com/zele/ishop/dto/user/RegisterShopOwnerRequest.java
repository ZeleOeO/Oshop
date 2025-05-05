package com.zele.ishop.dto.user;

import lombok.Data;

@Data
public class RegisterShopOwnerRequest {
    private String username;
    private String email;
    private String password;
}
