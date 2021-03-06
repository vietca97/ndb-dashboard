package com.neo.dashboard.repository;

import com.neo.dashboard.entity.MenuEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity,Long> {

    List<MenuEntity> findAll();
    Optional<MenuEntity> findById(Long id);
}
