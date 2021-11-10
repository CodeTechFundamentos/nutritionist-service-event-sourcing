package com.nutrix.query.api;

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
import queries.GetProfessionalProfilesQuery;
import result.ProfessionalProfileResult;

import java.util.List;

@RestController
@RequestMapping("/professional-profile")
@Api(tags="Professional Profile", value = "Servicio Web RESTFul de Professional Profile")
public class ProfessionalProfileQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public ProfessionalProfileQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de todos los Professional Profiles", notes ="Método que busca a todos los Professional Profiles" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Professional Profiles encontrados"),
            @ApiResponse(code=404, message = "Professional Profiles no encontrados")
    })
    public ResponseEntity<List<ProfessionalProfileResult>> getAll(){
        try{
            GetProfessionalProfilesQuery getProfessionalProfilesQuery = new GetProfessionalProfilesQuery();
            List<ProfessionalProfileResult> professionalProfiles = queryGateway.query(getProfessionalProfilesQuery,
                    ResponseTypes.multipleInstancesOf(ProfessionalProfileResult.class))
                    .join();
            return new ResponseEntity<>(professionalProfiles, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
