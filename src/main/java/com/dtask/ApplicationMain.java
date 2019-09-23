package com.dtask;

import com.dtask.parser.InputParser;
import com.dtask.parser.OutputParser;
import com.dtask.parser.PlainInputParser;
import com.dtask.parser.PlainOutputParser;
import com.dtask.schedule.Interval;
import com.dtask.schedule.Schedule;
import com.dtask.schedule.ScheduleBuilder;
import com.dtask.subintervalsearch.LinearSubIntervalSearch;
import java.util.List;

public class ApplicationMain {

  private static final String DEFAULT_INPUT_PATH = "input.txt";
  private static final String DEFAULT_OUTPUT_PATH = "output.txt";

  public static void main(String[] args) {
    try {
      String inputPath = args.length != 0 && args[0] != null ? args[0] : DEFAULT_INPUT_PATH;

      InputParser plainInputParser = new PlainInputParser();
      List<Interval> intervals = plainInputParser.parse(inputPath);

      ScheduleBuilder scheduleBuilder = new ScheduleBuilder(new LinearSubIntervalSearch(intervals));
      Schedule schedule = scheduleBuilder.build(intervals);

      OutputParser plainOutputParser = new PlainOutputParser();
      plainOutputParser.parse(schedule, DEFAULT_OUTPUT_PATH);
    } catch (Exception e) {
      e.printStackTrace();

      System.out.println(e.getMessage());
    }
  }
}
