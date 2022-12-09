package dev.jbriggs.aoc.days.d8;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Tree {

  private final int height;

  public Tree(int height) {
    this.height = height;
  }

  public boolean isHiddenBy(Tree other){
    return this.height <= other.getHeight();
  }

  @Override
  public String toString() {
    return String.valueOf(height);
  }
}
