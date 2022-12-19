package dev.jbriggs.aoc.days.d11;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day11Solution extends Day {

  public static final int DAY_NUMBER = 11;

  public Day11Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.11.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @SneakyThrows
  @Override
  protected Object partOne(List<String> input) {
    StringBuilder joined = new StringBuilder();
    input.forEach(x -> joined.append(x+"\n"));
    joined.append("\n");
    List<String> monkeysRaw = Arrays.stream(joined.toString().split("\n\n")).toList();
    KeepAwayGame keepAwayGame = new KeepAwayGame(monkeysRaw);
    keepAwayGame.runGame(20);
    return String.valueOf(keepAwayGame.getTotalMonkeyBusiness());
  }

  @SneakyThrows
  @Override
  protected Object partTwo(List<String> input) {
    StringBuilder joined = new StringBuilder();
    input.forEach(x -> joined.append(x+"\n"));
    joined.append("\n");
    List<String> monkeysRaw = Arrays.stream(joined.toString().split("\n\n")).toList();
    KeepAwayGame keepAwayGame = new KeepAwayGame(monkeysRaw, true);
    keepAwayGame.runGame(10000);
    return String.valueOf(keepAwayGame.getTotalMonkeyBusiness());
  }


}
