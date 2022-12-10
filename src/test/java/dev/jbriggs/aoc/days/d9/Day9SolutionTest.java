package dev.jbriggs.aoc.days.d9;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 9 solution test")
class Day9SolutionTest {

  private PuzzleInputParser puzzleInputParser;
  private Day9Solution solution;

  @BeforeEach
  public void beforeEach() {
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day9Solution(puzzleInputParser, "testdata/day9/input.txt");
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {


    @Test
    @DisplayName("Should return 13 for test input")
    void shouldReturn5ForTestInput1() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 13", result, is("13"));
    }


  }
}