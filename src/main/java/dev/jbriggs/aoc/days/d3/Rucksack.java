package dev.jbriggs.aoc.days.d3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Rucksack {

  private final List<Character> compartmentOne;
  private final List<Character> compartmentTwo;
  public Rucksack(String fullContents) {
    int length = fullContents.length();
    String subOne = fullContents.substring(0, length/2);
    String subTwo = fullContents.substring(length/2, length);
    compartmentOne = subOne.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    compartmentTwo = subTwo.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
  }

  public Collection<Character> getCompartmentOne() {
    return new ArrayList<>(compartmentOne);
  }

  public Collection<Character> getCompartmentTwo(){
    return new ArrayList<>(compartmentTwo);
  }

  public Collection<Character> getAll(){
    ArrayList<Character> characters = new ArrayList<>(compartmentOne);
    characters.addAll(compartmentTwo);
    return characters;
  }

  public Collection<Character> findUniqueCommonItems() {
    Collection<Character> duplicates = getCompartmentOne();
    duplicates.retainAll(getCompartmentTwo());
    return new HashSet<>(duplicates);
  }
}
