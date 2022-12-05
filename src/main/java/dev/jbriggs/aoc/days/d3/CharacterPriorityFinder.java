package dev.jbriggs.aoc.days.d3;

public class CharacterPriorityFinder {
  public static final int LOWER_CASE_BOUNDARY = 96;
  public static final int UPPER_CASE_BOUNDARY = 38;

  public int findPriority(char letter) {
    int result = letter - LOWER_CASE_BOUNDARY;
    if (result < 0){
      return letter - UPPER_CASE_BOUNDARY;
    }
    return result;
  }
}
