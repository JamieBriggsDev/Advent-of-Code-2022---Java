package dev.jbriggs.aoc.days.d8;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day8Solution extends Day {

  private final Device device;
  public static final int DAY_NUMBER = 8;

  public Day8Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.8.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    this.device = Device.builder().build();
  }

  @Override
  protected Object partOne(List<String> input) {
    int length = input.get(0).length();
    int height = input.size();
    Forest forest = new Forest(length, height);
    for (int y = 0; y < height; y++) {
      int[] treesInRow = Arrays.stream(input.get(y).split("")).mapToInt(x -> Integer.valueOf(x))
          .toArray();
      for (int x = 0; x < treesInRow.length; x++) {
        forest.placeItem(new Tree(treesInRow[x]), x, y);
      }
    }

    int treesVisible = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < length; x++) {
        if (forest.isTreeVisible(x, y)) {
          treesVisible++;
        }
      }
    }
    return String.valueOf(treesVisible);
  }

  @SneakyThrows
  @Override
  protected Object partTwo(List<String> input) {
    int length = input.get(0).length();
    int height = input.size();
    Forest forest = new Forest(length, height);
    for (int y = 0; y < height; y++) {
      int[] treesInRow = Arrays.stream(input.get(y).split("")).mapToInt(x -> Integer.valueOf(x))
          .toArray();
      for (int x = 0; x < treesInRow.length; x++) {
        forest.placeItem(new Tree(treesInRow[x]), x, y);
      }
    }

    Long highestScenicScore = 0L;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < length; x++) {
        Long scenicScore = forest.getScenicScore(x, y);
        if (scenicScore > highestScenicScore) {
          highestScenicScore = scenicScore;
        }
      }
    }
    return String.valueOf(highestScenicScore);
  }


}
