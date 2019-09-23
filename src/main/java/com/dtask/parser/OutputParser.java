package com.dtask.parser;

import com.dtask.schedule.Schedule;

public interface OutputParser {

  void parse(Schedule schedule, String path);
}
