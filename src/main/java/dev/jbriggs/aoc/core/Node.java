package dev.jbriggs.aoc.core;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class Node {

  private final Vector2 position;

  public Node(Vector2 position, String id) {
    this.position = position;
    this.id = id;
  }

  private String id;
  private ArrayList<Node> connectedNodes = new ArrayList<>();

  public boolean hasChildren() {
    return !isNull(connectedNodes) && !connectedNodes.isEmpty();
  }


}
