package dev.jbriggs.aoc.days.d6;

import org.springframework.stereotype.Component;

@Component
public class HandheldDevice {

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
