package com.nutrix.command.api;

import com.nutrix.command.application.dto.ErrorResponseDto;
import com.nutrix.command.infra.ProfessionalProfile;
import com.nutrix.query.models.CreateProfessionalProfileModel;
import command.CreateProfessionalProfileC;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ProfessionalProfileResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/professional-profile")
@Api(tags="Professional Profile", value = "Servicio Web RESTFul de Professional Profile")
public class ProfessionalProfileCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ProfessionalProfileCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Event Sourcing Post
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Professional Profile", notes ="Método que registra un Professional Profile" )
    @ApiResponses({
            @ApiResponse(code=200, message = "La operación fue exitosa", response = ProfessionalProfile.class),
            @ApiResponse(code=201, message = "Professional Profiles creado", response = ProfessionalProfile.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para obtener la respuesta solicitada"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
            @ApiResponse(code=404, message = "Professional Profiles no creado")
    })
    public ResponseEntity<Object> insertProfessionalProfile(@Validated @RequestBody CreateProfessionalProfileModel profile){
        String id = UUID.randomUUID().toString();
        CreateProfessionalProfileC createProfessionalProfileC = new CreateProfessionalProfileC(
                id,
                profile.getProfessional_experience_description(),
                profile.getNutritionistId()
        );
        CompletableFuture<Object> future = commandGateway.send(createProfessionalProfileC);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new ErrorResponseDto(ex.getMessage());
            }
            return new ProfessionalProfileResult(
                    createProfessionalProfileC.getId(),
                    createProfessionalProfileC.getProfessional_experience_description(),
                    createProfessionalProfileC.getNutritionistId()
            );
        });
        try {
            Object response = futureResponse.get();
            if (response instanceof ProfessionalProfileResult) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}