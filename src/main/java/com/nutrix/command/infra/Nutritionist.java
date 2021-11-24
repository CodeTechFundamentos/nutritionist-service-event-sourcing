package com.nutrix.command.infra;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="nutritionist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutritionist implements Serializable {

    @Id
    @ApiModelProperty(notes = "Nutritionist Id",name="nutritionistId",required=true,example = "04e19ea0-d9e4-4fa2-8cc8-6b7adc47bb71")
    private String id;
    @Column(name="username")
    @ApiModelProperty(notes = "Nutritionist username",name="username",required=true,example = "BillGuerrero")
    private String username;
    @Column(name="password")
    @ApiModelProperty(notes = "Nutritionist password",name="password",required=true,example = "password123")
    private String password;
    @Column(name="first_name")
    @ApiModelProperty(notes = "Nutritionist firstName",name="firstName",required=true,example = "Bill")
    private String firstName;
    @Column(name="last_name")
    @ApiModelProperty(notes = "Nutritionist lastName",name="lastName",required=true,example = "Guerrero")
    private String lastName;
    @Column(name="email")
    @ApiModelProperty(notes = "Nutritionist email",name="email",required=true,example = "billguerrero@hotmail.com")
    private String email;
    @Column(name="cnp_number")
    @ApiModelProperty(notes = "Nutritionist cnpNumber",name="cnpNumber",required=true,example = "7823994")
    private Integer cnpNumber;
    @Column(name="created_at")
    @ApiModelProperty(notes = "Nutritionist createdAt",name="createdAt",required=true,example = "2021-11-23T04:25:17.917+00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}