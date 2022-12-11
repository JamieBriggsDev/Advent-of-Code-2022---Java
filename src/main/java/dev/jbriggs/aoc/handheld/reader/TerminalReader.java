package dev.jbriggs.aoc.handheld.reader;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.handheld.register.MemoryRegisterHandler;
import dev.jbriggs.aoc.handheld.storage.TerminalCommand;
import dev.jbriggs.aoc.handheld.storage.TerminalDirectory;
import dev.jbriggs.aoc.handheld.storage.TerminalFile;
import dev.jbriggs.aoc.handheld.storage.TerminalReaderState;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class TerminalReader {

  public static final long TOTAL_DISK_SPACE_AVAILABLE = 70000000L;

  private static final Pattern COMMAND_PATTERN = Pattern.compile(
      "^\\$ ([a-z]+) ?([a-zA-Z\\.\\d\\/]+)?$");
  private static final Pattern OUTPUT_PATTERN = Pattern.compile(
      "^([a-z\\d]+) ?([a-zA-Z\\d\\/\\.]+)?$");
  private TerminalReaderState currentState = TerminalReaderState.WRITING;

  private final TerminalStorage terminalStorage;
  private final MemoryRegisterHandler memoryRegisterHandler;

  public TerminalReader(TerminalStorage terminalStorage) {
    this.terminalStorage = terminalStorage;
    this.memoryRegisterHandler = null;
  }

  public TerminalReader(TerminalStorage terminalStorage,
      MemoryRegisterHandler memoryRegisterHandler) {
    this.terminalStorage = terminalStorage;
    this.memoryRegisterHandler = memoryRegisterHandler;
  }

  public TerminalDirectory getCurrentTerminalDirectory() {
    return terminalStorage.getCurrentTerminalDirectory();
  }

  public Long totalSpaceUsed() {
    return terminalStorage.getRootTerminalDirectory().getSize();
  }

  public void readAll(List<String> input) throws TerminalException {
    for (String s : input) {
      read(s);
    }
  }

  public void read(String input) throws TerminalException {
    String trimmedInput = input.trim();
    Matcher commandMatcher = COMMAND_PATTERN.matcher(trimmedInput);
    if (commandMatcher.matches()) {
      TerminalCommand command = TerminalCommand.getByCommand(commandMatcher.group(1));
      performCommand(commandMatcher, command);
    }
    listenToInput(trimmedInput);
  }

  private void performCommand(Matcher commandMatcher, TerminalCommand command)
      throws TerminalException {
    Integer cyclesToComplete = command.getCyclesToComplete();
    for (int cycle = cyclesToComplete - 1; cycle >= 0; cycle--) {
      if (cycle == 0) {
        switch (command) {
          case CHANGE_DIRECTORY -> handleChangeDirectoryCommand(commandMatcher.group(2));
          case LIST -> handleListCommand();
          case ADDX -> handleAddToRegisterCommand(commandMatcher.group(2));
        }
      } else {
        if (!isNull(memoryRegisterHandler)) {
          memoryRegisterHandler.modifyNothing();
        }
      }
    }
  }

  private void handleListCommand() {
    if (!isNull(memoryRegisterHandler)) {
      memoryRegisterHandler.modifyNothing();
    }
    currentState = TerminalReaderState.LISTENING;
  }

  private void handleChangeDirectoryCommand(String directoryName) throws TerminalException {
    if (!isNull(memoryRegisterHandler)) {
      memoryRegisterHandler.modifyNothing();
    }
    currentState = TerminalReaderState.WRITING;
    try {
      terminalStorage.changeDirectory(directoryName);
    } catch (TerminalException e) {
      throw new TerminalException("Unable to change directory");
    }
  }

  private void handleAddToRegisterCommand(String stringValue) throws TerminalException {
    if (!isNull(memoryRegisterHandler)) {
      memoryRegisterHandler.addToRegister(Integer.valueOf(stringValue));
    }else{
      throw new TerminalException("Memory register handler module not added!");
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

  public Collection<TerminalDirectory> findDirectoriesBelowFileSize(long size) {
    return terminalStorage.findDirectoriesBelowFileSize(size);
  }

  public TerminalDirectory findSmallestDirectoryAboveSpecificSize(long size)
      throws TerminalException {
    return terminalStorage.findSmallestDirectoryAboveSpecificSize(size);
  }

  public String getCurrentDirectoryPath() {
    return terminalStorage.getCurrentDirectoryPath();
  }

  public Collection<TerminalDirectory> findAllDirectories() {
    return terminalStorage.findAllDirectories();
  }

  public int getRegisterValueAtCycle(int cycleNumber) {
    return memoryRegisterHandler.getXValueAtCycle(cycleNumber);
  }
}
