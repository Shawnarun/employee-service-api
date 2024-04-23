package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.EmployeeService;
import com.developers.serviceApi.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = {"/create"} ,params = {"branchId","userTypeId"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestEmployeeDTO dto,
            @RequestParam String branchId,
            @RequestParam String userTypeId) throws SQLException {
        LOGGER.info("Resource creation request received");
        CommonResponseDTO responseData = employeeService.create(dto,branchId,userTypeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = {"/get-all"},params = {"employeeAvailability","employmentState","branchId","userTypeId"})
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String employeeAvailability,
            @RequestParam String employmentState,
            @RequestParam String branchId,
            @RequestParam String userTypeId

    ) throws SQLException {
        LOGGER.info("Request received for get all ");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        employeeService.getAll(employeeAvailability,employmentState,branchId,userTypeId)),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-by-id"},params = {"employeeId"})
    public ResponseEntity<StandardResponse> getById(
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("Request received");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        employeeService.getById(employeeId)),
                HttpStatus.OK
        );
    }

    @PutMapping(path = {"/change-state"},params = {"state","employeeId"})
    public ResponseEntity<StandardResponse> changeState(
            @RequestParam boolean state,
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("Request received");
        CommonResponseDTO responseData = employeeService.changeState(state,employeeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = {"/change-employment-state"},params = {"employmentState","employeeId"})
    public ResponseEntity<StandardResponse> changeEmploymentState(
            @RequestParam boolean employmentState,
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("Request received");
        CommonResponseDTO responseData = employeeService.changeEmploymentState(employmentState,employeeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

}
