package com.switchfully.eurderdb.admin.dto;

import jakarta.persistence.Column;

public class CreateAdminDto {
    private Long id;
    private String username;
    private String password;

    public CreateAdminDto() {
    }

    public CreateAdminDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
