package dev.jbriggs.aoc.days.d2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DisplayName("Rock paper scissors score finder test")
class RockPaperScissorsScoreFinderTest {

  RockPaperScissorsScoreFinder finder = new RockPaperScissorsScoreFinder();

  @Nested
  @DisplayName("Draw tests")
  class drawTests{
    @Test
    @DisplayName("Should return 3 for draw with rock")
    public void shouldReturn3ForDrawWithRock(){
      // Given
      RPSEnum opponent = RPSEnum.ROCK;
      RPSEnum me = RPSEnum.ROCK;
      // When
      long result = finder.getScore(opponent, me);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }

    @Test
    @DisplayName("Should return 3 for draw with paper")
    public void shouldReturn3ForDrawWithPaper(){
      // Given
      RPSEnum opponent = RPSEnum.PAPER;
      RPSEnum me = RPSEnum.PAPER;
      // When
      long result = finder.getScore(opponent, me);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }

    @Test
    @DisplayName("Should return 3 for draw with scissors")
    public void shouldReturn3ForDrawWithScissors(){
      // Given
      RPSEnum opponent = RPSEnum.SCISSORS;
      RPSEnum me = RPSEnum.SCISSORS;
      // When
      long result = finder.getScore(opponent, me);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }
  }

}