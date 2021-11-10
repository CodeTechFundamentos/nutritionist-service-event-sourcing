package com.nutrix.command.domain;

import java.util.Date;

import com.nutrix.command.application.Notification;
import command.CreateNutritionistC;
import events.NutritionistCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

@Aggregate
public class NutritionistA {
    @AggregateIdentifier
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer cnpNumber;
    private Date createdAt;

    public NutritionistA(){
    }

    @CommandHandler
    public NutritionistA(CreateNutritionistC createNutritionistC){
        Notification notification = validateNutritionist(createNutritionistC);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        NutritionistCreatedEvent event = new NutritionistCreatedEvent(
                createNutritionistC.getId(),
                createNutritionistC.getFirstName(),
                createNutritionistC.getLastName(),
                createNutritionistC.getUsername(),
                createNutritionistC.getPassword(),
                createNutritionistC.getEmail(),
                createNutritionistC.getCnpNumber(),
                createNutritionistC.getCreatedAt()
        );
        apply(event);
    }

    private Notification validateNutritionist(CreateNutritionistC createNutritionistC) {
        Notification notification = new Notification();
        validateNutritionistId(createNutritionistC.getId(), notification);
        return notification;
    }

    private void validateNutritionistId(String nutritionistId, Notification notification) {
        if (nutritionistId == null) {
            notification.addError("Nutritionist id is missing");
        }
    }

    //Event Sourcing Handlers

    @EventSourcingHandler
    public void on(NutritionistCreatedEvent nutritionistCreatedEvent){
        this.id = nutritionistCreatedEvent.getId();
        this.firstName = nutritionistCreatedEvent.getFirstName();
        this.lastName = nutritionistCreatedEvent.getLastName();
        this.username = nutritionistCreatedEvent.getUsername();
        this.password = nutritionistCreatedEvent.getLastName();
        this.email=nutritionistCreatedEvent.getEmail();
        this.cnpNumber = nutritionistCreatedEvent.getCnpNumber();
        this.createdAt = nutritionistCreatedEvent.getCreatedAt();
    }
}
