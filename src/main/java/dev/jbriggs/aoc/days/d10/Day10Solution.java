package dev.jbriggs.aoc.days.d10;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.register.MemoryRegisterHandler;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day10Solution extends Day {

  public static final int DAY_NUMBER = 10;
  private final Device device;

  public Day10Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.10.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.device = new Device(
        new TerminalReader(new TerminalStorage(), new MemoryRegisterHandler()));

  }

  @SneakyThrows
  @Override
  protected String partOne(List<String> input) {
    device.readTerminalLines(input.stream().map(x -> "$ " + x).toList());
    List<Integer> cycles = Arrays.asList(20, 60, 100, 140, 180, 220);
    int totalSignalStrength = cycles.stream().mapToInt(device::getSignalStrengthAtCycle).sum();
    int one = device.getSignalStrengthAtCycle(20);
    int two = device.getSignalStrengthAtCycle(60);
    int three = device.getSignalStrengthAtCycle(100);
    int four = device.getSignalStrengthAtCycle(140);
    int five = device.getSignalStrengthAtCycle(180);
    int six = device.getSignalStrengthAtCycle(220);
    return String.valueOf(totalSignalStrength);
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    return "";
  }


}
