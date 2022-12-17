package dev.jbriggs.aoc.handheld;

public class HandheldException extends Exception {
  public HandheldException(String message) {
    super(message);
  }

  public HandheldException(String message, Throwable cause) {
    super(message, cause);
  }
}
