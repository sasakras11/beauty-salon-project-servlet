package com.salon.service.impl;

import com.salon.dao.UserDao;
import com.salon.entity.User;

import com.salon.exception.ValidationException;
import com.salon.service.UserService;
import com.salon.service.util.CredentialsValidator;
import com.salon.service.util.PasswordUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  private static final String ENCODED_PASSWORD = "encoded password";
  private static final String PASSWORD = "password";
  private static final String USERNAME = "username";
  private static final String INCORRECT_USERNAME = "incorrect username";
  private static final String INCORRECT_PASSWORD = "incorrect password";
  private static final String ENCODED_INCORRECT_PASSWORD = "encoded incorrect password";
  private static final User USER =
      User.UserBuilder.anUser().withUsername(USERNAME).withPassword(ENCODED_PASSWORD).build();

  @Mock private UserDao userDao;
  @Mock private CredentialsValidator credentialsValidator;
  @Mock private PasswordUtil passwordUtil;

  @InjectMocks private UserServiceImpl userService;

  @After
  public void resetMocks() {
    reset(userDao, passwordUtil, credentialsValidator);
  }

  @Test
  public void ifUserNameAndPasswordAreValidShouldReturnOptionalOfUser() {
    when(userDao.findByUserName(USERNAME)).thenReturn(Optional.of(USER));
    when(passwordUtil.getHashedPassword(PASSWORD)).thenReturn(ENCODED_PASSWORD);

    assertTrue(userService.login(USERNAME, PASSWORD).isPresent());

    verify(userDao, times(1)).findByUserName(eq(USERNAME));
    verify(passwordUtil, times(1)).getHashedPassword(eq(PASSWORD));
  }

  @Test
  public void ifUsernameIsIncorrectUserShouldNotLogIn() {
    when(userDao.findByUserName(INCORRECT_USERNAME)).thenReturn(Optional.empty());
    when(passwordUtil.getHashedPassword(PASSWORD)).thenReturn(ENCODED_PASSWORD);

    assertFalse(userService.login(INCORRECT_USERNAME, PASSWORD).isPresent());

    verify(userDao, times(1)).findByUserName(eq(INCORRECT_USERNAME));
    verify(passwordUtil, times(1)).getHashedPassword(eq(PASSWORD));
  }

  @Test
  public void ifPasswordIsIncorrectUserShouldNotRegister() {
    when(userDao.findByUserName(USERNAME)).thenReturn(Optional.of(USER));
    when(passwordUtil.getHashedPassword(INCORRECT_PASSWORD)).thenReturn(ENCODED_INCORRECT_PASSWORD);

    assertFalse(userService.login(USERNAME, INCORRECT_PASSWORD).isPresent());

    verify(userDao, times(1)).findByUserName(eq(USERNAME));
    verify(passwordUtil, times(1)).getHashedPassword(eq(INCORRECT_PASSWORD));
  }

  @Test
  public void ifCredentialsAreValidUserShouldRegister(){
    doNothing().when(credentialsValidator).validate(USERNAME,PASSWORD);
    when(userDao.findByUserName(USERNAME)).thenReturn(Optional.empty());
    doNothing().when(userDao).save(any());

    userService.register(USERNAME,PASSWORD);

    verify(credentialsValidator,times(1)).validate(USERNAME,PASSWORD);
    verify(userDao,times(1)).findByUserName(USERNAME);
    verify(userDao,times(1)).save(any());

  }
  @Test(expected = ValidationException.class)
  public void whenUserIsAlreadyInDatabaseHeShouldNoRegister() {
    doNothing().when(credentialsValidator).validate(any(),any());
    when(userDao.findByUserName(any())).thenReturn(Optional.of(USER));

    userService.register(USER.getUsername(), USER.getPassword());

    verify(credentialsValidator).validate(USERNAME,PASSWORD);
    verify(userDao).findByUserName(eq(USERNAME));

  }

}
