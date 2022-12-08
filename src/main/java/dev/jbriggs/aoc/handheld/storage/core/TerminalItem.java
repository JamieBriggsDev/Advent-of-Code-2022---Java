package dev.jbriggs.aoc.handheld.storage.core;

import lombok.Getter;

@Getter
public abstract class TerminalItem {

  protected final TerminalDirectory parentDirectory;
  protected final String name;

  protected TerminalItem(TerminalDirectory parentDirectory, String name) {
    this.parentDirectory = parentDirectory;
    this.name = name;
  }

  public abstract Long getSize();
}
