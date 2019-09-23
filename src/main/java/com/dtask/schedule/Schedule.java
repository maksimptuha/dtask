package com.dtask.schedule;

import java.util.List;

public class Schedule {

  public List<Interval> poshIntervals;
  public List<Interval> grottyIntervals;

  public Schedule(List<Interval> poshIntervals, List<Interval> grottyIntervals) {
    this.poshIntervals = poshIntervals;
    this.grottyIntervals = grottyIntervals;
  }

  public List<Interval> getPoshIntervals() {
    return poshIntervals;
  }

  public void setPoshIntervals(List<Interval> poshIntervals) {
    this.poshIntervals = poshIntervals;
  }

  public List<Interval> getGrottyIntervals() {
    return grottyIntervals;
  }

  public void setGrottyIntervals(List<Interval> grottyIntervals) {
    this.grottyIntervals = grottyIntervals;
  }
}
