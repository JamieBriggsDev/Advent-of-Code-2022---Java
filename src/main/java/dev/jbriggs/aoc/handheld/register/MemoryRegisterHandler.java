package dev.jbriggs.aoc.handheld.register;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class MemoryRegisterHandler {
  int xValue = 1;
  private List<Integer> registerValues = new ArrayList<>();

  public void addToRegister(int total) {
    xValue += total;
  }

  public void updateRegisterValues() {
    registerValues.add(xValue);
  }

  public int getXValueAtCycle(int cycle){
    return registerValues.get(cycle-1);
  }

  public int getCurrentCycle(){
    return registerValues.size();
  }
}
