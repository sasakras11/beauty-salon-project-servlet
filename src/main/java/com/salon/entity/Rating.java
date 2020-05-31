package com.salon.entity;

import java.util.Objects;

public class Rating {

  private final Integer id;

  private User master;

  private Double ratingMark;

  public Rating(RatingBuilder builder) {
    this.id = builder.id;
    this.master = builder.master;
    this.ratingMark = builder.ratingMark;
  }

  public Integer getId() {
    return id;
  }

  public User getMaster() {
    return master;
  }

  public void setMaster(User master) {
    this.master = master;
  }

  public Double getRatingMark() {
    return ratingMark;
  }

  public void setRatingMark(Double ratingMark) {
    this.ratingMark = ratingMark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Rating)) return false;
    Rating rating1 = (Rating) o;
    return Objects.equals(master, rating1.master)
        && Objects.equals(ratingMark, rating1.ratingMark);
  }

  @Override
  public int hashCode() {
    return Objects.hash( master, ratingMark);
  }

  @Override
  public String toString() {
    return "Rating{" +
            "id=" + id +
            ", master=" + master +
            ", ratingMark=" + ratingMark +
            '}';
  }

  public static final class RatingBuilder {
    private Integer id;
    private User master;
    private Double ratingMark;

    private RatingBuilder() {}

    public static RatingBuilder aRating() {
      return new RatingBuilder();
    }

    public RatingBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public RatingBuilder withMaster(User master) {
      this.master = master;
      return this;
    }

    public RatingBuilder withRatingMark(Double ratingMark) {
      this.ratingMark = ratingMark;
      return this;
    }

    public Rating build() {
      return new Rating(this);
    }
  }
}
