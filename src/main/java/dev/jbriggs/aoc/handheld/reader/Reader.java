package dev.jbriggs.aoc.handheld.reader;

import dev.jbriggs.aoc.handheld.HandheldException;
import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;
import java.util.List;

public interface Reader {
  void readAll(List<String> input) throws HandheldException;
  void setMemoryRegisterHolder(MemoryRegisterHolder memoryRegisterHolder);
}
