package com.cpsdb.declareserv.params.declarer;

public class JExamineeLogin {

    private String token;
    private Integer score;
    private String name;

    public String getToken() {
        return token;
    }

    public JExamineeLogin setToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public JExamineeLogin setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getName() {
        return name;
    }

    public JExamineeLogin setName(String name) {
        this.name = name;
        return this;
    }
}
