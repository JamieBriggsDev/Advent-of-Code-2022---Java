package dev.jbriggs.aoc.days.d5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Day 5 Solution Test")
class Day5SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  private Day5Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day5Solution(puzzleInputParser, "testdata/day5/input.txt");
  }

  @Test
  @DisplayName("Should return correct answer for part one")
  void shouldReturnCorrectAnswerForPartOne() {
    // Given
    // When
    String result = solution.partOneAnswer();
    // Then
    assertThat(result, is("CMZ"));
  }

  @Test
  @DisplayName("Should return correct answer for part two")
  void shouldReturnCorrectAnswerForPartTwo() {
    // Given
    // When
    String result = solution.partTwoAnswer();
    // Then
    assertThat(result, is("MCD"));
  }
}