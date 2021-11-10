package com.nutrix.query.handlers;
import com.nutrix.command.infra.INutritionistRepository;
import com.nutrix.command.infra.Nutritionist;
import com.nutrix.query.models.CreateNutritionistModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queries.GetNutritionistsQuery;
import result.NutritionistResult;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class NutritionistQueryHandler {
    private final INutritionistRepository nutritionistRepository;

    @Autowired
    public NutritionistQueryHandler(INutritionistRepository nutritionistRepository) {
        this.nutritionistRepository = nutritionistRepository;
    }

    @QueryHandler
    public List<NutritionistResult> handle(GetNutritionistsQuery query) {
        List<Nutritionist> nutritionists = nutritionistRepository.findAll();

        List<NutritionistResult> nutritionistModels =
                nutritionists.stream()
                        .map(nutritionist -> new NutritionistResult(
                                nutritionist.getId(),
                                nutritionist.getFirstName(),
                                nutritionist.getLastName(),
                                nutritionist.getUsername(),
                                nutritionist.getPassword(),
                                nutritionist.getEmail(),
                                nutritionist.getCnpNumber(),
                                nutritionist.getCreatedAt()
                        )).collect(Collectors.toList());
        return nutritionistModels;
    }
}
