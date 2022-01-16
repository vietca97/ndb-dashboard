package com.neo.identity.repository.share;


import com.neo.identity.entity.UmHybridUserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UmHybridUserRoleEntity,Long> {

    List<UmHybridUserRoleEntity> findAll();
}
