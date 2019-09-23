package com.dtask.schedule;

import java.time.LocalTime;
import java.util.Objects;

public class Interval {

  private Company company;
  private int departure;
  private int arrival;

  public Interval(Company company, int departure, int arrival) {
    this.company = company;
    this.departure = departure;
    this.arrival = arrival;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public int getDeparture() {
    return departure;
  }

  public void setDeparture(int departure) {
    this.departure = departure;
  }

  public int getArrival() {
    return arrival;
  }

  public void setArrival(int arrival) {
    this.arrival = arrival;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Interval)) {
      return false;
    }
    Interval interval = (Interval) o;
    return getDeparture() == interval.getDeparture()
        && getArrival() == interval.getArrival()
        && getCompany() == interval.getCompany();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCompany(), getDeparture(), getArrival());
  }

  @Override
  public String toString() {
    return String.join(
        " ",
        company.toString(),
        LocalTime.ofSecondOfDay(departure * 60).toString(),
        LocalTime.ofSecondOfDay(arrival * 60).toString());
  }

  public enum Company {
    Posh,
    Grotty
  }
}
