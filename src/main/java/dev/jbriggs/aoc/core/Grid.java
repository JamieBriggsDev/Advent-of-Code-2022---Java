package dev.jbriggs.aoc.core;

import java.lang.reflect.Array;

public class Grid<T> {

  private final int length;
  private final int height;
  T[][] items;

  public Grid(Class<T> clazz, int length, int height) {
    this.length = length;
    this.height = height;
    items = (T[][]) Array.newInstance(clazz, height, length);
  }

  public int getLength() {
    return length;
  }

  public int getHeight() {
    return height;
  }

  public void placeItem(T item, int xPosition, int yPosition) {
    items[yPosition][xPosition] = item;
  }

  public T getItem(int xPosition, int yPosition) {
    try{
      return items[yPosition][xPosition];
    }catch (ArrayIndexOutOfBoundsException e){
      return null;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < length; x++) {
        sb.append(getItem(x, y).toString());
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
