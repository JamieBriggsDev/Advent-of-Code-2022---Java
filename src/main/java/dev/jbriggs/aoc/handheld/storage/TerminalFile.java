package dev.jbriggs.aoc.handheld.storage;

import com.google.common.base.Objects;
import lombok.Getter;

@Getter
public class TerminalFile extends TerminalItem{
  private final Long size;

  public TerminalFile(TerminalDirectory parent, String fileName, Long size) {
    super(parent, fileName);
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalFile that = (TerminalFile) o;
    return Objects.equal(getName(), that.getName())
        && Objects.equal(size, that.size);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getName(), size);
  }

  @Override
  public String toString() {
    return String.format("%s (file, size=%d)", name, size);
  }
}
