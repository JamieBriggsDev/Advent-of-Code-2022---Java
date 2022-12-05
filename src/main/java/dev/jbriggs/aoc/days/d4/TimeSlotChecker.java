package dev.jbriggs.aoc.days.d4;

public class TimeSlotChecker {
  public boolean doTimeslotsFullyContainTheOther(TimeSlot timeSlotA, TimeSlot timeSlotB) {
    return timeSlotA.fullyContains(timeSlotB) || timeSlotB.fullyContains(timeSlotA);
  }

  public boolean doTimeslotsPartiallyContainTheOther(TimeSlot timeSlotA,
      TimeSlot timeSlotB) {
    return timeSlotA.partiallyContains(timeSlotB) || timeSlotB.partiallyContains(timeSlotA);
  }
}
