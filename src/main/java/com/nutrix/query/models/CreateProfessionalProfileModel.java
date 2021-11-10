package com.nutrix.query.models;

import lombok.Value;

import javax.persistence.Column;
import java.util.Date;

@Value
public class CreateProfessionalProfileModel {
    private String id;
    private String professional_experience_description;
    private String nutritionistId;
}
