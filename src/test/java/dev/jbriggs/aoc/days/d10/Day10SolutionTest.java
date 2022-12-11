package dev.jbriggs.aoc.days.d10;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 10 solution test")
class Day10SolutionTest {

  private PuzzleInputParser puzzleInputParser;
  Day10Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day10Solution(puzzleInputParser, "testdata/day10/input.txt");
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
      assertThat("Result should be 13140", result, is("13140"));
    }
  }

}