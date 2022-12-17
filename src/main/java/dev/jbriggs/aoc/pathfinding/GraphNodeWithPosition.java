package dev.jbriggs.aoc.pathfinding;

import dev.jbriggs.aoc.core.Vector2;

public interface GraphNodeWithPosition extends GraphNode {

  Vector2 getPosition();

  default int heuristicDistance(GraphNodeWithPosition other) {
    return Math.abs(this.getPosition().getX() - other.getPosition().getX())
        + Math.abs(this.getPosition().getY() - other.getPosition().getY());
  }
}
