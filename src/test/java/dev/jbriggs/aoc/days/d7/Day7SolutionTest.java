package dev.jbriggs.aoc.days.d7;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.storage.TerminalReader;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 7 solution test")
class Day7SolutionTest {

  private Device device;
  private PuzzleInputParser puzzleInputParser;
  private Day7Solution solution;

  @BeforeEach
  public void beforeEach() {
    device = new Device(new TerminalReader());
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day7Solution(puzzleInputParser, "testdata/day7/input.txt", device);
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {


    @Test
    @DisplayName("Should return 95437 for test input")
    void shouldReturn5ForTestInput1() {
      // Given
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 95437", result, is("95437"));
    }

    @Test
    @DisplayName("Should return 95437 for test input 2")
    void shouldReturn5ForTestInput2() {
      // Given
      solution = new Day7Solution(puzzleInputParser, "testdata/day7/input2.txt", device);
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 95437", result, is("95437"));
    }
  }
}