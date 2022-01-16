package com.neo.shared.repository;



import com.neo.shared.entity.UmHybridUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UmHybridUserRoleEntity,Long> {

    List<UmHybridUserRoleEntity> findAll();
}
