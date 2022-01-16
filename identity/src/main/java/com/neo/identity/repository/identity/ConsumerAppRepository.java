package com.neo.identity.repository.identity;



import com.neo.identity.entity.ConsumerAppEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerAppRepository extends CrudRepository<ConsumerAppEntity,Long> {

    List<ConsumerAppEntity> findAll();

    List<ConsumerAppEntity> findAllByUsername(String username);
}
