package dev.jbriggs.aoc;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public abstract class Day {

  public Day(PuzzleInputParser puzzleInputParser) {
    this.puzzleInputParser = puzzleInputParser;
    this.input = puzzleInputParser.getPuzzleInputFromFile(getInputPath());
  }

  private final PuzzleInputParser puzzleInputParser;

  private final String[] input;

  public abstract String[] getInput();

  protected abstract String partOne(List<String> input);

  protected abstract String partTwo(List<String> input);

  public abstract Integer getDayNumber();

  public abstract String getInputPath();

  public boolean skip(){
    return false;
  }

  public String partOneAnswer(){
    return partOne(Arrays.stream(getInput()).toList());
  }

  public String partTwoAnswer(){
    return partTwo(Arrays.stream(getInput()).toList());
  }

  @Override
  public String toString() {
    return "Day " + getDayNumber();
  }
}
