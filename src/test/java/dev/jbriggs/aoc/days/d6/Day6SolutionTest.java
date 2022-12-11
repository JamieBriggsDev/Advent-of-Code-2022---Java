package dev.jbriggs.aoc.days.d6;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 6 solution test")
class Day6SolutionTest {

  private Device device;
  private PuzzleInputParser puzzleInputParser;
  private Day6Solution solution;

  @BeforeEach
  public void beforeEach() {
    device = new Device(new TerminalReader(new TerminalStorage()));
    puzzleInputParser = new PuzzleInputParser();
  }

  @Nested
  @DisplayName("Part one tests")
  class PartOneTests {


    @Test
    @DisplayName("Should return 5 for test input 1")
    void shouldReturn5ForTestInput1() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input1.txt",
          device);
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 5", result, is("5"));
    }

    @Test
    @DisplayName("Should return 6 for test input 2")
    void shouldReturn6ForTestInput2() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input2.txt",
          device);
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 6", result, is("6"));
    }

    @Test
    @DisplayName("Should return 10 for test input 3")
    void shouldReturn10ForTestInput3() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input3.txt",
          device);
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 10", result, is("10"));
    }

    @Test
    @DisplayName("Should return 11 for test input 4")
    void shouldReturn11ForTestInput4() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input4.txt",
          device);
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 11", result, is("11"));
    }
  }

  @Nested
  @DisplayName("Part two tests")
  class PartTwoTests {
    @Test
    @DisplayName("Should return 19 for initial test input")
    void shouldReturn19ForInitialTestInput() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input0.txt",
          device);
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 19", result, is("19"));
    }

    @Test
    @DisplayName("Should return 23 for test input 1")
    void shouldReturn5ForTestInput1() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input1.txt",
          device);
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 23", result, is("23"));
    }

    @Test
    @DisplayName("Should return 23 for test input 2")
    void shouldReturn23ForTestInput2() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input2.txt",
          device);
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 23", result, is("23"));
    }

    @Test
    @DisplayName("Should return 29 for test input 3")
    void shouldReturn29ForTestInput3() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input3.txt",
          device);
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 29", result, is("29"));
    }

    @Test
    @DisplayName("Should return 26 for test input 4")
    void shouldReturn26ForTestInput4() {
      // Given
      solution = new Day6Solution(puzzleInputParser, "testdata/day6/input4.txt",
          device);
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 26", result, is("26"));
    }
  }

}