package dev.jbriggs.aoc.days.d9;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.days.d8.Forest;
import dev.jbriggs.aoc.days.d8.Tree;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import dev.jbriggs.aoc.util.VectorPrinter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day9Solution extends Day {

  public static final int DAY_NUMBER = 9;

  private static final Pattern COMMAND_PATTERN = Pattern.compile(
      "^([A-Z]) ([\\d])$");
  public Day9Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.9.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @SneakyThrows
  @Override
  protected String partOne(List<String> input) {
    Rope rope = new Rope();
    for(String command : input){
      Matcher matcher = COMMAND_PATTERN.matcher(command);
      if(matcher.matches()){
        Direction direction = Direction.fromCode(matcher.group(1));
        Integer times = Integer.valueOf(matcher.group(2));
        rope.move(direction, times);
      }
    }
    // Can't be 3114 too low
    // Can't be 3518 too low
    // 3519 too low
    return String.valueOf(
        rope.getRememberedTailsPositions().getAllItems().size());
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    return null;
  }


}
