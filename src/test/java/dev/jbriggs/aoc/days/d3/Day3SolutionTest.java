package dev.jbriggs.aoc.days.d3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Day 3 Solution Test")
class Day3SolutionTest {

  private PuzzleInputParser puzzleInputParser;
  private Day3Solution solution;

  @BeforeEach
  public void beforeEach() {
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day3Solution(puzzleInputParser, "testdata/day3/input.txt");
  }


  @Test
  @DisplayName("Should return correct answer for part one")
  void shouldReturnCorrectAnswerForPartOne() {
    // Given
    // When
    String result = solution.partOneAnswer();
    // Then
    assertThat(result, is("157"));
  }

  @Test
  @DisplayName("Should return correct answer for part two")
  void shouldReturnCorrectAnswerForPartTwo() {
    // Given
    // When
    String result = solution.partTwoAnswer();
    // Then
    assertThat(result, is("70"));
  }
}