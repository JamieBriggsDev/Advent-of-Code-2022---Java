package dev.jbriggs.aoc.days.d4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Time slot checker test")
class TimeSlotCheckerTest {

  TimeSlotChecker timeSlotChecker = new TimeSlotChecker();

  @Nested
  @DisplayName("Fully contain tests")
  class FullContainTests {
    @Test
    @DisplayName("Should return false when time slots dont overlap")
    void shouldReturnFalseWhenTimeSlotsDontOverlap() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(2).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(3).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return false if timeslots do not fully overlap",
          result, is(false));
    }

    @Test
    @DisplayName("Should return false if time slot A contains B but not completely")
    void shouldReturnFalseIfTimeSlotAContainsBButNotCompletely() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return false if timeslots do not fully overlap",
          result, is(false));
    }

    @Test
    @DisplayName("Should return false if time slot B contains A but not completely")
    void shouldReturnFalseIfTimeSlotBContainsAButNotCompletely() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return false if timeslots do not fully overlap",
          result, is(false));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B")
    void shouldReturnTrueIfTimeSlotAFullyContainsB() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A")
    void shouldReturnTrueIfTimeSlotBFullyContainsA() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B inclusive start")
    void shouldReturnTrueIfTimeSlotAFullyContainsBInclusiveStart() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A inclusive start")
    void shouldReturnTrueIfTimeSlotBFullyContainsAInclusiveStart() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B inclusive end")
    void shouldReturnTrueIfTimeSlotAFullyContainsBInclusiveEnd() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A inclusive end")
    void shouldReturnTrueIfTimeSlotBFullyContainsAInclusiveEnd() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsFullyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }


  }

  @Nested
  @DisplayName("Partially contain tests")
  class PartiallyContainTests {
    @Test
    @DisplayName("Should return false when time slots dont overlap")
    void shouldReturnFalseWhenTimeSlotsDontOverlap() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(2).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(3).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return false if timeslots do not fully overlap",
          result, is(false));
    }

    @Test
    @DisplayName("Should return true if time slot A contains B but not completely")
    void shouldReturnFalseIfTimeSlotAContainsBButNotCompletely() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots do overlap",
          result, is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B contains A but not completely")
    void shouldReturnFalseIfTimeSlotBContainsAButNotCompletely() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots do fully overlap",
          result, is(true));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B")
    void shouldReturnTrueIfTimeSlotAFullyContainsB() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A")
    void shouldReturnTrueIfTimeSlotBFullyContainsA() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B inclusive start")
    void shouldReturnTrueIfTimeSlotAFullyContainsBInclusiveStart() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(3).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A inclusive start")
    void shouldReturnTrueIfTimeSlotBFullyContainsAInclusiveStart() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(3).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot A fully contains B inclusive end")
    void shouldReturnTrueIfTimeSlotAFullyContainsBInclusiveEnd() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(1).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(2).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }

    @Test
    @DisplayName("Should return true if time slot B fully contains A inclusive end")
    void shouldReturnTrueIfTimeSlotBFullyContainsAInclusiveEnd() {
      // Given
      TimeSlot timeSlotA = TimeSlot.builder().startTime(2).endTime(4).build();
      TimeSlot timeSlotB = TimeSlot.builder().startTime(1).endTime(4).build();
      // When
      boolean result = timeSlotChecker.doTimeslotsPartiallyContainTheOther(
          timeSlotA, timeSlotB);
      // Then
      assertThat("Should return true if timeslots fully overlap", result,
          is(true));
    }


  }

}