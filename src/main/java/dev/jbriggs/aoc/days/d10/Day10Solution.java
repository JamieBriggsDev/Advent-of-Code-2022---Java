package dev.jbriggs.aoc.days.d10;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.CRTScreen;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.DeviceException;
import dev.jbriggs.aoc.handheld.reader.VideoSignalReader;
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
    this.device = Device.builder().addMemoryModule(new CRTScreen())
        .addReaderModule(new VideoSignalReader()).build();
  }

  @SneakyThrows
  @Override
  protected String partOne(List<String> input) {
    device.readTerminalLines(input.stream().map(x -> "$ " + x).toList());
    List<Integer> cycles = Arrays.asList(20, 60, 100, 140, 180, 220);
    if (device.getReader() instanceof VideoSignalReader videoSignalReader) {
      int totalSignalStrength = cycles.stream().mapToInt(
          x -> videoSignalReader.getSignalStrengthDuringCycle(x)).sum();
      return String.valueOf(totalSignalStrength);
    }else {
      throw new DeviceException("Video signal reader module missing!");
    }
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    device.readTerminalLines(input.stream().map(x -> "$ " + x).toList());
    return "\n" + device.getCurrentScreenDisplay();
  }


}
