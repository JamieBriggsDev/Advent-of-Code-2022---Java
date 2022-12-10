package dev.jbriggs.aoc;

public class AOCException extends RuntimeException{

  public AOCException() {
  }

  public AOCException(String message) {
    super(message);
  }

  public AOCException(String message, Throwable cause) {
    super(message, cause);
  }

  public AOCException(Throwable cause) {
    super(cause);
  }

  public AOCException(String message, Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
