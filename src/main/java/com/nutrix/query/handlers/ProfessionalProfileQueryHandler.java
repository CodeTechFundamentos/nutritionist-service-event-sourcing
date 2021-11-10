package com.nutrix.query.handlers;
import com.nutrix.command.infra.IProfessionalProfileRepository;

import com.nutrix.command.infra.ProfessionalProfile;
import com.nutrix.query.models.CreateProfessionalProfileModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queries.GetProfessionalProfilesQuery;
import result.ProfessionalProfileResult;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessionalProfileQueryHandler {
    private final IProfessionalProfileRepository professionalProfileRepository;

    @Autowired
    public ProfessionalProfileQueryHandler(IProfessionalProfileRepository professionalProfileRepository) {
        this.professionalProfileRepository = professionalProfileRepository;
    }

    @QueryHandler
    public List<ProfessionalProfileResult> handle(GetProfessionalProfilesQuery query) {
        List<ProfessionalProfile> professionalProfiles = professionalProfileRepository.findAll();

        List<ProfessionalProfileResult> professionalProfileModels =
                professionalProfiles.stream()
                        .map(professionalProfile -> new ProfessionalProfileResult(
                                professionalProfile.getId(),
                                professionalProfile.getProfessional_experience_description(),
                                professionalProfile.getNutritionistId()
                        )).collect(Collectors.toList());
        return professionalProfileModels;
    }
}
