package com.dtask.parser;

import com.dtask.schedule.Interval;
import com.dtask.schedule.Interval.Company;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

public class PlainInputParser implements InputParser {

  @Override
  public List<Interval> parse(String path) {
    List<String> inputLines;
    try {
      inputLines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalArgumentException("failed to read the input file", e);
    }

    return inputLines
      .stream()
      .map(inputLine -> {
        String[] lineValues = inputLine.split(" ");

        if(lineValues.length != 3) {
          throw new IllegalArgumentException("wrong input data");
        }

        Company company;
        try{
          company = Company.valueOf(lineValues[0]);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("wrong input interval company", e);
        }

        int departure;
        try {
          departure = LocalTime.parse(lineValues[1]).get(ChronoField.MINUTE_OF_DAY);
        } catch(DateTimeParseException e) {
          throw new IllegalArgumentException("wrong input interval departure", e);
        }

        int arrival;
        try {
          arrival = LocalTime.parse(lineValues[2]).get(ChronoField.MINUTE_OF_DAY);
        } catch(DateTimeParseException e) {
          throw new IllegalArgumentException("wrong input interval arrival", e);
        }

        return new Interval(company, departure, arrival);
      })
      .collect(Collectors.toList());
  }
}
