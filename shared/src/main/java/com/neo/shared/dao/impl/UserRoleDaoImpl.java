package com.neo.shared.dao.impl;

import com.neo.shared.dao.UserRoleDao;
import com.neo.shared.model.UserRoleModel;


import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UserRoleModel> findAll() {
        return entityManager.createNativeQuery(
                "select u.um_user_name username, r.um_role_name role " +
                "from um_hybrid_user_role u " +
                "join um_hybrid_role r on u.um_role_id = r.um_id")
                .unwrap(NativeQuery.class)
                .addScalar("username", StringType.INSTANCE)
                .addScalar("role", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserRoleModel.class)).getResultList();

    }

    @Override
    public List<UserRoleModel> findByUserName(String username) {
        return entityManager.createNativeQuery(
                "select u.um_user_name username, r.um_role_name role " +
                        "from um_hybrid_user_role u " +
                        "join um_hybrid_role r on u.um_role_id = r.um_id\n" +
                        "where u.um_user_name = :username")
                .unwrap(NativeQuery.class)
                .setParameter("username", username)
                .addScalar("username", StringType.INSTANCE)
                .addScalar("role", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserRoleModel.class)).getResultList();
    }
}
