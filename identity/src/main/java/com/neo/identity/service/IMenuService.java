package com.neo.identity.service;


import com.neo.identity.entity.MenuEntity;

import java.util.List;
import java.util.Optional;

public interface IMenuService {
    List<MenuEntity> findAllMenu();
    void save(MenuEntity menuEntity);
    MenuEntity update(MenuEntity menuEntity);
    void deleteById(Long id);
    Optional<MenuEntity> findById(Long id);
}
