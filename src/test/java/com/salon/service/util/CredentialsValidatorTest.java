package com.salon.service.util;

import com.salon.entity.User;
import com.salon.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CredentialsValidatorTest {

  @Parameterized.Parameter public User user;

  CredentialsValidator validator;

  @Parameterized.Parameters
  public static List<User> primeNumbers() {
    return Arrays.asList(
        User.UserBuilder.anUser().withPassword("ale").withUsername("alexander_").build(),
        User.UserBuilder.anUser().withPassword("alexa_dqdq").withUsername("ale?").build(),
        User.UserBuilder.anUser().withPassword("aleADQDAS").withUsername("al!e").build(),
        User.UserBuilder.anUser().withPassword("AAAAAVBB").withUsername("al()e").build(),
        User.UserBuilder.anUser().withPassword("ACAWCAAC1").withUsername("ale№").build());
  }

  @Before
  public void init() {
    validator = new CredentialsValidator();
  }

  @Test(expected = ValidationException.class)
  public void exceptionShouldBeIfNotMatchesPattern() {
    validator.validate(user.getUsername(), user.getPassword());
  }
}
