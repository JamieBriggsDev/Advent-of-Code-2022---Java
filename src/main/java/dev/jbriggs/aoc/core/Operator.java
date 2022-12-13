package dev.jbriggs.aoc.core;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Operator {
  ADDITION("+"),
  SUBTRACT("-"),
  MULTIPLY("*"),
  DIVIDE("/");

  private final String symbol;

  Operator(String symbol) {
    this.symbol = symbol;
  }

  public static Operator getBySymbol(String symbol){
    return Arrays.stream(Operator.values())
        .filter(x -> x.getSymbol().equals(symbol)).findFirst().orElse(null);
  }
}
