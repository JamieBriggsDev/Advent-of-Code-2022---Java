package dev.jbriggs.aoc.handheld.reader;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.handheld.DeviceException;
import dev.jbriggs.aoc.handheld.storage.TerminalCommand;
import dev.jbriggs.aoc.handheld.storage.TerminalDirectory;
import dev.jbriggs.aoc.handheld.storage.TerminalFile;
import dev.jbriggs.aoc.handheld.storage.TerminalReaderState;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class VideoSignalReader extends MemoryHolder implements Reader {

  public static final long TOTAL_DISK_SPACE_AVAILABLE = 70000000L;

  private static final Pattern COMMAND_PATTERN = Pattern.compile(
      "^\\$ ([a-z]+) ?([\\-a-zA-Z\\.\\d\\/]+)?$");
  private static final Pattern OUTPUT_PATTERN = Pattern.compile(
      "^([a-z\\d]+) ?([a-zA-Z\\d\\/\\.]+)?$");
  private TerminalReaderState currentState = TerminalReaderState.WRITING;

  private final TerminalStorage terminalStorage;
  private List<String> delayedTerminalCommand = new ArrayList<>();

  public VideoSignalReader() {
    this.terminalStorage = new TerminalStorage();
  }

  public TerminalDirectory getCurrentTerminalDirectory() {
    return terminalStorage.getCurrentTerminalDirectory();
  }

  public Long totalSpaceUsed() {
    return terminalStorage.getRootTerminalDirectory().getSize();
  }

  public void readAll(List<String> input) throws ReaderException {
    for (String s : input) {
      read(s);
    }
  }

  public void read(String input) throws ReaderException {
    String trimmedInput = input.trim();
    Matcher commandMatcher = COMMAND_PATTERN.matcher(trimmedInput);
    if (commandMatcher.matches()) {
      TerminalCommand command = TerminalCommand.getByCommand(commandMatcher.group(1));
      performCommand(commandMatcher, command, trimmedInput);
    }
    listenToInput(trimmedInput);
  }

  private void performCommand(Matcher commandMatcher, TerminalCommand command,
      String initialCommand) throws ReaderException {
    Integer cyclesToComplete = command.getCyclesToComplete();

    for (int i = cyclesToComplete - 1; i >= 0; i--) {
      if (i == 0) {
        switch (command) {
          case ADDX -> handleAddToRegisterCommand(commandMatcher.group(2));
          case NOOP -> log.debug("Skipping cycle");
          default -> throw new ReaderException("Unknown command, check if this is the correct reader");
        }
      }

      handleCycleIncrement();
    }
  }

  private void handleDelayedCommands(Matcher commandMatcher, TerminalCommand command) {
    delayedTerminalCommand.forEach(x -> {
      switch (command) {
        case ADDX -> {
          try {
            handleAddToRegisterCommand(commandMatcher.group(2));
          } catch (ReaderException e) {
            throw new RuntimeException(e);
          }
        }
      }
    });
    delayedTerminalCommand = new ArrayList<>();
  }

  private void addCommandToExecuteNextList(String command) {
    this.delayedTerminalCommand.add(command);
  }

  private void handleAddToRegisterCommand(String stringValue) throws ReaderException {
    if (!isNull(memory)) {
      memory.addToXRegister(Integer.valueOf(stringValue));
    } else {
      throw new ReaderException("Memory register handler module not added!");
    }
  }

  private void handleCycleIncrement() {
    if (!isNull(memory)) {
      memory.updateRegisterValues();
    }
  }

  private void listenToInput(String trimmedInput) {
    if (currentState.equals(TerminalReaderState.LISTENING)) {
      Matcher outputMatcher = OUTPUT_PATTERN.matcher(trimmedInput);
      if (outputMatcher.matches()) {
        String qualifier = outputMatcher.group(1);
        if ("dir".equals(qualifier)) {
          terminalStorage.getCurrentTerminalDirectory().addToContents(
              new TerminalDirectory(terminalStorage.getCurrentTerminalDirectory(),
                  outputMatcher.group(2)));
        } else {
          terminalStorage.getCurrentTerminalDirectory().addToContents(
              new TerminalFile(terminalStorage.getCurrentTerminalDirectory(),
                  outputMatcher.group(2), Long.parseLong(qualifier)));
        }
      }
    }
  }

  public int getRegisterValueDuringCycle(int cycleNumber) {
    return memory.getXRegisterValueAtCycle(cycleNumber-1);
  }
  public int getRegisterValueAtEndOfCycle(int cycleNumber) {
    return memory.getXRegisterValueAtCycle(cycleNumber);
  }

  public int getSignalStrengthDuringCycle(int cycleNumber) {
    return cycleNumber * memory.getXRegisterValueAtCycle(cycleNumber-1);
  }
}
