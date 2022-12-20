package dev.jbriggs.aoc.days.d14;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Day14SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  Day14Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day14Solution(puzzleInputParser, "testdata/day14/input.txt");
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {
    @Test
    @DisplayName("Should return 24 for test input")
    void shouldReturn24ForTestInput() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 24", result, is("24"));
    }
  }
}