package com.nutrix.command.domain;

import com.nutrix.command.application.Notification;
import command.CreateNutritionistC;
import command.CreateProfessionalProfileC;
import events.NutritionistCreatedEvent;
import events.ProfessionalProfileCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import javax.persistence.Column;
import java.util.Date;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ProfessionalProfileA {
    @AggregateIdentifier
    private String id;
    private String professional_experience_description;
    private String nutritionistId;

    public ProfessionalProfileA(){
    }

    @CommandHandler
    public ProfessionalProfileA(CreateProfessionalProfileC createProfessionalProfileC){
        Notification notification = validateProfessionalProfile(createProfessionalProfileC);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        ProfessionalProfileCreatedEvent event = new ProfessionalProfileCreatedEvent(
                createProfessionalProfileC.getId(),
                createProfessionalProfileC.getProfessional_experience_description(),
                createProfessionalProfileC.getNutritionistId()
        );
        apply(event);
    }

    private Notification validateProfessionalProfile(CreateProfessionalProfileC createProfessionalProfileC) {
        Notification notification = new Notification();
        validateProfessionalProfileId(createProfessionalProfileC.getId(), notification);
        return notification;
    }

    private void validateProfessionalProfileId(String profileId, Notification notification) {
        if (profileId == null) {
            notification.addError("Professional Profile id is missing");
        }
    }

    //Event Sourcing Handlers

    @EventSourcingHandler
    public void on(ProfessionalProfileCreatedEvent professionalProfileCreatedEvent){
        this.id = professionalProfileCreatedEvent.getId();
        this.professional_experience_description = professionalProfileCreatedEvent.getProfessional_experience_description();
        this.nutritionistId = professionalProfileCreatedEvent.getNutritionistId();

    }
}
