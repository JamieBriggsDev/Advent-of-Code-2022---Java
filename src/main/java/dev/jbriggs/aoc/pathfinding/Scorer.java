package dev.jbriggs.aoc.pathfinding;

import dev.jbriggs.aoc.core.Node;

public interface Scorer<T extends GraphNode> {
  double computeCost(T from, T to);

}
