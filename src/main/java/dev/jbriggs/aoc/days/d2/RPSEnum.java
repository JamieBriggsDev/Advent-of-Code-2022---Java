package dev.jbriggs.aoc.days.d2;

import java.util.Arrays;

public enum RPSEnum {
  ROCK("A", "X", 1), PAPER("B", "Y", 2), SCISSORS("C", "Z", 3);

  private final String playerACode;
  private final String playerBCode;
  private final int usagePoints;

  public String getPlayerACode() {
    return playerACode;
  }

  public String getPlayerBCode() {
    return playerBCode;
  }

  public int getUsagePoints() {
    return usagePoints;
  }

  RPSEnum(String playerACode, String playerBCode, int usagePoints) {
    this.playerACode = playerACode;
    this.playerBCode = playerBCode;
    this.usagePoints = usagePoints;
  }

  public static RPSEnum fromPlayerOneCode(String code) {
    return Arrays.stream(values()).filter(x -> x.getPlayerACode().equals(code))
        .findFirst().orElse(null);
  }

  public static RPSEnum fromPlayerTwoCode(String code) {
    return Arrays.stream(values()).filter(x -> x.getPlayerBCode().equals(code))
        .findFirst().orElse(null);
  }

  public RPSEnum getLosingAgainstValue() {
    return switch (this) {
      case ROCK -> PAPER;
      case PAPER -> SCISSORS;
      case SCISSORS -> ROCK;
    };
  }

  public RPSEnum getWinningAgainstValue() {
    return switch (this) {
      case ROCK -> SCISSORS;
      case PAPER -> ROCK;
      case SCISSORS -> PAPER;
    };
  }


}
