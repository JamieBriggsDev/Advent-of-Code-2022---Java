package dev.jbriggs.aoc.days.d4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TimeSlot {

  private final int startTime;
  private final int endTime;


  public TimeSlot(String sections) {
    String[] split = sections.split("-");
    startTime = Integer.parseInt(split[0]);
    endTime = Integer.parseInt(split[1]);
  }

  public boolean fullyContains(TimeSlot other) {
    return this.getStartTime() <= other.getStartTime()
        && this.getEndTime() >= other.getEndTime();
  }

  public boolean partiallyContains(TimeSlot other) {
    return other.getStartTime() >= this.getStartTime()
        && other.getStartTime() <= this.getEndTime();
  }
}
