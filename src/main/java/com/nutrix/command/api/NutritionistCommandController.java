package com.nutrix.command.api;

import com.nutrix.command.application.dto.ErrorResponseDto;

import com.nutrix.command.infra.Nutritionist;
import com.nutrix.query.models.CreateNutritionistModel;
import command.CreateNutritionistC;
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
import result.NutritionistResult;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/nutritionist")
@Api(tags="Nutritionist", value = "Servicio Web RESTFul de Nutritionist")
public class NutritionistCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public NutritionistCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Event Sourcing Post
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Recipe de un Nutritionist", notes ="Método que registra un Nutritionist" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionist creado"),
            @ApiResponse(code=404, message = "Nutritionist no creado")
    })
    public ResponseEntity<Object> insertNutritionist(@Validated @RequestBody CreateNutritionistModel nutritionist){
        String id = UUID.randomUUID().toString();
        CreateNutritionistC createNutritionistC = new CreateNutritionistC(
                id,
                nutritionist.getFirstName(),
                nutritionist.getLastName(),
                nutritionist.getUsername(),
                nutritionist.getPassword(),
                nutritionist.getEmail(),
                nutritionist.getCnpNumber(),
                nutritionist.getCreatedAt()
        );
        CompletableFuture<Object> future = commandGateway.send(createNutritionistC);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new ErrorResponseDto(ex.getMessage());
            }
            return new NutritionistResult(
                    createNutritionistC.getId(),
                    createNutritionistC.getFirstName(),
                    createNutritionistC.getLastName(),
                    createNutritionistC.getUsername(),
                    createNutritionistC.getPassword(),
                    createNutritionistC.getEmail(),
                    createNutritionistC.getCnpNumber(),
                    createNutritionistC.getCreatedAt()
            );
        });
        try {
            Object response = futureResponse.get();
            if (response instanceof NutritionistResult) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}