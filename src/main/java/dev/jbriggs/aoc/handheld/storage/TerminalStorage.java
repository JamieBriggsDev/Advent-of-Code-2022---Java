package dev.jbriggs.aoc.handheld.storage;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.handheld.reader.TerminalException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TerminalStorage {

  private TerminalDirectory currentTerminalDirectory;
  private TerminalDirectory rootTerminalDirectory;

  public TerminalStorage() {
    this.currentTerminalDirectory = null;
    this.rootTerminalDirectory = null;
  }

  private void updateCurrentDirectory(TerminalDirectory newCurrent) {
    currentTerminalDirectory = newCurrent;
  }


  public void changeDirectory(String directoryName) throws TerminalException {
    if (directoryName.equals("..")) {
      updateCurrentDirectory(currentTerminalDirectory.getParentDirectory());
    } else if (!isNull(currentTerminalDirectory) && directoryName.equals("/")) {
      updateCurrentDirectory(rootTerminalDirectory);
    } else {
      if (isNull(currentTerminalDirectory) || directoryName.equals("/")) {
        updateCurrentDirectory(new TerminalDirectory(directoryName));
        rootTerminalDirectory = currentTerminalDirectory;
      } else {
        Optional<TerminalDirectory> directory = currentTerminalDirectory.getContents().stream()
            .filter(x -> x instanceof TerminalDirectory && x.getName().equals(directoryName))
            .findFirst().map(x -> (TerminalDirectory) x);
        if (directory.isPresent()) {
          updateCurrentDirectory(directory.get());
        } else {
          throw new TerminalException("Directory not found yet - " + directoryName);
        }
      }
    }
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

  private void addChildren(TerminalDirectory parent, HashSet<TerminalDirectory> totalDirectories) {
    if (!isNull(parent)) {
      for (TerminalItem item : parent.getContents()) {
        if (item instanceof TerminalDirectory directory) {
          totalDirectories.add(directory);
          addChildren(directory, totalDirectories);
        }
      }
    }
  }

  public TerminalDirectory findSmallestDirectoryAboveSpecificSize(Long size)
      throws TerminalException {
    List<TerminalDirectory> directoriesBelowFileSize = new ArrayList<>(findAllDirectories());
    Comparator<TerminalDirectory> comparator = Comparator.comparing(TerminalDirectory::getSize);
    Collections.sort(directoriesBelowFileSize, comparator);
    Optional<TerminalDirectory> first = directoriesBelowFileSize.stream()
        .filter(x -> x.getSize() > size).findFirst();
    if (first.isPresent()) {
      return first.get();
    } else {
      throw new TerminalException("No directories found above size " + size);
    }
  }

  public Collection<TerminalDirectory> findDirectoriesBelowFileSize(Long size) {
    return findAllDirectories().stream().filter(x -> x.getSize() < size)
        .collect(Collectors.toList());
  }

}
