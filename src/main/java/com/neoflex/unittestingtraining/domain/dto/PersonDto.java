package com.neoflex.unittestingtraining.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
