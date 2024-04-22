package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseBranchDTO;

import java.util.List;

public interface BranchService {
    CommonResponseDTO create(RequestUserTypeDTO dto);

    List<ResponseBranchDTO> getAll();

    CommonResponseDTO delete(String branchId);
}
