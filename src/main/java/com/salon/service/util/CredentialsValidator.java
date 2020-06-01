package com.salon.service.util;

import com.salon.exception.ValidationException;

public class CredentialsValidator {

  private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,}";
  private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

  public void validate(String username, String password) {
    validateUsername(username);
    validatePassword(password);
  }

  private void validateUsername(String str) {
    if (!str.matches(USERNAME_PATTERN)) {
      throw new ValidationException(
          "username should contain only English literals,numbers and be at least 3 characters long");
    }
  }

  private void validatePassword(String str) {
    if (!str.matches(PASSWORD_PATTERN)) {
      throw new ValidationException(
          "password should contain only English literals,numbers and be at least 8 characters long");
    }
  }
}
