package com.salon.entity;

public enum Procedure {
  HAIR_CUT(1),
  PILLING(2),
  BODY_SCRUB(1),
  SUGARING(2);

  private final int durationHours;

  Procedure(int durationHours) {
    this.durationHours= durationHours;
  }

  public int getDurationHours() {
    return durationHours;
  }
}
