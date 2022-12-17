package dev.jbriggs.aoc.pathfinding;

public class PathFindingException extends RuntimeException{

  public PathFindingException() {
  }

  public PathFindingException(String message) {
    super(message);
  }

  public PathFindingException(String message, Throwable cause) {
    super(message, cause);
  }

  public PathFindingException(Throwable cause) {
    super(cause);
  }

  public PathFindingException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
