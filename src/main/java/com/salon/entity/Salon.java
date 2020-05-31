package com.salon.entity;

import java.util.List;
import java.util.Objects;

public class Salon {
  private final Integer id;
  private String address;
  private List<User> masters;

  public Salon(SalonBuilder builder) {
    this.id = builder.id;
    this.address = builder.address;
    this.masters = builder.masters;
  }

  public int getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<User> getMasters() {
    return masters;
  }

  public void setMasters(List<User> masters) {
    this.masters = masters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Salon)) return false;
    Salon salon = (Salon) o;
    return
        Objects.equals(address, salon.address)
        && Objects.equals(masters, salon.masters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, masters);
  }

  @Override
  public String toString() {
    return "Salon{" +
            "id=" + id +
            ", address='" + address + '\'' +
            ", masters=" + masters +
            '}';
  }

  public static final class SalonBuilder {
    private int id;
    private String address;
    private List<User> masters;

    private SalonBuilder() {}

    public static SalonBuilder aSalon() {
      return new SalonBuilder();
    }

    public SalonBuilder withId(int id) {
      this.id = id;
      return this;
    }

    public SalonBuilder withAddress(String address) {
      this.address = address;
      return this;
    }

    public SalonBuilder withMasters(List<User> masters) {
      this.masters = masters;
      return this;
    }

    public Salon build() {
      return new Salon(this);
    }
  }
}
