package com.neo.identity.service.impl;



import com.neo.identity.entity.UmHybridUserRoleEntity;
import com.neo.identity.repository.share.UserRoleRepository;
import com.neo.identity.service.UmHybridUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmHybridUserRoleImpl implements UmHybridUserRoleService {


    private final UserRoleRepository userRoleRepository;

    public UmHybridUserRoleImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public List<UmHybridUserRoleEntity> getAllUserAndRole() {
        return userRoleRepository.findAll();
    }
}
