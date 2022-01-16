package com.neo.shared.service.impl;




import com.neo.shared.entity.UmHybridUserRoleEntity;
import com.neo.shared.repository.UserRoleRepository;
import com.neo.shared.service.UmHybridUserRoleService;
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
