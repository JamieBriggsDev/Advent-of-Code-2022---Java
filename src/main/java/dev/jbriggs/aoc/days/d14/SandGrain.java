package dev.jbriggs.aoc.days.d14;

import dev.jbriggs.aoc.core.Collidable;
import dev.jbriggs.aoc.core.Vector2;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SandGrain extends Vector2 implements Collidable<Vector2> {
  public SandGrain(int x, int y) {
    super(x, y);
  }
  public SandGrain(Vector2 other) {
    super(other);
  }
  @Override
  public boolean collidesWith(Vector2 other) {
    return this.equals(other);
  }
}
