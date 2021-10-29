package com.neo.dashboard.service.impl;

import com.neo.dashboard.entity.MenuEntity;
import com.neo.dashboard.repository.MenuRepository;
import com.neo.dashboard.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

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
