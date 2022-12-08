package dev.jbriggs.aoc.days.d7;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.storage.core.TerminalDirectory;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day7Solution extends Day {

  private final Device device;
  public static final int DAY_NUMBER = 7;

  public Day7Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.7.input}") String inputPath, Device device) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.device = device;
  }

  @Override
  protected String partOne(List<String> input) {
    device.readTerminalLines(input);
    Collection<TerminalDirectory> directoriesAboveFileSize = device.findDirectoriesBelowFileSize(
        100000L);
    return String.valueOf(directoriesAboveFileSize.stream()
        .mapToLong(TerminalDirectory::getSize).sum());
  }

  @Override
  protected String partTwo(List<String> input) {
    return null;
  }


}
