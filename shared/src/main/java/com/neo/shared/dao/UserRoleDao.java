package com.neo.shared.dao;

import com.neo.shared.model.UserRoleModel;

import java.util.List;

public interface UserRoleDao {

    List<UserRoleModel> findAll();

    List<UserRoleModel> findByUserName(String username);
}
