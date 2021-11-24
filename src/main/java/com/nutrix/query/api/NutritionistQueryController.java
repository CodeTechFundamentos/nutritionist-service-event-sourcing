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
import org.springframework.web.bind.annotation.PathVariable;
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
            @ApiResponse(code=200, message = "La operación fue exitosa", response = Nutritionist.class),
            @ApiResponse(code=201, message = "Nutritionists encontrados", response = Nutritionist.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para obtener la respuesta solicitada"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
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

    //get nutritionist by id
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de un Nutritionist", notes ="Método que busca a un Nutritionist" )
    @ApiResponses({
            @ApiResponse(code=200, message = "La operación fue exitosa", response = Nutritionist.class),
            @ApiResponse(code=201, message = "Nutritionist encontrados", response = Nutritionist.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para obtener la respuesta solicitada"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
            @ApiResponse(code=404, message = "Nutritionist no encontrados")
    })
    public ResponseEntity<NutritionistResult> getById(@PathVariable  String id){
        try{
            GetNutritionistsQuery getNutritionistsQuery = new GetNutritionistsQuery();
            List<NutritionistResult> nutritionists = queryGateway.query(getNutritionistsQuery,
                    ResponseTypes.multipleInstancesOf(NutritionistResult.class))
                    .join();
            for (NutritionistResult nutritionist : nutritionists) {
                if (nutritionist.getId().equals(id)) {
                    return new ResponseEntity<>(nutritionist, HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
