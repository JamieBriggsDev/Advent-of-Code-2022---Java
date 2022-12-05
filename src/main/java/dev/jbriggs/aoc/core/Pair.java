package dev.jbriggs.aoc.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair<T>{
  private T A;
  private T B;
}
