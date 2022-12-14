package dev.jbriggs.aoc.pathfinding.route;

import dev.jbriggs.aoc.pathfinding.Graph;
import dev.jbriggs.aoc.pathfinding.GraphNode;
import dev.jbriggs.aoc.pathfinding.PathFindingException;
import dev.jbriggs.aoc.pathfinding.Scorer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AStarRouteFinder<T extends GraphNode> {

  private final Graph<T> graph;
  private final Scorer<T> nextNodeScorer;
  private final Scorer<T> targetScorer;

  public List<T> findRoute(T from, T to) throws PathFindingException {
    Queue<RouteNode<T>> openSet = new PriorityQueue<>();
    Map<T, RouteNode<T>> allNodes = new HashMap<>();

    RouteNode<T> start = new RouteNode<>(from, null, 0d,
        targetScorer.computeCost(from, to));
    openSet.add(start);
    allNodes.put(from, start);

    while (!openSet.isEmpty()) {
      RouteNode<T> next = openSet.poll();
      if (next.getCurrent().equals(to)) {
        List<T> route = new ArrayList<>();
        RouteNode<T> current = next;
        do {
          route.add(0, current.getCurrent());
          current = allNodes.get(current.getPrevious());
        } while (current != null);
        return route;
      }
      graph.getConnections(next.getCurrent()).forEach(connection -> {
        RouteNode<T> nextNode = allNodes.getOrDefault(connection,
            new RouteNode<>(connection));
        allNodes.put(connection, nextNode);

        double newScore =
            next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(),
                connection);
        if (newScore < nextNode.getRouteScore()) {
          nextNode.setPrevious(next.getCurrent());
          nextNode.setRouteScore(newScore);
          nextNode.setEstimatedScore(
              newScore + targetScorer.computeCost(connection, to));
          openSet.add(nextNode);
        }
      });
    }

    throw new PathFindingException("No route found");
  }
}
