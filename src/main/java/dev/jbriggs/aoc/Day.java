package dev.jbriggs.aoc;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public abstract class Day {

  public Day(Integer dayNumber,
      PuzzleInputParser puzzleInputParser, String inputPath) {
    this.puzzleInputParser = puzzleInputParser;
    this.dayNumber = dayNumber;
    this.input = puzzleInputParser.getPuzzleInputFromFile(inputPath);
  }

  private final PuzzleInputParser puzzleInputParser;
  private final String[] input;
  private final Integer dayNumber;

  protected abstract String partOne(List<String> input);

  protected abstract String partTwo(List<String> input);

  public Integer getDayNumber() {
    return this.dayNumber;
  }

  public String[] getInput() {
    return this.input;
  }

  public String partOneAnswer() {
    return partOne(Arrays.stream(getInput()).toList());
  }

  public String partTwoAnswer() {
    return partTwo(Arrays.stream(getInput()).toList());
  }

  @Override
  public String toString() {
    return "Day " + getDayNumber();
  }
}
