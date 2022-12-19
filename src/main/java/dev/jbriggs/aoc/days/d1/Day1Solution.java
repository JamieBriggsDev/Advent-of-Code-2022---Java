package dev.jbriggs.aoc.days.d1;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.ArrayEmptySplitter;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day1Solution extends Day {

  private final CalorieCounter calorieCounter;
  private final ArrayEmptySplitter arrayEmptySplitter;

  public Day1Solution(PuzzleInputParser puzzleInputParser, @Value("${solutions.day.1.input}") String inputPath) {
    super(1, puzzleInputParser, inputPath);
    calorieCounter = new CalorieCounter();
    arrayEmptySplitter = new ArrayEmptySplitter();
  }

  @Override
  protected Object partOne(List<String> input) {
    List<List<String>> split = arrayEmptySplitter.split(input);
    Long total = 0L;
    for (List<String> elfCalories : split) {
      Long totalElfCalories = calorieCounter.countCalories(elfCalories);
      if (totalElfCalories > total) {
        total = totalElfCalories;
      }
    }
    return total.toString();
  }

  @Override
  protected Object partTwo(List<String> input) {
    List<List<String>> split = arrayEmptySplitter.split(input);
    List<Long> longs = split.stream().map(calorieCounter::countCalories).sorted(Long::compareTo)
        .sorted(Comparator.reverseOrder()).toList();
    return String.valueOf(longs.get(0) + longs.get(1) + longs.get(2));
  }

}
