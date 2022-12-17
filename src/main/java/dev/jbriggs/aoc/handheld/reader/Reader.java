package dev.jbriggs.aoc.handheld.reader;

import dev.jbriggs.aoc.handheld.HandheldException;
import java.util.List;

public interface Reader {
  void readAll(List<String> input) throws HandheldException;
}
