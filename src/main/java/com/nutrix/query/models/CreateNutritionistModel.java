package com.nutrix.query.models;

import lombok.Value;

import javax.persistence.Column;
import java.util.Date;

@Value
public class CreateNutritionistModel {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer cnpNumber;
    private Date createdAt;
}
