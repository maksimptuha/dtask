package com.dtask.subintervalsearch;

import static com.dtask.schedule.Interval.Company.Grotty;
import static com.dtask.schedule.Interval.Company.Posh;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.dtask.schedule.Interval;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LinearSubIntervalSearchTest {

  private SubIntervalSearch linearSubIntervalSearch;

  @Before
  public void setUp() {
    List<Interval> intervals =
        Arrays.asList(
            new Interval(Posh, 1, 6),
            new Interval(Posh, 3, 4),
            new Interval(Grotty, 5, 6),
            new Interval(Posh, 7, 8));

    linearSubIntervalSearch = new LinearSubIntervalSearch(intervals);
  }

  @Test(expected = IllegalArgumentException.class)
  public void searchSubIntervals_nullIntervalPassed_thenException() {
    linearSubIntervalSearch.search(null);
  }

  @Test
  public void searchSubIntervals_subIntervalsFound_thenSuccessful() {
    Interval interval = new Interval(Posh, 1, 6);

    List<Interval> subIntervals = linearSubIntervalSearch.search(interval);

    assertThat("failed to find sub intervals", subIntervals.size(), is(2));
    assertThat(
        "failed to find sub intervals",
        subIntervals,
        containsInAnyOrder(new Interval(Posh, 3, 4), new Interval(Grotty, 5, 6)));
  }
}
