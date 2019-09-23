package com.dtask.subintervalsearch;

import com.dtask.schedule.Interval;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LinearSubIntervalSearch implements SubIntervalSearch {

  private List<Interval> intervals;

  public LinearSubIntervalSearch(List<Interval> intervals) {
    if (Objects.isNull(intervals)) {
      throw new IllegalArgumentException("null intervals list passed");
    }

    this.intervals = intervals;
  }

  @Override
  public List<Interval> search(Interval interval) {
    if (Objects.isNull(interval)) {
      throw new IllegalArgumentException("null interval passed");
    }

    return intervals.stream()
        .filter(
            _interval ->
                !Objects.equals(_interval, interval)
                    && _interval.getDeparture() >= interval.getDeparture()
                    && _interval.getArrival() <= interval.getArrival())
        .collect(Collectors.toList());
  }
}
