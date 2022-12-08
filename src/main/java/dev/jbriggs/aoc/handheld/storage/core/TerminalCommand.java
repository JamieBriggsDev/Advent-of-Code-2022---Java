package dev.jbriggs.aoc.handheld.storage.core;

import dev.jbriggs.aoc.handheld.storage.TerminalException;
import java.util.Arrays;
import java.util.Optional;

public enum TerminalCommand {
  CHANGE_DIRECTORY("cd"),
  LIST("ls");

  private final String command;

  TerminalCommand(String command) {
    this.command = command;
  }

  public static TerminalCommand getByCommand(String command)
      throws TerminalException {
    Optional<TerminalCommand> first = Arrays.stream(values())
        .filter(x -> x.command.equals(command)).findFirst();
    return first.orElseThrow(() -> new TerminalException("Command could not be found"));
  }
}
