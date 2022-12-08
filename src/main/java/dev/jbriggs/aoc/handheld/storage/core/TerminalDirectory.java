package dev.jbriggs.aoc.handheld.storage.core;

import com.google.common.base.Objects;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringJoiner;
import lombok.Getter;

@Getter
public class TerminalDirectory extends TerminalItem {

  private final HashSet<TerminalItem> contents;

  public TerminalDirectory(TerminalDirectory parent, String folderName) {
    super(parent, folderName);
    this.contents = new HashSet<>();
  }

  public void addToContents(TerminalItem terminalDirectory) {
    contents.add(terminalDirectory);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalDirectory terminalDirectory = (TerminalDirectory) o;
    return Objects.equal(getName(), terminalDirectory.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getName());
  }

  public Long getSize() {
    return contents.stream()
        .mapToLong(TerminalItem::getSize).sum();
  }


  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner("\n");
    stringJoiner.add(String.format("%s (dir)", name));
    for (TerminalItem item : getContents().stream().sorted(
        Comparator.comparing(a -> a.name)).toList()) {
      stringJoiner.add("\t-" + item.toString().replace("\n", "\n\t"));
    }
    return stringJoiner.toString();
  }
}
