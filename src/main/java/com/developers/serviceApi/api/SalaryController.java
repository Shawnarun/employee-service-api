package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.EmployeeService;
import com.developers.serviceApi.service.SalaryService;
import com.developers.serviceApi.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/salaries")
public class SalaryController {

    private final SalaryService salaryService;
    private final Logger LOGGER = LoggerFactory.getLogger(SalaryController.class);

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping(path = {"/create"} ,params = {"employeeId"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestSalaryDTO dto,
            @RequestParam String employeeId) throws SQLException {
        LOGGER.info("Resource creation request received");
        CommonResponseDTO responseData = salaryService.create(dto,employeeId);
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
                        salaryService.getAll()),
                HttpStatus.OK
        );
    }
}
