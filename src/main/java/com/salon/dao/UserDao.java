package com.salon.dao;

import com.salon.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<User> {

    Optional<User> findByUserName(String username);

    List<User> findAllMasterBySalonId(int salonId);



}
