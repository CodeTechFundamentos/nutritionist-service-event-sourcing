package com.nutrix.command.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "professional_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalProfile implements Serializable {

    @Id
    private String id;
    @Column(name ="professional_experience_description")
    private String professional_experience_description;
    @Column(name ="nutritionistId")
    private String nutritionistId;


}