package com.neo.identity.controller;


import com.neo.identity.entity.MenuEntity;
import com.neo.identity.entity.UmHybridUserRoleEntity;
import com.neo.identity.service.IMenuService;
import com.neo.identity.service.UmHybridUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j

public class MenuController {

    Logger logger = LoggerFactory.getLogger(MenuController.class);
    private final IMenuService iMenuService;


    public MenuController(IMenuService iMenuService) {
        this.iMenuService = iMenuService;
    }

     @GetMapping("/hello")
    public String hello (){
        return "hello";
    }
    
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
