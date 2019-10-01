package com.dtask.schedule;

import static com.dtask.schedule.Interval.Company.Grotty;
import static com.dtask.schedule.Interval.Company.Posh;

import com.dtask.subintervalsearch.SubIntervalSearch;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScheduleBuilder {

  private SubIntervalSearch subIntervalSearch;

  public ScheduleBuilder(SubIntervalSearch subIntervalSearch) {
    this.subIntervalSearch = subIntervalSearch;
  }

  public Schedule build(List<Interval> intervals) {
    if (Objects.isNull(intervals)) {
      throw new IllegalArgumentException("null intervals list passed");
    }

    List<Interval> filteredIntervals =
        intervals.stream()
            .distinct()
            .filter(
                interval -> {
                  if (interval.getArrival() - interval.getDeparture() > 60) {
                    return false;
                  }

                  List<Interval> subIntervals =
                      subIntervalSearch.search(interval).stream()
                          .filter(
                              _interval ->
                                  !(_interval.getDeparture() == interval.getDeparture()
                                      && _interval.getArrival() == interval.getArrival()
                                      && _interval.getCompany() == Grotty))
                          .collect(Collectors.toList());

                  return subIntervals.isEmpty();
                })
            .collect(Collectors.toList());

    List<Interval> poshIntervals =
        filteredIntervals.stream()
            .filter(interval -> Objects.equals(interval.getCompany(), Posh))
            .sorted(Comparator.comparing(Interval::getDeparture))
            .collect(Collectors.toList());
    List<Interval> grottyIntervals =
        filteredIntervals.stream()
            .filter(interval -> Objects.equals(interval.getCompany(), Grotty))
            .sorted(Comparator.comparing(Interval::getDeparture))
            .collect(Collectors.toList());

    return new Schedule(poshIntervals, grottyIntervals);
  }
}
