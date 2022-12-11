package dev.jbriggs.aoc.days.d10;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day10Solution extends Day {

  public static final int DAY_NUMBER = 10;

  public Day10Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.10.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @SneakyThrows
  @Override
  protected String partOne(List<String> input) {
    return "";
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    return "";
  }


}
