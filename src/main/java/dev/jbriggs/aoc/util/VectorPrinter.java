package dev.jbriggs.aoc.util;

import dev.jbriggs.aoc.core.Vector2;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class VectorPrinter {

  public static String toString(List<Vector2> list) {
    int xMin = list.stream().mapToInt(Vector2::getX).min().orElse(0);
    int xMax = list.stream().mapToInt(Vector2::getX).max().orElse(0);
    int yMin = list.stream().mapToInt(Vector2::getY).min().orElse(0);
    int yMax = list.stream().mapToInt(Vector2::getY).max().orElse(0);
    StringBuilder stringBuilder = new StringBuilder();
    for (int y = yMax; y >= yMin; y--) {
      for (int x = xMin; x <= xMax; x++) {
        if(list.contains(new Vector2(x, y))){
          stringBuilder.append("#");
        }else {
          stringBuilder.append(".");
        }
      }
          stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
  public static String toString(Vector2... vector2s) {
    return toString(Arrays.stream(vector2s).toList());
  }
}
