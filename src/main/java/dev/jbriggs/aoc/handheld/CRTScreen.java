package dev.jbriggs.aoc.handheld;

import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;

public class CRTScreen extends MemoryRegisterHolder {

  public boolean isPixelLitAtCycle(int cycle) {
    return isPixelLitAtCycle(cycle, cycle);
  }
  public boolean isPixelLitAtCycle(int cycle, int xPosition) {
    int xRegisterValueAtCycle = getXRegisterValueAtCycle(cycle);
    return xRegisterValueAtCycle - 1 <= xPosition
        && xPosition <= xRegisterValueAtCycle + 1;
  }

  public String printScreen(int length, int height, char litCharacter, char unlitCharacter) {
    StringBuilder sb = new StringBuilder();
    int currentCycle = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < length; x++) {
        if (isPixelLitAtCycle(currentCycle, x)) {
          sb.append(litCharacter);
        } else {
          sb.append(unlitCharacter);
        }
        currentCycle++;
      }
      if(y != height - 1){
        sb.append("\n");
      }
    }
    return sb.toString();
  }
  public String printScreen(int length, int height) {
    return printScreen(length, height, '#', '.');
  }
}
