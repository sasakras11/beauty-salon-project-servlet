package com.salon.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

  private final Integer id;
  private String username;
  private String password;
  private Role role;
  private List<Reservation> reservations;

  public User(UserBuilder builder) {
    this.id = builder.id;
    this.username = builder.username;
    this.password = builder.password;
    this.role = builder.role;
    this.reservations = builder.reservations;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(username, user.username)
        && Objects.equals(password, user.password)
        && role == user.role
        && Objects.equals(reservations, user.reservations);
  }

  @Override
  public int hashCode() {
    return Objects.hash( username, password, role, reservations);
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", role="
        + role
        + ", reservations="
        + reservations
        + '}';
  }

  public static final class UserBuilder {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private List<Reservation> reservations;

    private UserBuilder() {}


    public static UserBuilder anUser() {
      return new UserBuilder();
    }

    public UserBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public UserBuilder withUsername(String username) {
      this.username = username;
      return this;
    }

    public UserBuilder withPassword(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder withRole(Role role) {
      this.role = role;
      return this;
    }

    public UserBuilder withReservations(List<Reservation> reservations) {
      this.reservations = reservations;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
