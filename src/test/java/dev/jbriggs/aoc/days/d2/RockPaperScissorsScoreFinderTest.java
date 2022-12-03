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
    void shouldReturn3ForDrawWithRock(){
      // Given
      RPSEnum me = RPSEnum.ROCK;
      RPSEnum opponent = RPSEnum.ROCK;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }

    @Test
    @DisplayName("Should return 3 for draw with paper")
    public void shouldReturn3ForDrawWithPaper(){
      // Given
      RPSEnum me = RPSEnum.PAPER;
      RPSEnum opponent = RPSEnum.PAPER;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }

    @Test
    @DisplayName("Should return 3 for draw with scissors")
    void shouldReturn3ForDrawWithScissors(){
      // Given
      RPSEnum me = RPSEnum.SCISSORS;
      RPSEnum opponent = RPSEnum.SCISSORS;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 3 for draw", result, is(3L));
    }
  }

  @Nested
  @DisplayName("Win tests")
  class winTests{
    @Test
    @DisplayName("Should return 6 for rock beating scissors")
    void shouldReturn6ForRockBeatingScissors(){
      // Given
      RPSEnum me = RPSEnum.ROCK;
      RPSEnum opponent = RPSEnum.SCISSORS;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 6 for win", result, is(6L));
    }

    @Test
    @DisplayName("Should return 6 for paper beating rock")
    void shouldReturn6ForPaperBeatingRock(){
      // Given
      RPSEnum me = RPSEnum.PAPER;
      RPSEnum opponent = RPSEnum.ROCK;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 6 for win", result, is(6L));
    }

    @Test
    @DisplayName("Should return 6 for scissors beating paper")
    void shouldReturn6FoScissorsBeatingPaper(){
      // Given
      RPSEnum me = RPSEnum.SCISSORS;
      RPSEnum opponent = RPSEnum.PAPER;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be 6 for win", result, is(6L));
    }
  }

  @Nested
  @DisplayName("Loses tests")
  class losesTests{
    @Test
    @DisplayName("Should return 0 for rock losing against paper")
    void shouldReturn6ForRockBeatingScissors(){
      // Given
      RPSEnum me = RPSEnum.ROCK;
      RPSEnum opponent = RPSEnum.PAPER;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be for loss", result, is(0L));
    }

    @Test
    @DisplayName("Should return 0 for paper losing against scissors")
    void shouldReturn6ForPaperLosingAgainstScissors(){
      // Given
      RPSEnum me = RPSEnum.PAPER;
      RPSEnum opponent = RPSEnum.SCISSORS;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be for loss", result, is(0L));
    }

    @Test
    @DisplayName("Should return 0 for scisssors losing against rock")
    void shouldReturn6ForScissorsLosingAgainstRock(){
      // Given
      RPSEnum me = RPSEnum.SCISSORS;
      RPSEnum opponent = RPSEnum.ROCK;
      // When
      long result = finder.getScore(me, opponent);
      // Then
      assertThat("Result should be for loss", result, is(0L));
    }
  }

}