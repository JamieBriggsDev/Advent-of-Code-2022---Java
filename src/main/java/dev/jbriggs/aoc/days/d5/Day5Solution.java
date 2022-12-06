package dev.jbriggs.aoc.days.d5;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day5Solution extends Day {

  public static final int DAY_NUMBER = 5;
  private static final Pattern PATTERN = Pattern.compile(
      "^move (\\d+) from (\\d+) to (\\d+)$");

  public Day5Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.5.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @Override
  protected String partOne(List<String> input) {
    int splitIndex = input.indexOf("");
    List<String> cargo = input.subList(0, splitIndex);
    List<String> commands = input.subList(splitIndex + 1, input.size());

    // Initialize cargo storage
    CargoStorage cargoStorage = getCargoStorage(cargo);
    // Run through commands
    for (int i = 0; i < commands.size(); i++) {
      Matcher matcher = PATTERN.matcher(commands.get(i));
      if (matcher.matches()) {
        Integer times = Integer.valueOf(matcher.group(1));
        Integer source = Integer.valueOf(matcher.group(2));
        Integer target = Integer.valueOf(matcher.group(3));
        cargoStorage.moveCargo(times, source, target);
      }
    }

    return cargoStorage.readTopOfStorage();
  }

  @Override
  protected String partTwo(List<String> input) {
    int splitIndex = input.indexOf("");
    List<String> cargo = input.subList(0, splitIndex);
    List<String> commands = input.subList(splitIndex + 1, input.size());

    // Initialize cargo storage
    CargoStorage cargoStorage = getCargoStorage(cargo);
    // Run through commands
    for (int i = 0; i < commands.size(); i++) {
      Matcher matcher = PATTERN.matcher(commands.get(i));
      if (matcher.matches()) {
        Integer times = Integer.valueOf(matcher.group(1));
        Integer source = Integer.valueOf(matcher.group(2));
        Integer target = Integer.valueOf(matcher.group(3));
        cargoStorage.moveCargoRetainOrder(times, source, target);
      }
    }

    return cargoStorage.readTopOfStorage();
  }

  public CargoStorage getCargoStorage(List<String> cargoDetails) {
    StringJoiner stringJoiner = new StringJoiner("\n");
    cargoDetails.forEach(stringJoiner::add);
    return new CargoStorage(stringJoiner.toString());
  }


}
