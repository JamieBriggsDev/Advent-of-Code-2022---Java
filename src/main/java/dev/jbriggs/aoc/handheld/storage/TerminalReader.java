package dev.jbriggs.aoc.handheld.storage;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.handheld.storage.core.TerminalCommand;
import dev.jbriggs.aoc.handheld.storage.core.TerminalDirectory;
import dev.jbriggs.aoc.handheld.storage.core.TerminalFile;
import dev.jbriggs.aoc.handheld.storage.core.TerminalItem;
import dev.jbriggs.aoc.handheld.storage.core.TerminalReaderState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Slf4j
@Component
public class TerminalReader {

  public static final long TOTAL_DISK_SPACE_AVAILABLE = 70000000L;

  private static final Pattern COMMAND_PATTERN = Pattern.compile(
      "^\\$ ([a-z]+) ?([a-zA-Z\\.\\d\\/]+)?$");
  private static final Pattern OUTPUT_PATTERN = Pattern.compile(
      "^([a-z\\d]+) ?([a-zA-Z\\d\\/\\.]+)?$");
  private TerminalReaderState currentState = TerminalReaderState.WRITING;
  private TerminalDirectory currentTerminalDirectory;
  private TerminalDirectory rootTerminalDirectory;

  public TerminalReader() {
    rootTerminalDirectory = null;
    currentTerminalDirectory = null;
  }

  public TerminalDirectory getCurrentTerminalDirectory() {
    return currentTerminalDirectory;
  }

  public Long totalSpaceUsed() {
    return getRootTerminalDirectory().getSize();
  }

  public void read(String input) throws TerminalException {
    String trimedInput = input.trim();

    Matcher commandMatcher = COMMAND_PATTERN.matcher(trimedInput);
    if (commandMatcher.matches()) {
      TerminalCommand command = TerminalCommand.getByCommand(
          commandMatcher.group(1));
      switch (command) {
        case CHANGE_DIRECTORY -> changeDirectory(commandMatcher.group(2));
        case LIST -> currentState = TerminalReaderState.LISTENING;
      }
    }

    if (currentState.equals(TerminalReaderState.LISTENING)) {
      Matcher outputMatcher = OUTPUT_PATTERN.matcher(trimedInput);
      if (outputMatcher.matches()) {
        String qualifier = outputMatcher.group(1);
        if ("dir".equals(qualifier)) {
          currentTerminalDirectory.addToContents(
              new TerminalDirectory(currentTerminalDirectory,
                  outputMatcher.group(2)));
        } else {
          currentTerminalDirectory.addToContents(
              new TerminalFile(currentTerminalDirectory, outputMatcher.group(2),
                  Long.parseLong(qualifier)));
        }
      }
    }

  }

  private void changeDirectory(String directoryName) throws TerminalException {
    currentState = TerminalReaderState.WRITING;
    if (directoryName.equals("..")) {
      updateCurrentDirectory(currentTerminalDirectory.getParentDirectory());
    } else if (!isNull(currentTerminalDirectory) && directoryName.equals("/")) {
      updateCurrentDirectory(rootTerminalDirectory);
    } else {
      if (isNull(currentTerminalDirectory) || directoryName.equals("/")) {
        updateCurrentDirectory(
            new TerminalDirectory(currentTerminalDirectory, directoryName));
        rootTerminalDirectory = currentTerminalDirectory;
      } else {
        Optional<TerminalDirectory> directory = currentTerminalDirectory.getContents()
            .stream().filter(x -> x instanceof TerminalDirectory && x.getName()
                .equals(directoryName)).findFirst()
            .map(x -> (TerminalDirectory) x);
        if (directory.isPresent()) {
          updateCurrentDirectory(directory.get());
        } else {
          throw new TerminalException(
              "Directory not found yet - " + directoryName);
        }
      }
    }
  }

  private void updateCurrentDirectory(TerminalDirectory newCurrent) {
    currentTerminalDirectory = newCurrent;
  }

  public String getCurrentDirectoryPath() {
    boolean notAtRoot = false;
    TerminalDirectory terminalDirectoryToCheck = currentTerminalDirectory;
    Stack<TerminalDirectory> directories = new Stack<>();
    while (!notAtRoot) {
      directories.add(terminalDirectoryToCheck);
      if (isNull(terminalDirectoryToCheck.getParentDirectory())) {
        notAtRoot = true;
      } else {
        terminalDirectoryToCheck = terminalDirectoryToCheck.getParentDirectory();
      }
    }
    StringJoiner stringJoiner = new StringJoiner("/");
    while (!directories.isEmpty()) {
      stringJoiner.add(directories.pop().getName());
    }
    return (stringJoiner + "/").replace("//", "/");
  }

  public Collection<TerminalDirectory> findAllDirectories() {
    HashSet<TerminalDirectory> totalDirectories = new HashSet<>();
    totalDirectories.add(rootTerminalDirectory);
    addChildren(rootTerminalDirectory, totalDirectories);
    return totalDirectories;
  }


  private void addChildren(TerminalDirectory parent,
      HashSet<TerminalDirectory> totalDirectories) {
    if (!isNull(parent)) {
      for (TerminalItem item : parent.getContents()) {
        if (item instanceof TerminalDirectory directory) {
          totalDirectories.add(directory);
          addChildren(directory, totalDirectories);
        }
      }
    }
  }

  public Collection<TerminalDirectory> findDirectoriesBelowFileSize(Long size) {
    return findAllDirectories().stream().filter(x -> x.getSize() < size)
        .collect(Collectors.toList());
  }

  public TerminalDirectory findSmallestDirectoryAboveSpecificSize(Long size)
      throws TerminalException {
    List<TerminalDirectory> directoriesBelowFileSize = new ArrayList<>(
        findAllDirectories());
    Comparator<TerminalDirectory> comparator = Comparator.comparing(
        TerminalDirectory::getSize);
    Collections.sort(directoriesBelowFileSize, comparator);
    Optional<TerminalDirectory> first = directoriesBelowFileSize.stream()
        .filter(x -> x.getSize() > size).findFirst();
    if (first.isPresent()) {
      return first.get();
    } else {
      throw new TerminalException("No directories found above size " + size);
    }
  }
}
