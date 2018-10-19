package com.cpsdb.declareserv.params.organiz;

public class JOrganizLogin {

    /**
     * @Description 注册账号
     */
    private String username;
    /**
     * @Description 返回token
     */
    private String token;

    public String getUsername() {
        return username;
    }

    public JOrganizLogin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getToken() {
        return token;
    }

    public JOrganizLogin setToken(String token) {
        this.token = token;
        return this;
    }
}
