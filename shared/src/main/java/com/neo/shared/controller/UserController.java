package com.neo.shared.controller;

import com.neo.shared.dao.UserRoleDao;
import com.neo.shared.entity.UmHybridUserRoleEntity;
import com.neo.shared.model.UserRoleModel;
import com.neo.shared.service.UmHybridUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UmHybridUserRoleService umHybridUserRoleService;

    private final UserRoleDao userRoleDao;

    public UserController(UmHybridUserRoleService umHybridUserRoleService, UserRoleDao userRoleDao) {
        this.umHybridUserRoleService = umHybridUserRoleService;
        this.userRoleDao = userRoleDao;
    }

    @GetMapping("/get-all-user-role")
    public List<UserRoleModel> getAllUserAndRole (){
        logger.info("Get all user and role ");
        List<UserRoleModel> lstUserAndRole = userRoleDao.findAll();
        return lstUserAndRole;
    }

    @GetMapping("/get-all-user-role-by-username")
    public List<UserRoleModel> getAllUserAndRoleByUsername (@RequestParam(value = "username", required = true) String username){
        logger.info("Get all user and role ");
        List<UserRoleModel> lstUserAndRole = userRoleDao.findByUserName(username);
        return lstUserAndRole;
    }

    @GetMapping("/api/v1")
    public String dashboard(){
        logger.info("Application dashboard");
        return "Dashboard";
    }
}
