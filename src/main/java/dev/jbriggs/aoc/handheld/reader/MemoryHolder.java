package dev.jbriggs.aoc.handheld.reader;

import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;

public abstract class MemoryHolder {
  protected MemoryRegisterHolder memory;
  public void setMemory(MemoryRegisterHolder memory){
    this.memory = memory;
  }
}
