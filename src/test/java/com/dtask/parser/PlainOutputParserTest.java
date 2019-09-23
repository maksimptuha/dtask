package com.dtask.parser;

import static com.dtask.schedule.Interval.Company.Grotty;
import static com.dtask.schedule.Interval.Company.Posh;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.dtask.schedule.Interval;
import com.dtask.schedule.Schedule;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlainOutputParserTest {

  private String outputPath = "src/test/resources/output.txt";

  private OutputParser plainOutputParser = new PlainOutputParser();

  private void clean() throws Exception {
    Files.deleteIfExists(Paths.get(outputPath));
  }

  @Before
  public void setUp() throws Exception {
    clean();
  }

  @After
  public void tearDown() throws Exception {
    clean();
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseOutput_wrongPathPassed_thenException() {
    Schedule schedule = new Schedule(Collections.emptyList(), Collections.emptyList());

    plainOutputParser.parse(schedule, "src/test/resources/a/output.txt");
  }

  @Test
  public void parseOutput_outputParsed_thenSuccessful() {
    Schedule schedule =
        new Schedule(
            Collections.singletonList(new Interval(Posh, 600, 660)),
            Collections.singletonList(new Interval(Grotty, 600, 660)));

    plainOutputParser.parse(schedule, outputPath);

    assertThat("failed to parse output", Files.exists(Paths.get(outputPath)), is(true));
  }
}
