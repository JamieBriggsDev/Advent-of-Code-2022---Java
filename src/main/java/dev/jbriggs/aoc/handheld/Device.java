package dev.jbriggs.aoc.handheld;

import dev.jbriggs.aoc.handheld.storage.TerminalException;
import dev.jbriggs.aoc.handheld.storage.TerminalReader;
import dev.jbriggs.aoc.handheld.storage.core.TerminalDirectory;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Device {
  private final TerminalReader terminalReader;

  public Device(TerminalReader terminalReader) {
    this.terminalReader = terminalReader;
  }

  public void readTerminalLines(List<String> lines){
    lines.forEach(x -> {
      try {
        terminalReader.read(x);
      } catch (TerminalException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public Collection<TerminalDirectory> findDirectoriesAboveFileSize(Long size){
    return this.terminalReader.findAllDirectories().stream().filter(x -> x.getSize() > 100000).collect(
        Collectors.toList());
  }

  public Collection<TerminalDirectory> findDirectoriesBelowFileSize(Long size){
    return this.terminalReader.findAllDirectories().stream().filter(x -> x.getSize() < 100000).collect(
        Collectors.toList());
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

  private static boolean hasUniqueValues(char[] data,
      int currentCharacterPosition, int markerLength) {
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
