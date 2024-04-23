package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.SalaryDTO;
import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseUserTypeDTO;
import com.developers.serviceApi.entity.Employee;
import com.developers.serviceApi.entity.Salary;
import com.developers.serviceApi.entity.UserType;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.EmployeeRepo;
import com.developers.serviceApi.repo.SalaryRepo;
import com.developers.serviceApi.service.SalaryService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.EmployeeMapper;
import com.developers.serviceApi.util.mapper.SalaryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final Generator generator;
    private final SalaryRepo salaryRepo;
    private final SalaryMapper salaryMapper;
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    public SalaryServiceImpl(Generator generator, SalaryRepo salaryRepo, SalaryMapper salaryMapper, EmployeeRepo employeeRepo, EmployeeMapper employeeMapper) {
        this.generator = generator;
        this.salaryRepo = salaryRepo;
        this.salaryMapper = salaryMapper;
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public CommonResponseDTO create(RequestSalaryDTO dto, String employeeId) {
        Optional<Employee> employee= employeeRepo.findById(employeeId);
        if(employee.isEmpty()){
            throw new EntryNotFoundException("Employee Not Found");
        }
        //--------------------------------------------------------------------------------------------------------------
        String prefix="SAPI-S-";
        String propertyId = generator.generateNewId(prefix,salaryRepo.findLastId(prefix,prefix.length()+1));
        //---------------------------------------------------------------------

        SalaryDTO salaryDTO= new SalaryDTO(
            propertyId,
                dto.getMonth(),
                dto.getAmount(),
                true,
                employeeMapper.toEmployeeDTO(employee.get())
        );
        salaryRepo.save(salaryMapper.toSalary(salaryDTO));
        return new CommonResponseDTO(200,"changed",employeeId,new ArrayList<>());

    }

    @Override
    public List<ResponseSalaryDTO> getAll(String searchText) {
        List<Salary> list = salaryRepo.findBySearchText(searchText);
        ArrayList<ResponseSalaryDTO> arrayList = new ArrayList<>();

        for(Salary u :list){
            arrayList.add(new ResponseSalaryDTO(
                   u.getSalaryId(),
                    u.getMonth(),
                    u.getAmount(),
                    u.isPaidStatus(),
                    u.getEmployee().getEmployeeId()


            ));
        }
        return arrayList;
    }

}
