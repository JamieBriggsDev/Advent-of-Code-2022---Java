package dev.jbriggs.aoc.days.d14;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day14Solution extends Day {

  public static final int DAY_NUMBER = 14;

  public Day14Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.14.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  // 7313 too high
  @SneakyThrows
  @Override
  protected Object partOne(List<String> input) {
    CollidableCollection collidableCollection = new CollidableCollection();
    for(String row : input){
      collidableCollection.add(new Wall(row));
    }
    Cave cave = new Cave(collidableCollection);
    int steps = 0;
    boolean stillLooping = true;
    while(stillLooping){
      if(!cave.dropSand()){
        stillLooping = false;
      }else{
        steps++;
      }
    }
    return steps;
  }

  @SneakyThrows
  @Override
  protected Object partTwo(List<String> input) {
    return null;
  }


}
