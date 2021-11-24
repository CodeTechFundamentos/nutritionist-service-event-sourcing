package com.nutrix.command.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Professional Profile Id",name="professionalProfileId",required=true,example = "04e19ea0-d9e4-4fa2-8cc8-6b7adc47bb71")
    private String id;
    @Column(name ="professional_experience_description")
    @ApiModelProperty(notes = "Professional Profile description",name="professionalProfileId",required=true,example = "Egresado de la Universidad Nacional de San Marcos")
    private String professional_experience_description;
    @Column(name ="nutritionistId")
    @ApiModelProperty(notes = "Professional Profile nutritionistId",name="nutritionistId",required=true,example = "10293a832")
    private String nutritionistId;


}