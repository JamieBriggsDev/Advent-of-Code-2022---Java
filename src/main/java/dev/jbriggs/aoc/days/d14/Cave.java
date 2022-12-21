package dev.jbriggs.aoc.days.d14;

import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Cave {

  private final CollidableCollection collidableCollection;
  private final int lowestYValue;

  public Cave(CollidableCollection collidableCollection) {
    this.collidableCollection = collidableCollection;
    this.lowestYValue = this.collidableCollection.getCollidableList().stream()
        .map(Wall.class::cast).mapToInt(Wall::getLowestPoint).max().orElse(0);
  }

  public boolean dropSand(boolean hasVoid) {
    SandGrain sandGrain = new SandGrain(500, 0);
    boolean isFalling = true;
    while (isFalling) {
      if (sandGrain.getY() > lowestYValue) {
        if(!hasVoid){
          collidableCollection.add(sandGrain);
        }
        return false;
      }
      // Move down one
      Vector2 down = new Vector2(sandGrain);
      down.move(Direction.UP, 1);
      Vector2 downLeft = new Vector2(sandGrain);
      downLeft.move(Direction.UP, 1);
      downLeft.move(Direction.LEFT, 1);
      Vector2 downRight = new Vector2(sandGrain);
      downRight.move(Direction.UP, 1);
      downRight.move(Direction.RIGHT, 1);

      if (!collidableCollection.collidesWithAnyObject(down)) {
        sandGrain.setNewValues(down);
      } else if (!collidableCollection.collidesWithAnyObject(downLeft)) {
        sandGrain.setNewValues(downLeft);
      } else if (!collidableCollection.collidesWithAnyObject(downRight)) {
        sandGrain.setNewValues(downRight);
      } else {
        isFalling = false;
      }
    }
    collidableCollection.add(sandGrain);
    return true;
  }

  /**
   * @return True if sand settles, false if sand false into void
   */
  public boolean dropSand() {
    return dropSand(true);
  }
}
