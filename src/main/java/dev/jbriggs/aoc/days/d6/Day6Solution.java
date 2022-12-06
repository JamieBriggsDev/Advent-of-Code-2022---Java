package dev.jbriggs.aoc.days.d6;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day6Solution extends Day {

  private final HandheldDevice handheldDevice;
  public static final int DAY_NUMBER = 6;

  public Day6Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.6.input}") String inputPath,
      HandheldDevice handheldDevice) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.handheldDevice = handheldDevice;
  }

  @Override
  protected String partOne(List<String> input) {
    return String.valueOf(handheldDevice.findMarkerPosition(input.get(0), 4));
  }

  @Override
  protected String partTwo(List<String> input) {
    return String.valueOf(handheldDevice.findMarkerPosition(input.get(0), 14));
  }


}
