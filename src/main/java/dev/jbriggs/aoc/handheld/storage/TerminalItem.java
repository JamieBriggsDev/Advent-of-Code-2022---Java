package dev.jbriggs.aoc.handheld.storage;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class TerminalItem {

  protected final TerminalDirectory parentDirectory;
  protected final String name;

  protected TerminalItem(TerminalDirectory parentDirectory, String name) {
    this.parentDirectory = parentDirectory;
    this.name = name;
  }

  public abstract Long getSize();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalItem item = (TerminalItem) o;
    return Objects.equal(name,
        item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(parentDirectory, name);
  }
}
