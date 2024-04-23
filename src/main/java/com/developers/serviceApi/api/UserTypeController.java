package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.UserTypeService;
import com.developers.serviceApi.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/user-types")
@CrossOrigin
public class UserTypeController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserTypeController.class);
    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }


    @PostMapping(path = {"/create"} )
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestUserTypeDTO dto) throws SQLException {
        LOGGER.info("Resource creation request received");
        CommonResponseDTO responseData = userTypeService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = {"/get-all"})
    public ResponseEntity<StandardResponse> getAll(
    ) throws SQLException {
        LOGGER.info("Request received for get all ");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        userTypeService.getAll()),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = {"/{userTypeId}"})
    public ResponseEntity<StandardResponse> delete(
            @PathVariable String userTypeId ){
        LOGGER.info("Delete request received {}", userTypeId);
        CommonResponseDTO responseData = userTypeService.delete(userTypeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }



}
