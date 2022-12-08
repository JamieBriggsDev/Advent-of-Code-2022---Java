package dev.jbriggs.aoc.handheld.storage;

import dev.jbriggs.aoc.handheld.HandheldException;

public class TerminalException extends HandheldException {
  public TerminalException(String message) {
    super(message);
  }

  public TerminalException(String message, Throwable cause) {
    super(message, cause);
  }
}
