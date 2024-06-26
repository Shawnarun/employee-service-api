package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestBranchDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseBranchDTO;

import java.util.List;

public interface BranchService {
    CommonResponseDTO create(RequestBranchDTO dto);

    List<ResponseBranchDTO> getAll();

    CommonResponseDTO delete(String branchId);
}
