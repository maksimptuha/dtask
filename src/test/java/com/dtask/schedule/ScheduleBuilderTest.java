package com.dtask.schedule;

import static com.dtask.schedule.Interval.Company.Grotty;
import static com.dtask.schedule.Interval.Company.Posh;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.dtask.subintervalsearch.LinearSubIntervalSearch;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ScheduleBuilderTest {

  private List<Interval> intervals;

  private ScheduleBuilder scheduleBuilder;

  @Before
  public void setUp() {
    intervals =
        Arrays.asList(
            new Interval(Posh, 615, 670),
            new Interval(Posh, 610, 660),
            new Interval(Grotty, 610, 660),
            new Interval(Grotty, 990, 1125),
            new Interval(Posh, 725, 750),
            new Interval(Grotty, 750, 805),
            new Interval(Grotty, 765, 805),
            new Interval(Posh, 1045, 1081));

    scheduleBuilder = new ScheduleBuilder(new LinearSubIntervalSearch(intervals));
  }

  @Test(expected = IllegalArgumentException.class)
  public void buildSchedule_nullIntervalsPassed_thenException() {
    scheduleBuilder.build(null);
  }

  @Test
  public void buildSchedule_scheduleBuilt_thenSuccessful() {
    Schedule schedule = scheduleBuilder.build(intervals);
    List<Interval> poshIntervals = schedule.getPoshIntervals();
    List<Interval> grottyIntervals = schedule.getGrottyIntervals();

    System.out.println(poshIntervals);
    System.out.println(grottyIntervals);

    assertThat("failed to build posh schedule", poshIntervals.size(), is(4));
    assertThat(
        "failed to build posh schedule",
        poshIntervals,
        containsInAnyOrder(
            new Interval(Posh, 615, 670),
            new Interval(Posh, 610, 660),
            new Interval(Posh, 725, 750),
            new Interval(Posh, 1045, 1081)));

    assertThat("failed to build grotty schedule", grottyIntervals.size(), is(1));
    assertThat(
        "failed to build grotty schedule",
        grottyIntervals,
        containsInAnyOrder(new Interval(Grotty, 765, 805)));
  }
}
