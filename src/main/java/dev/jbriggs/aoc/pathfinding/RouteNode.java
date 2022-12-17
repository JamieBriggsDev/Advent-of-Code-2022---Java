package dev.jbriggs.aoc.pathfinding;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteNode<T extends GraphNode> implements Comparable<RouteNode> {

  private final T current;
  private T previous;
  private double routeScore;
  private double estimatedScore;

  RouteNode(T current) {
    this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
  }

  RouteNode(T current, T previous, double routeScore, double estimatedScore) {
    this.current = current;
    this.previous = previous;
    this.routeScore = routeScore;
    this.estimatedScore = estimatedScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RouteNode<?> routeNode = (RouteNode<?>) o;
    return Objects.equal(current, routeNode.current);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(current);
  }

  @Override
  public int compareTo(RouteNode o) {
    return Double.compare(this.estimatedScore, o.estimatedScore);
  }
}
