package com.salon.service.impl;

import com.salon.dao.UserDao;
import com.salon.entity.Role;
import com.salon.entity.User;
import com.salon.exception.ValidationException;
import com.salon.service.UserService;
import com.salon.service.util.PasswordUtil;
import com.salon.service.util.CredentialsValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final CredentialsValidator credentialsValidator;
    private final PasswordUtil passwordUtil;

    public UserServiceImpl(UserDao userDao, CredentialsValidator credentialsValidator, PasswordUtil passwordUtil) {
        this.userDao = userDao;
        this.credentialsValidator = credentialsValidator;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public Optional<User> login(String username, String password) {

        Optional<User> byUsername = userDao.findByUserName(username);
        String hashedPassword = passwordUtil.getHashedPassword(password);

        boolean isPresent = byUsername
                .map(User::getPassword)
                .filter(pass -> pass.equals(hashedPassword))
                .isPresent();

        return isPresent ? byUsername : Optional.empty();
    }

    @Override
    public User register(String username, String password) {
        credentialsValidator.validate(username, password);

        if (!(userDao.findByUserName(username).isPresent())) {
            User user = User.UserBuilder.anUser().withUsername(username).withPassword(passwordUtil.getHashedPassword(password)).withRole(Role.CLIENT).build();
            userDao.save(user);
           return user;
        } else {
            throw new ValidationException("user is already registered");
        }
    }

    @Override
    public User findByName(String name) {
        return userDao.findByUserName(name).get();
    }
}

