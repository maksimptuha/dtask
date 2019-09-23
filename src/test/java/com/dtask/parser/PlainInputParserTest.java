package com.dtask.parser;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.dtask.schedule.Interval;
import com.dtask.schedule.Interval.Company;
import java.util.List;
import org.junit.Test;

public class PlainInputParserTest {

  private InputParser plainInputParser = new PlainInputParser();

  @Test(expected = IllegalArgumentException.class)
  public void inputParse_nonExistingInputFilePathPassed_thenException() {
    String path = "src/test/resources/input.txt";

    plainInputParser.parse(path);
  }

  @Test(expected = IllegalArgumentException.class)
  public void inputParse_wrongIntervalInputPassed_thenException() {
    String path = "src/test/resources/input1.txt";

    plainInputParser.parse(path);
  }

  @Test(expected = IllegalArgumentException.class)
  public void inputParse_wrongIntervalCompanyPassed_thenException() {
    String path = "src/test/resources/input2.txt";

    plainInputParser.parse(path);
  }

  @Test(expected = IllegalArgumentException.class)
  public void inputParse_wrongIntervalDeparturePassed_thenException() {
    String path = "src/test/resources/input3.txt";

    plainInputParser.parse(path);
  }

  @Test(expected = IllegalArgumentException.class)
  public void inputParse_wrongIntervalArrivalPassed_thenException() {
    String path = "src/test/resources/input4.txt";

    plainInputParser.parse(path);
  }

  @Test
  public void inputParse_emptyInputParsed_thenSuccessful() {
    String path = "src/test/resources/input5.txt";

    List<Interval> intervals = plainInputParser.parse(path);

    assertThat("failed to parse input", intervals.size(), is(0));
  }

  @Test
  public void inputParse_inputParsed_thenSuccessful() {
    String path = "src/test/resources/input6.txt";

    List<Interval> intervals = plainInputParser.parse(path);

    Interval expectedInterval1 = new Interval(Company.Posh, 10 * 60, 11 * 60);
    Interval expectedInterval2 = new Interval(Company.Grotty, 11 * 60, 12 * 60);

    assertThat("failed to parse input", intervals.size(), is(2));
    assertThat(
        "failed to parse input",
        intervals,
        containsInAnyOrder(expectedInterval1, expectedInterval2));
  }
}
