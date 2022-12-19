package dev.jbriggs.aoc.days.d3;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Day3Solution extends Day {
  private final CharacterPriorityFinder finder;
  public Day3Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.3.input}") String inputPath) {
    super(3, puzzleInputParser, inputPath);
    this.finder = new CharacterPriorityFinder();
  }

  @Override
  protected Object partOne(List<String> input) {
    List<Rucksack> rucksacks = input.stream().map(Rucksack::new).toList();
    Integer sum = 0;
    for(Rucksack rucksack : rucksacks){
      Collection<Character> commonItems = rucksack.findUniqueCommonItems();
      for(Character character : commonItems){
        sum += this.finder.findPriority(character);
      }
    }
    return String.valueOf(sum);
  }

  @Override
  protected Object partTwo(List<String> input) {
    List<Rucksack> rucksacks = input.stream().map(Rucksack::new).toList();
    List<RucksackGroup> groups = new ArrayList<>();
    for(int i = 0; i < rucksacks.size(); i += 3){
      groups.add(new RucksackGroup(rucksacks.get(i), rucksacks.get(i+1), rucksacks.get(i+2)));
    }
    Integer sum = 0;
    for(RucksackGroup group : groups){
      sum += this.finder.findPriority(group.findMostCommonItem());
    }
    return String.valueOf(sum);
  }

}
