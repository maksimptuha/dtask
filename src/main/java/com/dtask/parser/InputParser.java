package com.dtask.parser;

import com.dtask.schedule.Interval;
import java.util.List;

public interface InputParser {

  List<Interval> parse(String path);
}
