package com.dtask.subintervalsearch;

import com.dtask.schedule.Interval;
import java.util.List;

public interface SubIntervalSearch {

  List<Interval> search(Interval interval);
}
