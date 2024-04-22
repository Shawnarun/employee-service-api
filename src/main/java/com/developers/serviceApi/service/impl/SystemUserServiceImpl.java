package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.entity.SystemUser;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.SystemUserRepo;
import com.developers.serviceApi.service.SystemUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepo systemUserRepo;

    public SystemUserServiceImpl(SystemUserRepo systemUserRepo) {
        this.systemUserRepo = systemUserRepo;

    }

    @Override
    public boolean login(String userName, String password) {
        Optional<SystemUser> user= systemUserRepo.findByUserName(userName);
        if(user.isEmpty()){
            throw new EntryNotFoundException("User Not Found!!!!");
        }
        return user.get().getPassword().equals(password);
    }
}
