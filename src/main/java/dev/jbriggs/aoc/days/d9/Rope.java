package dev.jbriggs.aoc.days.d9;

import dev.jbriggs.aoc.AOCException;
import dev.jbriggs.aoc.core.Counter;
import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.util.VectorPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
@AllArgsConstructor
public class Rope {

  private Vector2 head;
  private Vector2 tail;
  @Builder.Default
  private Counter<Vector2> rememberedTailsPositions = new Counter<>();

  public Rope() {
    head = new Vector2(0, 0);
    tail = new Vector2(0, 0);
    rememberedTailsPositions = new Counter<>();
    rememberedTailsPositions.countItem(new Vector2(tail));
  }

  public void move(Direction direction, int places) {
    for (int x = 0; x < places; x++) {
      head.move(direction, 1);
      directionsToMoveTail(direction).forEach(d -> {
        tail.move(d, 1);
        if(!head.isTouching(tail)){
          throw new AOCException("Tail is not touching head!");
        }
      });
      rememberedTailsPositions.countItem(new Vector2(tail));

    }
  }


  private List<Direction> directionsToMoveTail(Direction initialDirection) {
    List<Direction> directions = new ArrayList<>();
    if (!head.isTouching(tail)) {
      directions.add(initialDirection);
      int xDistance = head.getX() - tail.getX();
      int yDistance = head.getY() - tail.getY();
      if (Math.abs(xDistance * yDistance) >= 1) {
        boolean isVerticalDirection = Arrays.asList(Direction.UP,
                Direction.DOWN)
            .contains(initialDirection);
        boolean isHorizontalDirection = Arrays.asList(Direction.LEFT,
                Direction.RIGHT)
            .contains(initialDirection);
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
}
