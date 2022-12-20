package dev.jbriggs.aoc.days.d14;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.core.Collidable;
import dev.jbriggs.aoc.core.Vector2;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class Wall implements Collidable<Vector2> {

  private final List<Vector2> points;

  public Wall(String rawInput) {
    points = new ArrayList<>();
    for (String point : rawInput.split(" -> ")) {
      String[] pointArray = point.split(",");
      points.add(new Vector2(Integer.parseInt(pointArray[0]),
          Integer.parseInt(pointArray[1])));
    }
  }

  public int getLowestPoint(){
    return points.stream().mapToInt(Vector2::getY).max().orElse(Integer.MIN_VALUE);
  }

  public boolean collidesWith(Vector2 position) {
    if (points.contains(position)) {
      return true;
    } else {
      for (int x = 0; x < points.size() - 1; x++) {
        Double distanceWithPosition =
            points.get(x).distanceTo(position) + points.get(x + 1)
                .distanceTo(position);
        if (distanceWithPosition.equals(
            points.get(x).distanceTo(points.get(x + 1)))) {
          return true;
        }
      }
      return false;
    }
  }


  public static class WallBuilder {

    public WallBuilder point(Vector2 point) {
      if (isNull(this.points)) {
        this.points = new ArrayList<>();
      }
      this.points.add(point);
      return this;
    }
  }
}
