package com.neo.identity.service;


import com.neo.identity.entity.ConsumerAppEntity;

import java.util.List;

public interface ConsumerAppService {

    List<ConsumerAppEntity> finAll();
    List<ConsumerAppEntity> findAllByUsername(String username);
}
