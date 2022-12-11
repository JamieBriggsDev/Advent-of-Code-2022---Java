package dev.jbriggs.aoc.days.d6;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.register.MemoryRegisterHandler;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day6Solution extends Day {

  private final Device device;
  public static final int DAY_NUMBER = 6;

  public Day6Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.6.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.device = new Device(
        new TerminalReader(new TerminalStorage(), new MemoryRegisterHandler()));
  }

  @Override
  protected String partOne(List<String> input) {
    return String.valueOf(device.findMarkerPosition(input.get(0), 4));
  }

  @Override
  protected String partTwo(List<String> input) {
    return String.valueOf(device.findMarkerPosition(input.get(0), 14));
  }


}
