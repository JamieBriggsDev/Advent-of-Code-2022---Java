package dev.jbriggs.aoc.days.d8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.storage.TerminalReader;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 8 solution test")
class Day8SolutionTest {

  private Device device;
  private PuzzleInputParser puzzleInputParser;
  private Day8Solution solution;

  @BeforeEach
  public void beforeEach() {
    device = new Device(new TerminalReader());
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day8Solution(puzzleInputParser, "testdata/day8/input.txt", device);
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {


    @Test
    @DisplayName("Should return 21 for test input")
    void shouldReturn5ForTestInput1() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 21", result, is("21"));
    }


  }

  @Nested
  @DisplayName("Part two tests")
  class PartTwoTests {


    @Test
    @DisplayName("Should return 21 for test input")
    void shouldReturn5ForTestInput1() {
      // Given
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 21", result, is("21"));
    }


  }
}