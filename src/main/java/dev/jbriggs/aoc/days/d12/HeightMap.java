package dev.jbriggs.aoc.days.d12;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.AOCException;
import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.pathfinding.Graph;
import dev.jbriggs.aoc.pathfinding.GraphNode;
import dev.jbriggs.aoc.pathfinding.PathFindingException;
import dev.jbriggs.aoc.pathfinding.RouteFinder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class HeightMap extends Graph<MountainNode> {

  private final int length;
  private final int height;
  private final List<MountainNode> startPoint;
  private MountainNode endPoint;

  public HeightMap(List<String> rows) {
    length = rows.get(0).length();
    height = rows.size();
    startPoint = new ArrayList<>();
    initialize(rows, "S");
  }

  public HeightMap(List<String> rows, String startingId) {
    length = rows.get(0).length();
    height = rows.size();
    startPoint = new ArrayList<>();
    initialize(rows, startingId);
  }

  private void initialize(List<String> rows, String startingPointId) {

    for (int y = 0; y < height; y++) {
      char[] characters = rows.get(y).toCharArray();
      for (int x = 0; x < length; x++) {
        MountainNode mountainNode = new MountainNode(
            String.valueOf(characters[x]), new Vector2(x, y));
        addItem(mountainNode);
        if (mountainNode.getId().equals(startingPointId)) {
          startPoint.add(mountainNode);
        }
        if (mountainNode.isEnd()) {
          endPoint = mountainNode;
        }
      }
    }

    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getLength(); x++) {
        MountainNode toFindChildrenFor = getItem(x, y);
        addIfClimbable(toFindChildrenFor, getItem(x - 1, y));
        addIfClimbable(toFindChildrenFor, getItem(x + 1, y));
        addIfClimbable(toFindChildrenFor, getItem(x, y - 1));
        addIfClimbable(toFindChildrenFor, getItem(x, y + 1));
      }
    }
  }

  public MountainNode getItem(int x, int y) {
    return new ArrayList<>(getNodes()).stream()
        .filter(m -> m.getPosition().equals(new Vector2(x, y))).findFirst()
        .orElse(null);
  }

  public MountainNode getItem(String id) {
    return new ArrayList<>(getNodes()).stream()
        .filter(m -> m.getId().equals(id)).findFirst().orElse(null);
  }

  private void addIfClimbable(MountainNode toFindChildrenFor,
      MountainNode nodeToAdd) {
    if (!isNull(nodeToAdd)) {
      char parentLetter = toFindChildrenFor.getRawId();
      char nodeToAddChar = nodeToAdd.getRawId();
      if (Objects.equals(nodeToAddChar, parentLetter)
          || (int) nodeToAddChar <= parentLetter + 1) {
        addConnection(toFindChildrenFor, nodeToAdd);
      }
    }
  }

  public List<GraphNode> getShortestPath() {
    RouteFinder<MountainNode> finder = new RouteFinder<>(this,
        new HeuristicScorer(), new HeuristicScorer());
    List<List<GraphNode>> shortestPaths = new ArrayList<>();
    startPoint.forEach(sp -> {
      try {
        List<MountainNode> route = finder.findRoute(sp, endPoint);
        shortestPaths.add(route.stream().map(GraphNode.class::cast).toList());
      } catch (PathFindingException e) {
        log.debug("Path not found for " + sp);
      }
    });
    return shortestPaths.stream().min(Comparator.comparingInt(List::size))
        .stream().findFirst().orElseThrow(AOCException::new);
  }

}
