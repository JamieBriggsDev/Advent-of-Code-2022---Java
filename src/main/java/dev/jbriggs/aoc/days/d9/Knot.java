package dev.jbriggs.aoc.days.d9;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.AOCException;
import dev.jbriggs.aoc.core.Counter;
import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.util.VectorPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@SuperBuilder
public class Knot extends Vector2 {

  private final int index;
  private Knot childKnot;

  public Knot(int x, int y, int index) {
    super(x, y);
    this.index = index;
  }

  public Knot(Vector2 other) {
    super(other);
    this.index = 0;
  }

  public void moveAll(Direction direction, int places) {
    moveAll(direction, places, null, null);
  }

  public void moveAll(Direction direction, int places,
      Counter<Vector2> rememberedTailsPositions, Knot tailToRemember) {
    for (int i = 0; i < places; i++) {
      // Direction.LEFT.equals(direction) && places == 8 && i == 3
      move(direction, 1);
      if (!isNull(childKnot)) {
        moveChildren(direction);
      }
/*
      log.info("\n" + VectorPrinter.toString(getAllLocations()));
*/
      if (!isNull(rememberedTailsPositions) && !isNull(tailToRemember)) {
        rememberedTailsPositions.countItem(new Vector2(tailToRemember));
      }

      if (this.getAllLocations().size() == 10) {
        log.info("""
            {direction} {places} - {step}
            {map}""".replace("{direction}", direction.getCode())
            .replace("{places}", String.valueOf(places))
            .replace("{step}", String.valueOf(i)).replace("{map}",
                VectorPrinter.toString(getAllLocations())));
      }
    }
  }

  public void moveChildren(Direction initialDirection) {
    if (!isNull(childKnot)) {
      directionsToMoveTail(initialDirection).forEach(
          newDirection -> {
            childKnot.move(newDirection, 1);
          });
      childKnot.moveChildren(initialDirection);
      if (!this.isTouching(childKnot)) {
        log.info("Not touching child knot");
      }
    }
  }

  private List<Direction> directionsToMoveTail(Direction initialDirection) {
    List<Direction> directions = new ArrayList<>();
    if (!this.isTouching(childKnot)) {
      directions.add(initialDirection);
      int xDistance = this.getX() - childKnot.getX();
      int yDistance = this.getY() - childKnot.getY();
      if (Math.abs(xDistance * yDistance) >= 1) {
        boolean isVerticalDirection = Arrays.asList(Direction.UP,
            Direction.DOWN).contains(initialDirection);
        boolean isHorizontalDirection = Arrays.asList(Direction.LEFT,
            Direction.RIGHT).contains(initialDirection);
        if (isVerticalDirection && xDistance > 0) {
          directions.add(Direction.RIGHT);
        } else if (isVerticalDirection && xDistance < 0) {
          directions.add(Direction.LEFT);
        } else if (isHorizontalDirection && yDistance > 0) {
          directions.add(Direction.UP);
        } else {
          directions.add(Direction.DOWN);
        }
      }
    }
    return directions;
  }

  public List<Vector2> getAllLocations() {
    List<Vector2> locations = new ArrayList<>();
    locations.add(this);
    Knot currentlyLookingAt = this;
    while (!isNull(currentlyLookingAt.getChildKnot())) {
      currentlyLookingAt = currentlyLookingAt.getChildKnot();
      locations.add(currentlyLookingAt);
    }
    return locations;
  }
}
