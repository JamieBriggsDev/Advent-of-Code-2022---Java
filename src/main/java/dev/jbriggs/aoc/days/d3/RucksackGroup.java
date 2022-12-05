package dev.jbriggs.aoc.days.d3;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class RucksackGroup {

  private final Rucksack rucksackOne;
  private final Rucksack rucksackTwo;
  private final Rucksack rucksackThree;

  public RucksackGroup(Rucksack rucksackOne, Rucksack rucksackTwo,
      Rucksack rucksackThree) {
    this.rucksackOne = rucksackOne;
    this.rucksackTwo = rucksackTwo;
    this.rucksackThree = rucksackThree;
  }


  public Character findMostCommonItem() {
    Collection<Character> commonCharacters = this.rucksackOne.getAll();
    commonCharacters.retainAll(this.rucksackTwo.getAll());
    commonCharacters.retainAll(this.rucksackThree.getAll());
    return commonCharacters.stream().findFirst().orElse(null);
  }
}
