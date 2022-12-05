package dev.jbriggs.aoc.days.d4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Day 4 Solution Test")
class Day4SolutionTest {
  private PuzzleInputParser puzzleInputParser;
  private Day4Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day4Solution(puzzleInputParser, "testdata/day4/input.txt");
  }

  @Test
  @DisplayName("Should return correct answer for part one")
  void shouldReturnCorrectAnswerForPartOne() {
    // Given
    // When
    String result = solution.partOneAnswer();
    // Then
    assertThat(result, is("2"));
  }

  @Test
  @DisplayName("Should return correct answer for part two")
  void shouldReturnCorrectAnswerForPartTwo() {
    // Given
    // When
    String result = solution.partTwoAnswer();
    // Then
    assertThat(result, is("4"));
  }

}