package dev.jbriggs.aoc.days.d7;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.storage.TerminalDirectory;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Collection;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
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
  @SneakyThrows
  protected String partOne(List<String> input) {
    device.readTerminalLines(input);
    Collection<TerminalDirectory> directoriesAboveFileSize = device.getTerminalReader()
        .findDirectoriesBelowFileSize(100000L);
    return String.valueOf(
        directoriesAboveFileSize.stream().mapToLong(TerminalDirectory::getSize)
            .sum());
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    device.readTerminalLines(input);
    Long spaceNeeded = 30000000L;
    Long spaceUsed = device.getTerminalReader().totalSpaceUsed();
    Long spaceAvailable = TerminalReader.TOTAL_DISK_SPACE_AVAILABLE - spaceUsed;
    long spaceToDelete = spaceNeeded - spaceAvailable;
    TerminalDirectory result = device.getTerminalReader()
        .findSmallestDirectoryAboveSpecificSize(spaceToDelete);
    return String.valueOf(result.getSize());
  }


}
