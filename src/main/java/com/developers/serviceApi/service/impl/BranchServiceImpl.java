package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.BranchDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseBranchDTO;
import com.developers.serviceApi.entity.Branch;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.BranchRepo;
import com.developers.serviceApi.service.BranchService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.BranchMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private final Generator generator;
    private final BranchRepo branchRepo;
    private final BranchMapper branchMapper;

    public BranchServiceImpl(Generator generator, BranchRepo branchRepo, BranchMapper branchMapper) {
        this.generator = generator;
        this.branchRepo = branchRepo;
        this.branchMapper = branchMapper;
    }

    @Override
    public CommonResponseDTO create(RequestUserTypeDTO dto) {
        //--------------------------------------------------------------------------------------------------------------
        String prefix="SAPI-B-";
        String propertyId = generator.generateNewId(prefix,branchRepo.findLastId(prefix,prefix.length()+1));
        //---------------------------------------------------------------------

        BranchDTO branchDTO= new BranchDTO(
                propertyId,
                dto.getUserTypeName(),
                true
        );
        branchRepo.save(branchMapper.toBranch(branchDTO));
        return new CommonResponseDTO(201, " saved!", true, new ArrayList<>());
    }

    @Override
    public List<ResponseBranchDTO> getAll() {
        List<Branch> list = branchRepo.findAll();
        ArrayList<ResponseBranchDTO> arrayList = new ArrayList<>();

        for(Branch u :list){
            arrayList.add(new ResponseBranchDTO(
                    u.getBranchId(),
                    u.getBranchName(),
                    u.isActiveState()
            ));
        }
        return arrayList;
    }

    @Override
    public CommonResponseDTO delete(String branchId) {
        Optional<Branch> branch = branchRepo.findById(branchId);
        if(branch.isEmpty()){
            throw new EntryNotFoundException("Branch Not Found");
        }
        branchRepo.delete(branch.get());
        return new CommonResponseDTO(204, "Record Deleted!",
                branchId, new ArrayList<>());
    }

}
