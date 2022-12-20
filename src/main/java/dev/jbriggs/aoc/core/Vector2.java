package dev.jbriggs.aoc.core;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
public class Vector2 {

  private int x;
  private int y;

  public Vector2(Vector2 other) {
    this.x = other.x;
    this.y = other.y;
  }

  public void setNewValues(Vector2 other){
    this.x = other.getX();
    this.y = other.getY();
  }

  public void move(Direction direction, int places) {
    switch (direction) {
      case UP -> moveUp(places);
      case DOWN -> moveDown(places);
      case RIGHT -> moveRight(places);
      case LEFT -> moveLeft(places);
    }
  }

  private void moveUp(int places) {
    this.y += places;
  }

  private void moveDown(int places) {
    this.y -= places;
  }

  private void moveRight(int places) {
    this.x += places;
  }

  private void moveLeft(int places) {
    this.x -= places;
  }

  @Override
  public String toString() {
    return "(%d,%d)".formatted(x, y);
  }

  public boolean isTouching(Vector2 other) {
    int xDistance = this.getX() - other.getX();
    int yDistance = this.getY() - other.getY();
    return !(Math.abs(xDistance) > 1 || Math.abs(yDistance) > 1);
  }

  public double distanceTo(Vector2 other) {
    return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(
        this.getY() - other.getY(), 2));
  }
}
