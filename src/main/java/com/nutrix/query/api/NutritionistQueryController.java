package com.nutrix.query.api;

import com.nutrix.command.infra.Nutritionist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.GetNutritionistsQuery;
import result.NutritionistResult;


import java.util.List;

@RestController
@RequestMapping("/nutritionist")
@Api(tags="Nutritionist", value = "Servicio Web RESTFul de Nutritionist")
public class NutritionistQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public NutritionistQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de todos los Nutritionist", notes ="Método que busca a todos los Nutritionists" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Nutritionists encontrados"),
            @ApiResponse(code=404, message = "Nutritionists no encontrados")
    })
    public ResponseEntity<List<NutritionistResult>> getAll(){
        try{
            GetNutritionistsQuery getNutritionistsQuery = new GetNutritionistsQuery();
            List<NutritionistResult> nutritionists = queryGateway.query(getNutritionistsQuery,
                    ResponseTypes.multipleInstancesOf(NutritionistResult.class))
                    .join();
            return new ResponseEntity<>(nutritionists, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
