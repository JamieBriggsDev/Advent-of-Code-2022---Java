package dev.jbriggs.aoc;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public abstract class Day {

  public Day(PuzzleInputParser puzzleInputParser, String inputPath) {
    this.puzzleInputParser = puzzleInputParser;
    this.input = puzzleInputParser.getPuzzleInputFromFile(inputPath);
  }

  private final PuzzleInputParser puzzleInputParser;
  private final String[] input;

  protected abstract String partOne(List<String> input);

  protected abstract String partTwo(List<String> input);

  public abstract Integer getDayNumber();

  public String[] getInput(){
    return this.input;
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
