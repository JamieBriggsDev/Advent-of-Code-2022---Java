package dev.jbriggs.aoc.days.d2;

public class RockPaperScissorsScoreFinder {
  public long getScore(RPSEnum me, RPSEnum opponent) {
    if(me.getWinningAgainstValue() == opponent){
      return 6L;
    }else if(me.getLosingAgainstValue() == opponent){
      return 0L;
    }else{
      return 3L;
    }
  }
}
