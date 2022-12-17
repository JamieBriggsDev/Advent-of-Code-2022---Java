package dev.jbriggs.aoc.handheld;

import static java.util.Objects.isNull;

import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;
import dev.jbriggs.aoc.handheld.reader.Reader;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import java.util.List;

public class Device {

  protected Device() {
  }

  public static Builder builder() {
    return new Builder();
  }

  private MemoryRegisterHolder memory;
  private Reader reader;

  public Reader getReader() {
    return reader;
  }

  protected void setReader(Reader reader) {
    this.reader = reader;
  }

  public MemoryRegisterHolder getMemory() {
    return memory;
  }

  protected void setMemory(MemoryRegisterHolder memory) {
    this.memory = memory;
  }

  public void readTerminalLines(List<String> lines) throws HandheldException {
    if (!isNull(reader)) {
      reader.readAll(lines);
    } else {
      throw new HandheldException("Reader module not added!");
    }
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
      int currentCharacterPosition,
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

  public String getCurrentScreenDisplay() throws HandheldException {
    if (memory instanceof CRTScreen crtScreen) {
      return crtScreen.printScreen(40, 6, '#', '.');
    } else {
      throw new HandheldException("Missing CRT Screen module!");
    }
  }

  public static class Builder {

    private Device device;

    public Builder() {
      this.device = new Device();
    }

    public Builder addModule(Reader reader) {
      this.device.setReader(reader);
      return this;
    }

    public Builder addModule(MemoryRegisterHolder memoryRegisterHolder) {
      this.device.setMemory(memoryRegisterHolder);
      return this;
    }

    public Device build() {
      if (!isNull(this.device.getReader())) {
        this.device.getReader().setMemoryRegisterHolder(
            this.device.getMemory());
      }
      return device;
    }

  }
}
