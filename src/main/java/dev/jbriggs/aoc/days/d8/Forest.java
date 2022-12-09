package dev.jbriggs.aoc.days.d8;

import dev.jbriggs.aoc.core.Grid;

public class Forest extends Grid<Tree> {

  public Forest(int length, int height) {
    super(Tree.class, length, height);
  }


  public boolean isTreeVisible(int xOrigin, int yOrigin) {
    return isVisibleFromLeft(xOrigin, yOrigin) || isVisibleFromRight(xOrigin,
        yOrigin) || isVisibleFromTop(xOrigin, yOrigin) || isVisibleFromBottom(
        xOrigin, yOrigin);
  }


  private boolean isVisibleFromLeft(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    for (int x = xOrigin - 1; x >= 0; x--) {
      Tree other = getItem(x, yOrigin);
      if (treeToCheck.isHiddenBy(other)) {
        return false;
      }
    }
    return true;
  }

  private boolean isVisibleFromRight(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    for (int x = xOrigin + 1; x < getLength(); x++) {
      Tree other = getItem(x, yOrigin);
      if (treeToCheck.isHiddenBy(other)) {
        return false;
      }
    }
    return true;
  }

  private boolean isVisibleFromTop(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    for (int y = yOrigin - 1; y >= 0; y--) {
      Tree other = getItem(xOrigin, y);
      if (treeToCheck.isHiddenBy(other)) {
        return false;
      }
    }
    return true;
  }

  private boolean isVisibleFromBottom(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    for (int y = yOrigin + 1; y < getHeight(); y++) {
      Tree other = getItem(xOrigin, y);
      if (treeToCheck.isHiddenBy(other)) {
        return false;
      }
    }
    return true;
  }

  public Long getScenicScore(int xOrigin, int yOrigin) {
    Long top = getViewingDistanceTop(xOrigin, yOrigin);
    Long left = getViewingDistanceLeft(xOrigin, yOrigin);
    Long bottom = getViewingDistanceBottom(xOrigin, yOrigin);
    Long right = getViewingDistanceRight(xOrigin, yOrigin);
    return left * right * top * bottom;
  }

  private Long getViewingDistanceLeft(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    Long total = 0L;
    for (int x = xOrigin - 1; x >= 0; x--) {
      Tree other = getItem(x, yOrigin);
      total++;
      if (treeToCheck.isHiddenBy(other)) {
        return total;
      }
    }
    return total;
  }

  private Long getViewingDistanceRight(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    Long total = 0L;
    for (int x = xOrigin + 1; x < getLength(); x++) {
      Tree other = getItem(x, yOrigin);
      total++;
      if (treeToCheck.isHiddenBy(other)) {
        return total;
      }
    }
    return total;
  }

  private Long getViewingDistanceTop(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    Long total = 0L;
    for (int y = yOrigin - 1; y >= 0; y--) {
      Tree other = getItem(xOrigin, y);
      total++;
      if (treeToCheck.isHiddenBy(other)) {
        return total;
      }
    }
    return total;
  }

  private Long getViewingDistanceBottom(int xOrigin, int yOrigin) {
    Tree treeToCheck = getItem(xOrigin, yOrigin);
    Long total = 0L;
    for (int y = yOrigin + 1; y < getHeight(); y++) {
      Tree other = getItem(xOrigin, y);
      total++;
      if (treeToCheck.isHiddenBy(other)) {
        return total;
      }
    }
    return total;
  }
}
