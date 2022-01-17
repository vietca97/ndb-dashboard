package com.neo.identity.controller;

import com.neo.identity.dto.ServiceProvider;
import com.neo.identity.utils.ConvertFromXmlToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceProviderController {

    @Autowired
    private ConvertFromXmlToJson convert;

    @GetMapping("/getUrl")
    public List<ServiceProvider> getUrl(){
        return convert.getListUrl("administrator");
    }
}
