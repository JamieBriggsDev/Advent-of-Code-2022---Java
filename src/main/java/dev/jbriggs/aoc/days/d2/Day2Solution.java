package dev.jbriggs.aoc.days.d2;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day2Solution extends Day {

  private final RockPaperScissorsScoreFinder rockPaperScissorsScoreFinder;

  public Day2Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.2.input}") String inputPath) {
    super(2, puzzleInputParser, inputPath);
    rockPaperScissorsScoreFinder = new RockPaperScissorsScoreFinder();
  }

  @Override
  protected String partOne(List<String> input) {
    return String.valueOf(input.stream().map(x -> x.split(" ")).mapToLong(
            x -> {
              RPSEnum opponent = RPSEnum.fromPlayerOneCode(x[0]);
              RPSEnum me = RPSEnum.fromPlayerTwoCode(x[1]);
              return rockPaperScissorsScoreFinder.getScore(
                  me, opponent) + me.getUsagePoints();
            })
        .sum());
  }

  @Override
  protected String partTwo(List<String> input) {
    return String.valueOf(input.stream().map(x -> x.split(" ")).mapToLong(
            x -> {
              RPSEnum opponent = RPSEnum.fromPlayerOneCode(x[0]);
              RPSResultEnum result = RPSResultEnum.fromResultCode(x[1]);
              RPSEnum me = null;
              if(RPSResultEnum.WIN == result){
                me = opponent.getLosingAgainstValue();
              }else if(RPSResultEnum.LOSE == result){
                me = opponent.getWinningAgainstValue();
              }else{
                me = opponent;
              }
              return rockPaperScissorsScoreFinder.getScore(
                  me, opponent) + me.getUsagePoints();
            })
        .sum());
  }

}
