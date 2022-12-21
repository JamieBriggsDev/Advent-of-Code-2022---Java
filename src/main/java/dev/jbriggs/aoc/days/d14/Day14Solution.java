package dev.jbriggs.aoc.days.d14;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.core.Collidable;
import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import dev.jbriggs.aoc.util.VectorPrinter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day14Solution extends Day {

  public static final int DAY_NUMBER = 14;

  public Day14Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.14.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @SneakyThrows
  @Override
  protected Object partOne(List<String> input) {
    CollidableCollection collidableCollection = new CollidableCollection();
    for (String row : input) {
      collidableCollection.add(new Wall(row));
    }
    Cave cave = new Cave(collidableCollection);
    int steps = 0;
    boolean stillLooping = true;
    while (stillLooping) {
      if (!cave.dropSand()) {
        stillLooping = false;
      } else {
        steps++;
      }
    }
    return steps;
  }

  @SneakyThrows
  @Override
  protected Object partTwo(List<String> input) {
    CollidableCollection collidableCollection = new CollidableCollection();
    for (String row : input) {
      collidableCollection.add(new Wall(row));
    }
    int lowest = collidableCollection.getCollidableList().stream()
        .map(Wall.class::cast).mapToInt(Wall::getLowestPoint).max().orElse(0);
    collidableCollection.add(new Wall(
        Arrays.asList(new Vector2(Integer.MIN_VALUE, lowest + 2),
            new Vector2(Integer.MAX_VALUE, lowest + 2))));
    Cave cave = new Cave(collidableCollection);

    int steps = 0;
    boolean stillLooping = true;
    while (stillLooping) {
      cave.dropSand(false);
      if (cave.getCollidableCollection().stream()
          .filter(x -> x instanceof SandGrain).map(SandGrain.class::cast)
          .anyMatch(x -> x.getX() == 500 && x.getY() == 0)) {
        stillLooping = false;
      } else {
        steps++;
      }
    }
    List<Collidable<Vector2>> collect = cave.getCollidableCollection().stream()
        .filter(x -> x instanceof SandGrain).filter(x -> ((SandGrain) x).getY() < cave.getLowestYValue()).collect(
            Collectors.toList());
    log.debug("\n" + VectorPrinter.toString(collect.stream().map(x -> (SandGrain) x).collect(Collectors.toList())));
    return collect.size();
  }


}
