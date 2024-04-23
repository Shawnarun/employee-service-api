package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestBranchDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.BranchService;
import com.developers.serviceApi.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/branch")
@CrossOrigin
public class BranchController {
    private final Logger LOGGER = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping(path = {"/create"} )
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestBranchDTO dto) throws SQLException {
        LOGGER.info("Resource creation request received");
        CommonResponseDTO responseData = branchService.create(dto);
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
                        branchService.getAll()),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = {"/{branchId}"})
    public ResponseEntity<StandardResponse> delete(
            @PathVariable String branchId ){
        LOGGER.info("Delete request received {}", branchId);
        CommonResponseDTO responseData = branchService.delete(branchId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }
}
