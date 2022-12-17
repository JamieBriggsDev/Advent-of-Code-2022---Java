package dev.jbriggs.aoc.days.d11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 11 solution test")
class Day11SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  Day11Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day11Solution(puzzleInputParser, "testdata/day11/input.txt");
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {
    @Test
    @DisplayName("Should return 10605 for test input")
    void shouldReturn10605ForTestInput() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 10605", result, is("10605"));
    }
  }

  @Nested
  @Disabled(value = "Not finished")
  @DisplayName("Part two tests")
  class PartTwoTests {
    @Test
    @DisplayName("Should return 2713310158 for test input")
    void shouldReturn2713310158ForTestInput() {
      // Given
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 2713310158", result, is("2713310158"));
    }
  }
}