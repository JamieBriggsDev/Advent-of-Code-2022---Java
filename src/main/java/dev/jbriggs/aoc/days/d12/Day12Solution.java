package dev.jbriggs.aoc.days.d12;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.days.d11.KeepAwayGame;
import dev.jbriggs.aoc.pathfinding.GraphNode;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import dev.jbriggs.aoc.util.VectorPrinter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day12Solution extends Day {

  public static final int DAY_NUMBER = 12;

  public Day12Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.12.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  @SneakyThrows
  @Override
  protected String partOne(List<String> input) {
    HeightMap heightMap = new HeightMap(input);
    List<GraphNode> shortestPath = heightMap.getShortestPath();
    log.debug("\n" + VectorPrinter.toString(
        shortestPath.stream().map(MountainNode.class::cast)
            .map(MountainNode::getPosition).collect(Collectors.toList())));
    return String.valueOf(shortestPath.size() - 1);
  }

  @SneakyThrows
  @Override
  protected String partTwo(List<String> input) {
    HeightMap heightMap = new HeightMap(input, "a");
    List<GraphNode> shortestPath = heightMap.getShortestPath();
    log.debug("\n" + VectorPrinter.toString(
        shortestPath.stream().map(MountainNode.class::cast)
            .map(MountainNode::getPosition).collect(Collectors.toList())));
    return String.valueOf(shortestPath.size() - 1);
  }


}
