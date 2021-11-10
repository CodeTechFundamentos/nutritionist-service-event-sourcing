package com.nutrix.command.infra;

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
    private String id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="cnp_number")
    private Integer cnpNumber;
    @Column(name="created_at")
    private Date createdAt;

}