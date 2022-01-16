package com.neo.identity.service.impl;


import com.neo.identity.entity.ConsumerAppEntity;
import com.neo.identity.repository.identity.ConsumerAppRepository;
import com.neo.identity.service.ConsumerAppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerAppServiceImpl implements ConsumerAppService {


    private final ConsumerAppRepository consumerAppRepository;

    public ConsumerAppServiceImpl(ConsumerAppRepository consumerAppRepository) {
        this.consumerAppRepository = consumerAppRepository;
    }

    @Override
    public List<ConsumerAppEntity> finAll() {
        return consumerAppRepository.findAll();
    }

    @Override
    public List<ConsumerAppEntity> findAllByUsername(String username) {
        return consumerAppRepository.findAllByUsername(username);
    }
}
