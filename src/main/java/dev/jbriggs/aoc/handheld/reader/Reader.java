package dev.jbriggs.aoc.handheld.reader;

import dev.jbriggs.aoc.handheld.DeviceException;
import java.util.List;

public interface Reader {
  void readAll(List<String> input) throws ReaderException;

}
