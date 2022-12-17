package dev.jbriggs.aoc.days.d12;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 12 solution test")
class Day12SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  Day12Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day12Solution(puzzleInputParser, "testdata/day12/input.txt");
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {
    @Test
    @DisplayName("Should return 31 for test input")
    void shouldReturn31ForTestInput() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 31", result, is("31"));
    }
  }

  @Nested
  @DisplayName("Part two tests")
  class PartTwoTests {
    @Test
    @DisplayName("Should return 29 for test input")
    void shouldReturn29ForTestInput() {
      // Given
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 29", result, is("29"));
    }
  }
}