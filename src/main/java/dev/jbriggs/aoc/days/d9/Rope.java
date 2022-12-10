package dev.jbriggs.aoc.days.d9;

import dev.jbriggs.aoc.core.Counter;
import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import java.util.ArrayList;
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

  private Knot head;
  private Knot tail;
  private List<Knot> knots = new ArrayList<>();
  @Builder.Default
  private Counter<Vector2> rememberedTailsPositions = new Counter<>();

  public Rope() {
    this(2);
  }

  public Rope(int length) {
    knots = new ArrayList<>();
    rememberedTailsPositions = new Counter<>();
    for (int i = 0; i < length; i++) {
      Knot knotToAdd = new Knot(0, 0, i);
      if (i == 0) {
        head = knotToAdd;
        knots.add(knotToAdd);
      } else if (i == length - 1) {
        tail = knotToAdd;
        knots.add(knotToAdd);
      } else {
        knots.add(knotToAdd);
      }
    }
    // Set child knots
    for (int i = 0; i < length - 1; i++) {
      knots.get(i).setChildKnot(knots.get(i + 1));
    }

    rememberedTailsPositions.countItem(new Vector2(tail));
  }

  public void move(Direction direction, int places) {
    head.moveAll(direction, places, rememberedTailsPositions, tail);
  }

}
