package dev.jbriggs.aoc.days.d1;

import java.util.Collection;

public class CalorieCounter {

  public Long countCalories(Collection<String> emptyList) {
    return emptyList.stream().mapToLong(Long::parseLong).sum();
  }
}
