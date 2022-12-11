package dev.jbriggs.aoc.handheld;

import dev.jbriggs.aoc.handheld.reader.TerminalException;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import java.util.List;
import org.springframework.stereotype.Component;

public class Device {

  private final TerminalReader terminalReader;
  private final TerminalStorage terminalStorage;

  public Device() {
    this.terminalStorage = new TerminalStorage();
    this.terminalReader = new TerminalReader(this.terminalStorage);
  }

  public Device(TerminalReader terminalReader) {
    this.terminalReader = terminalReader;
    this.terminalStorage = terminalReader.getTerminalStorage();
  }

  public TerminalReader getTerminalReader() {
    return terminalReader;
  }

  public void readTerminalLines(List<String> lines) throws TerminalException {
    terminalReader.readAll(lines);
  }

  public int findMarkerPosition(String input, int markerLength) {
    char[] chars = input.toCharArray();
    for (int i = markerLength; i < chars.length; i++) {
      if (hasUniqueValues(chars, i, markerLength)) {
        return i;
      }
    }
    return input.length();
  }

  private static boolean hasUniqueValues(char[] data, int currentCharacterPosition,
      int markerLength) {
    for (int toCheck = currentCharacterPosition - (markerLength);
        toCheck < currentCharacterPosition; toCheck++) {
      for (int other = toCheck + 1; other < currentCharacterPosition; other++) {
        char otherCharacter = data[other];
        char toCheckCharacter = data[toCheck];
        if (toCheckCharacter == otherCharacter && other != toCheck) {
          return false;
        }
      }
    }
    return true;
  }
}
