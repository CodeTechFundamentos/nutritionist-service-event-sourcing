package com.nutrix.query.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Value
public class CreateNutritionistModel {
    @ApiModelProperty(notes = "Nutritionist Id",name="nutritionistId",required=true,example = "04e19ea0-d9e4-4fa2-8cc8-6b7adc47bb71")
    private String id;
    @ApiModelProperty(notes = "Nutritionist username",name="username",required=true,example = "BillGuerrero")
    private String username;
    @ApiModelProperty(notes = "Nutritionist password",name="password",required=true,example = "password123")
    private String password;
    @ApiModelProperty(notes = "Nutritionist firstName",name="firstName",required=true,example = "Bill")
    private String firstName;
    @ApiModelProperty(notes = "Nutritionist lastName",name="lastName",required=true,example = "Guerrero")
    private String lastName;
    @ApiModelProperty(notes = "Nutritionist email",name="email",required=true,example = "billguerrero@hotmail.com")
    private String email;
    @ApiModelProperty(notes = "Nutritionist cnpNumber",name="cnpNumber",required=true,example = "7823994")
    private Integer cnpNumber;
    @ApiModelProperty(notes = "Nutritionist createdAt",name="createdAt",required=true,example = "2021-11-23T04:25:17.917+00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
