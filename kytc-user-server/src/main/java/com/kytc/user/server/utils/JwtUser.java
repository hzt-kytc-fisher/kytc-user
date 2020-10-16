package com.kytc.user.server.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtUser implements Serializable {
    private Integer id;
    private String username;
}