package com.switchfully.eurderdb.admin.dto;

public class AdminDto {
    private Long id;
    private String username;
    private String password;

    public AdminDto() {
    }

    public AdminDto(String username, String password) {
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
