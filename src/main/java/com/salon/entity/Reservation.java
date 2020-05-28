package com.salon.entity;

import java.util.Date;
import java.util.Objects;

public class Reservation {

  private final Integer id;

  private Date start;

  private Date end;

  private Procedure procedure;

  private User beautyMaster;

  private User client;

  public Reservation(ReservationBuilder builder) {
    this.id = builder.id;
    this.start = builder.start;
    this.end = builder.end;
    this.procedure = builder.procedure;
    this.beautyMaster = builder.beautyMaster;
    this.client = builder.client;
  }

  public Integer getId() {
    return id;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }


  public Procedure getProcedure() {
    return procedure;
  }

  public void setProcedure(Procedure procedure) {
    this.procedure = procedure;
  }

  public User getBeautyMaster() {
    return beautyMaster;
  }

  public void setBeautyMaster(User beautyMaster) {
    this.beautyMaster = beautyMaster;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Reservation)) return false;
    Reservation that = (Reservation) o;
    return Objects.equals(id, that.id)
        && Objects.equals(start, that.start)
        && Objects.equals(end, that.end)
        && procedure == that.procedure
        && Objects.equals(beautyMaster, that.beautyMaster)
        && Objects.equals(client, that.client);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, start, end, procedure, beautyMaster, client);
  }

  @Override
  public String toString() {
    return "Reservation{" +
            "id=" + id +
            ", start=" + start +
            ", end=" + end +
            ", serviceOption=" + procedure +
            ", beautyMaster=" + beautyMaster +
            ", client=" + client +
            '}';
  }

  public static class ReservationBuilder {
    private Integer id;
    private Date start;
    private Date end;
    private Procedure procedure;
    private User beautyMaster;
    private User client;

    private ReservationBuilder() {}

    public static ReservationBuilder aReservation() {
      return new ReservationBuilder();
    }

    public ReservationBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public ReservationBuilder withStart(Date start) {
      this.start = start;
      return this;
    }

    public ReservationBuilder withEnd(Date end) {
      this.end = end;
      return this;
    }

    public ReservationBuilder withProcedure(Procedure procedure) {
      this.procedure = procedure;
      return this;
    }

    public ReservationBuilder withBeautyMaster(User beautyMaster) {
      this.beautyMaster = beautyMaster;
      return this;
    }

    public ReservationBuilder withClient(User client) {
      this.client = client;
      return this;
    }

    public Reservation build() {
      return new Reservation(this);
    }
  }
}
