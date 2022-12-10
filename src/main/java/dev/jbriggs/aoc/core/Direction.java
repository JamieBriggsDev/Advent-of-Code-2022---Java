package dev.jbriggs.aoc.core;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Direction {
  LEFT("L"), RIGHT("R"), UP("U"), DOWN("D");

  private final String code;

  Direction(String code) {
    this.code = code;
  }

  public static Direction fromCode(String code) {
    return Arrays.stream(Direction.values())
        .filter(x -> x.getCode().equals(code)).findFirst().orElse(null);
  }
}
