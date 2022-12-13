package dev.jbriggs.aoc.days.d11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Monkey divisible test test")
class MonkeyDivisibleTestTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize correctly")
    void shouldInitializeCorrectly() {
      // Given
      MonkeyDivisibleTest monkeyDivisibleTest = new MonkeyDivisibleTest(23, 2,
          3);
      // Then
      assertThat("Denominator value should be 23",
          monkeyDivisibleTest.denominatorValue(), is(23));
      assertThat("True monkey ID should be 2",
          monkeyDivisibleTest.trueMonkeyId(), is(2));
      assertThat("False monkey ID should be 3",
          monkeyDivisibleTest.falseMonkeyId(), is(3));
    }
  }

  @Nested
  @DisplayName("Perform test test")
  class PerformTestTests {

    @Test
    @DisplayName("Should return true monkey ID when test passes")
    void shouldReturnTrueMonkeyIdWhenTestPasses() {
      // When
      MonkeyDivisibleTest monkeyDivisibleTest = MonkeyDivisibleTest.builder()
          .denominatorValue(2).trueMonkeyId(1).falseMonkeyId(3).build();
      // Given
      int result = monkeyDivisibleTest.test(6L);
      // Then
      assertThat("Result should be 1", result, is(1));
    }

    @Test
    @DisplayName("Should return false monkey ID when test fails")
    void shouldReturnFalseMonkeyIdWhenTestPasses() {
      // When
      MonkeyDivisibleTest monkeyDivisibleTest = MonkeyDivisibleTest.builder()
          .denominatorValue(2).trueMonkeyId(1).falseMonkeyId(3).build();
      // Given
      int result = monkeyDivisibleTest.test(7L);
      // Then
      assertThat("Result should be 3", result, is(3));
    }
  }
}