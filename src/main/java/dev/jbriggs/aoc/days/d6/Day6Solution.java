package dev.jbriggs.aoc.days.d6;

import dev.jbriggs.aoc.AOCException;
import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.reader.MarkerReader;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day6Solution extends Day {

  private final Device device;
  public static final int DAY_NUMBER = 6;

  public Day6Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.6.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.device = Device.builder().addReaderModule(new MarkerReader()).build();
  }

  @Override
  @SneakyThrows
  protected Object partOne(List<String> input) {
    device.readTerminalLines(input);
    if(device.getReader() instanceof MarkerReader markerReader){
      return String.valueOf(markerReader.findMarkerPosition(4));
    }
    throw new AOCException("Device missing MarkerReader module");
  }

  @Override
  @SneakyThrows
  protected Object partTwo(List<String> input) {
    device.readTerminalLines(input);
    if(device.getReader() instanceof MarkerReader markerReader){
      return String.valueOf(device.findMarkerPosition(input.get(0), 14));
    }
    throw new AOCException("Device missing MarkerReader module");
  }


}
