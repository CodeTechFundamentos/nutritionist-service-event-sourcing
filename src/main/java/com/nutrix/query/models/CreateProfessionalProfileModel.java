package com.nutrix.query.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.persistence.Column;
import java.util.Date;

@Value
public class CreateProfessionalProfileModel {

    @ApiModelProperty(notes = "Professional Profile Id",name="professionalProfileId",required=true,example = "04e19ea0-d9e4-4fa2-8cc8-6b7adc47bb71")
    private String id;
    @ApiModelProperty(notes = "Professional Profile description",name="professionalProfileId",required=true,example = "Egresado de la Universidad Nacional de San Marcos")
    private String professional_experience_description;
    @ApiModelProperty(notes = "Professional Profile nutritionistId",name="nutritionistId",required=true,example = "10293a832")
    private String nutritionistId;
}
