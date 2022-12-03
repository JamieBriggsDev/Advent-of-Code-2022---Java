package dev.jbriggs.aoc.days.d2;

import java.util.Arrays;

public enum RPSResultEnum {
  LOSE("X"),
  DRAW("Y"),
  WIN("Z");

  private final String resultCode;

  public String getResultCode() {
    return resultCode;
  }

  RPSResultEnum(String resultCode) {
    this.resultCode = resultCode;
  }

  public static RPSResultEnum fromResultCode(String code) {
    return Arrays.stream(values()).filter(x -> x.getResultCode().equals(code))
        .findFirst().orElse(null);
  }

}
