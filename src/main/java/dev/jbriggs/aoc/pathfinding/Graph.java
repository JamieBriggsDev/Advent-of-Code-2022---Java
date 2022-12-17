package dev.jbriggs.aoc.pathfinding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

@Getter
public class Graph<T extends GraphNode>{
  private final Set<T> nodes;
  private final Map<T, Set<T>> connections;

  public Graph() {
    this.nodes = new HashSet<>();
    this.connections = new HashMap<>();
  }

  public Set<T> getConnections(T node) {
    return connections.get(node);
  }

  public void addItem(T node){
    this.nodes.add(node);
    this.connections.put(node, new HashSet<>());
  }

  public void addConnection(T parent, T child){
    connections.get(parent).add(child);
  }
}
