package com.neo.identity.controller;


import com.neo.identity.entity.ConsumerAppEntity;
import com.neo.identity.service.ConsumerAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ConsumerAppController {

    Logger log = LoggerFactory.getLogger(ConsumerAppController.class);
    private final ConsumerAppService consumerAppService;

    public ConsumerAppController(ConsumerAppService consumerAppService) {
        this.consumerAppService = consumerAppService;
    }

    @GetMapping("/get-all-consumer-app")
    public List<ConsumerAppEntity> findAll(){
        log.info("get all consumer application");
        return consumerAppService.finAll();
    }

    @GetMapping("/api/v1")
    public String dashboard(){
        log.info("Application dashboard");
        return "Dashboard";
    }

    @GetMapping("/get-consumer-app-by-username")
    public List<ConsumerAppEntity> findAllByUsername(@RequestParam String username){
        log.info("get all  consumer application by username");
        return consumerAppService.findAllByUsername(username);
    }
}
