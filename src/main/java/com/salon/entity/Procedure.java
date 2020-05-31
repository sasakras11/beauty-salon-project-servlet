package com.salon.entity;

import java.util.Objects;

public class Procedure {
  private final Integer id;
  private String name;
  private String description;
  private int durationHours;


  public Procedure(ProcedureBuilder builder){
    this.name = builder.name;
    this.description = builder.description;
    this.durationHours = builder.durationHours;
    this.id = builder.id;

  }
  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDurationHours() {
    return durationHours;
  }

  public void setDurationHours(int durationHours) {
    this.durationHours = durationHours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Procedure)) return false;
    Procedure procedure = (Procedure) o;
    return durationHours == procedure.durationHours &&
            Objects.equals(name, procedure.name) &&
            Objects.equals(description, procedure.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash( name, description, durationHours);
  }

  @Override
  public String toString() {
    return "Procedure{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", durationHours=" + durationHours +
            '}';
  }

  public static final class ProcedureBuilder {
    private Integer id;
    private String name;
    private String description;
    private int durationHours;

    private ProcedureBuilder() {
    }

    public static ProcedureBuilder aProcedure() {
      return new ProcedureBuilder();
    }

    public ProcedureBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public ProcedureBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public ProcedureBuilder withDescription(String description) {
      this.description = description;
      return this;
    }

    public ProcedureBuilder withDurationHours(int durationHours) {
      this.durationHours = durationHours;
      return this;
    }

    public Procedure build() {
    return new Procedure(this);
    }
  }
}
