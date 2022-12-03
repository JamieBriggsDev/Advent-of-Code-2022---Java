package dev.jbriggs.aoc.days.d2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Day 2 Solution Test")
class Day2SolutionTest {

  private PuzzleInputParser puzzleInputParser;
  private Day2Solution solution;

  @BeforeEach
  public void beforeEach(){
    puzzleInputParser = new PuzzleInputParser();
    solution = new Day2Solution(puzzleInputParser, "testdata/day2/input.txt");
  }

  @Test
  @DisplayName("Should get correct answer for part one with test input")
  void shouldGetCorrectAnswerForPartOneWithTestInput(){
    // Given
    // When
    String result = solution.partOneAnswer();
    // Then
    assertThat(result, is("15"));
  }

  @Test
  @DisplayName("Should get correct answer for part two with test input")
  void shouldGetCorrectAnswerForPartTwoWithTestInput(){
    // Given
    // When
    String result = solution.partTwoAnswer();
    // Then
    assertThat(result, is("12"));
  }
}