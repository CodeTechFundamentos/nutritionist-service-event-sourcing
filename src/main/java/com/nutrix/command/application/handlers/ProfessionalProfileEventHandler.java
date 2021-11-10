package com.nutrix.command.application.handlers;

import com.nutrix.command.infra.IProfessionalProfileRepository;
import com.nutrix.command.infra.ProfessionalProfile;
import events.ProfessionalProfileCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("professional-profile")
public class ProfessionalProfileEventHandler {
    private final IProfessionalProfileRepository professionalProfileRepository;

    @Autowired
    public ProfessionalProfileEventHandler(IProfessionalProfileRepository professionalProfileRepository){
        this.professionalProfileRepository = professionalProfileRepository;
    }

    @EventHandler
    public void on(ProfessionalProfileCreatedEvent event){
        professionalProfileRepository.save(new ProfessionalProfile(
                        event.getId(),
                        event.getProfessional_experience_description(),
                        event.getNutritionistId()
        )
        );
    }
}
