package com.waiterraterclient.dto;

import java.math.BigDecimal;

public class ServerScoreDTO {
    private BigDecimal score;
    private String firstName;
    private String lastName;

    public ServerScoreDTO(BigDecimal score, String firstName, String lastName) {
        this.score = score;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ServerScoreDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}