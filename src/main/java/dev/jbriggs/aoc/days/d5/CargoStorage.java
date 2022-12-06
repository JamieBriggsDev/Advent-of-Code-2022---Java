package dev.jbriggs.aoc.days.d5;

import static com.google.common.base.Strings.isNullOrEmpty;

import dev.jbriggs.aoc.core.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class CargoStorage {

  private List<Stack<String>> stacks;

  public CargoStorage(String basicInput) {
    // Total stacks:
    String[] lines = basicInput.split("\n");
    String[] stackIdsRaw = lines[lines.length - 1].split(" ");
    int totalStacks = Integer.parseInt(stackIdsRaw[stackIdsRaw.length - 1]);
    // Intialize stacks
    stacks = new ArrayList<>();
    for (int i = 0; i < totalStacks; i++) {
      stacks.add(new Stack<>());
    }

    // Go through and add containers
    for (int i = lines.length - 2; i >= 0; i--) {
      String[] columns = lines[i].split("(?<=\\G....)");
      for (int x = 0; x < columns.length; x++) {
        String contents = columns[x].trim();
        if (!isNullOrEmpty(contents)) {
          stacks.get(x).addToTop(contents);
        }
      }
    }
  }

  public Stack<String> getStack(int index) {
    return this.stacks.get(index - 1);
  }

  public void moveCargo(int total, int sourceStack, int targetStack) {
    for (int i = 0; i < total; i++) {
      String cargoToMove = getStack(sourceStack).takeAndRemoveTop();
      getStack(targetStack).addToTop(cargoToMove);
    }
  }

  public void moveCargoRetainOrder(int total, int sourceStack,
      int targetStack) {
    List<String> contentsToMove = new ArrayList<>();
    for (int i = 0; i < total; i++) {
      contentsToMove.add(getStack(sourceStack).takeAndRemoveTop());
    }
    Collections.reverse(contentsToMove);
    contentsToMove.forEach(x-> getStack(targetStack).addToTop(x));
  }

  public String readTopOfStorage() {
    StringBuilder stringBuilder = new StringBuilder();
    this.stacks.forEach(x -> stringBuilder.append(
        x.peekTop().replaceAll("[\\[\\]]", "")));
    return stringBuilder.toString();
  }


}
