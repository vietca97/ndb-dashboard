package com.neo.dashboard.controller;

import com.neo.dashboard.entity.MenuEntity;
import com.neo.dashboard.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@Transactional
public class MenuController {

    Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private IMenuService iMenuService;

    @GetMapping("/get-all-menu")
    public List<MenuEntity> getAllMenu (){
        logger.info("Get all menu ");
        return iMenuService.findAllMenu();
    }

    @GetMapping("/get-one/{id}")
    public Optional<MenuEntity> getOneMenu (@PathVariable("id") Long id){
        logger.info("Get one menu ");
        return iMenuService.findById(id);
    }

    @PostMapping("/create-menu")
    public MenuEntity createMenu (@RequestBody MenuEntity menuEntity){
        logger.info("create menu ");
        iMenuService.save(menuEntity);
         return menuEntity;
    }

    @PostMapping("/update-menu")
    public MenuEntity updateMenu (@RequestBody MenuEntity menuEntity){
        logger.info("update menu ");
        return iMenuService.update(menuEntity);
    }

    @PostMapping("/delete-menu/{id}")
    public void deleteMenu (@PathVariable("id") Long id ){
        logger.info("delete menu ");
        iMenuService.deleteById(id);
    }
}
