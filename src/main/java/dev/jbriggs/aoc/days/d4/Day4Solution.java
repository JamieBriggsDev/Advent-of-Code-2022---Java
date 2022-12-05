package dev.jbriggs.aoc.days.d4;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.core.Pair;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day4Solution extends Day {

  public static final int DAY_NUMBER = 4;

  public TimeSlotChecker timeSlotChecker;

  public Day4Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.4.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
    timeSlotChecker = new TimeSlotChecker();
  }

  @Override
  protected String partOne(List<String> input) {
    List<Pair<TimeSlot>> timeSlotPairs = getTimeSlotPairs(input);
    return String.valueOf(timeSlotPairs.stream().filter(
        x -> timeSlotChecker.doTimeslotsFullyContainTheOther(x.getA(),
            x.getB())).count());
  }

  @Override
  protected String partTwo(List<String> input) {
    List<Pair<TimeSlot>> timeSlotPairs = getTimeSlotPairs(input);
    return String.valueOf(timeSlotPairs.stream().filter(
        x -> timeSlotChecker.doTimeslotsPartiallyContainTheOther(x.getA(),
            x.getB())).count());
  }

  private static List<Pair<TimeSlot>> getTimeSlotPairs(List<String> input) {
    List<Pair<TimeSlot>> timeSlotPairs = new ArrayList<>();

    input.forEach(x -> {
      String[] timeSlotsRaw = x.split(",");
      timeSlotPairs.add(new Pair<>(new TimeSlot(timeSlotsRaw[0]),
          new TimeSlot(timeSlotsRaw[1])));
    });

    return timeSlotPairs;
  }

}
