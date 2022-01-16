package com.neo.identity.service.impl;


import com.neo.identity.entity.MenuEntity;
import com.neo.identity.repository.identity.MenuRepository;
import com.neo.identity.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements IMenuService {


    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuEntity> findAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public void save(MenuEntity menuEntity) {
        menuRepository.save(menuEntity);
    }

    @Override
    public MenuEntity update(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }

    @Override
    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Optional<MenuEntity> findById(Long id) {
        return menuRepository.findById(id);
    }


}
