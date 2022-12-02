package dev.jbriggs.aoc.days.d2;

import java.util.Arrays;

public enum RPSEnum {
  ROCK("A", "X", 1),
  PAPER("B", "Y", 2),
  SCISSORS("C", "Z", 3);

  private final String playerACode;
  private final String playerBCode;
  private final int points;

  public String getPlayerACode() {
    return playerACode;
  }

  public String getPlayerBCode() {
    return playerBCode;
  }

  public int getPoints() {
    return points;
  }

  RPSEnum(String playerACode, String playerBCode, int points) {
    this.playerACode = playerACode;
    this.playerBCode = playerBCode;
    this.points = points;
  }

  public static RPSEnum fromPlayerOneCode(String code) {
    return Arrays.stream(values()).filter(x -> x.getPlayerACode().equals(code)).findFirst()
        .orElse(null);
  }

  public static RPSEnum fromPlayerTwoCode(String code) {
    return Arrays.stream(values()).filter(x -> x.getPlayerBCode().equals(code)).findFirst()
        .orElse(null);
  }

  
}
