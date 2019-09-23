package com.dtask.parser;

import com.dtask.schedule.Schedule;
import java.io.FileWriter;
import java.io.IOException;

public class PlainOutputParser implements OutputParser {

  @Override
  public void parse(Schedule schedule, String path) {
    try (FileWriter fileWriter = new FileWriter(path, false)) {
      schedule
          .getPoshIntervals()
          .forEach(
              interval -> {
                writeLine(fileWriter, interval.toString());
              });

      writeLine(fileWriter, "");

      schedule
          .getGrottyIntervals()
          .forEach(
              interval -> {
                writeLine(fileWriter, interval.toString());
              });
    } catch (IOException e) {
      throw new IllegalArgumentException("failed to create the output file", e);
    }
  }

  private void writeLine(FileWriter fileWriter, String line) {
    try {
      fileWriter.write(line + '\n');
    } catch (IOException e) {
      throw new IllegalArgumentException("failed to write to the output file", e);
    }
  }
}
