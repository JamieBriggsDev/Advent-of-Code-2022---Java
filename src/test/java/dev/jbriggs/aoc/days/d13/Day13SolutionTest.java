package dev.jbriggs.aoc.days.d13;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.days.d12.Day12Solution;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 13 solution test")
class Day13SolutionTest {

  private PuzzleInputParser puzzleInputParser;
  Day13Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day13Solution(puzzleInputParser, "testdata/day13/input.txt");
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {
    @Test
    @DisplayName("Should return 13 for test input")
    void shouldReturn13ForTestInput() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 13", result, is("13"));
    }
  }

  @Nested
  @DisplayName("Part two tests")
  class PartTwoTests {
    @Test
    @DisplayName("Should return 140 for test input")
    void shouldReturn140ForTestInput() {
      // Given
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 140", result, is("140"));
    }
  }
}