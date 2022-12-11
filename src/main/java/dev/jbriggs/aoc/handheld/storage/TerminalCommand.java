package dev.jbriggs.aoc.handheld.storage;

import dev.jbriggs.aoc.handheld.reader.TerminalException;
import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum TerminalCommand {
  CHANGE_DIRECTORY("cd", 1),
  LIST("ls", 1),
  NOOP("noop", 1),
  ADDX("addx", 2),
  ;

  private final String command;
  private final Integer cyclesToComplete;

  TerminalCommand(String command, Integer cyclesToComplete) {
    this.command = command;
    this.cyclesToComplete = cyclesToComplete;
  }

  public static TerminalCommand getByCommand(String command)
      throws TerminalException {
    Optional<TerminalCommand> first = Arrays.stream(values())
        .filter(x -> x.command.equals(command)).findFirst();
    return first.orElseThrow(() -> new TerminalException("Command could not be found"));
  }
}
