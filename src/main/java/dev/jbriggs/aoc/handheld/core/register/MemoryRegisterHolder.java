package dev.jbriggs.aoc.handheld.core.register;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class MemoryRegisterHolder {
  int xRegister = 1;
  private List<Integer> registerValues = new ArrayList<>();

  public void addToXRegister(int total) {
    xRegister += total;
  }

  public void updateRegisterValues() {
    registerValues.add(xRegister);
  }

  public int getXRegisterValueAtCycle(int cycle){
    try{
      return registerValues.get(cycle-1);
    }catch (IndexOutOfBoundsException e){
      return 1;
    }
  }

  public int getCurrentCycle(){
    return registerValues.size();
  }
}
