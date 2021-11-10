package com.nutrix.command.application.handlers;


import com.nutrix.command.infra.INutritionistRepository;
import com.nutrix.command.infra.Nutritionist;
import events.NutritionistCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("nutritionist")
public class NutritionistEventHandler {
    private final INutritionistRepository nutritionistRepository;

    @Autowired
    public NutritionistEventHandler(INutritionistRepository nutritionistRepository){
        this.nutritionistRepository = nutritionistRepository;
    }

    @EventHandler
    public void on(NutritionistCreatedEvent event){
        nutritionistRepository.save(new Nutritionist(
                        event.getId(),
                        event.getFirstName(),
                        event.getLastName(),
                        event.getUsername(),
                        event.getPassword(),
                        event.getEmail(),
                        event.getCnpNumber(),
                        event.getCreatedAt()
        )
        );
    }
}
