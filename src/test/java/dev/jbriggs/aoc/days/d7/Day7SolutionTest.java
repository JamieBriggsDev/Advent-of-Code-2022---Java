package dev.jbriggs.aoc.days.d7;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.Device;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Day 7 solution test")
class Day7SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  private Day7Solution solution;

  @BeforeEach
  public void beforeEach() {
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day7Solution(puzzleInputParser, "testdata/day7/input.txt");
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
      solution = new Day7Solution(puzzleInputParser, "testdata/day7/input2.txt");
      // When
      String result = solution.partOneAnswer();
      // Then
      assertThat("Result should be 95437", result, is("95437"));
    }
  }

  @Nested
  @DisplayName("Part two tests")
  class PartTwoTests {

    @Test
    @DisplayName("Should return 24933642 for test input")
    void shouldReturn24933642ForTestInput2() {
      // Given
      // When
      String result = solution.partTwoAnswer();
      // Then
      assertThat("Result should be 24933642", result, is("24933642"));
    }
  }
}