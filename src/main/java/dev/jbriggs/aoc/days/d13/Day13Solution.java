package dev.jbriggs.aoc.days.d13;

import static com.google.common.base.Strings.isNullOrEmpty;

import dev.jbriggs.aoc.Day;
import dev.jbriggs.aoc.handheld.packet.PacketCollection;
import dev.jbriggs.aoc.handheld.packet.PacketPair;
import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Day13Solution extends Day {

  public static final int DAY_NUMBER = 13;

  public Day13Solution(PuzzleInputParser puzzleInputParser,
      @Value("${solutions.day.13.input}") String inputPath) {
    super(DAY_NUMBER, puzzleInputParser, inputPath);
  }

  // 7313 too high
  @SneakyThrows
  @Override
  protected Object partOne(List<String> input) {
    List<PacketPair> pairs = new ArrayList<>();
    int index = 1;
    for(int i = 0; i < input.size(); i += 3){
      PacketPair pair = new PacketPair(index, new PacketCollection(input.get(i)), new PacketCollection(input.get(i+1)));
      pairs.add(pair);
      index++;
    }
    Stream<PacketPair> packetPairStream = pairs.stream()
        .filter(PacketPair::isInRightOrder);
    return packetPairStream.mapToInt(
        PacketPair::getIndex).sum();
  }

  @SneakyThrows
  @Override
  protected Object partTwo(List<String> input) {
    List<PacketCollection> packetCollections = new ArrayList<>();
    for(String row : input){
      if(!isNullOrEmpty(row)){
        packetCollections.add(new PacketCollection(row));
      }
    }
    PacketCollection dividerA = new PacketCollection("[[2]]");
    PacketCollection dividerB = new PacketCollection("[[6]]");
    packetCollections.add(dividerA);
    packetCollections.add(dividerB);
    List<PacketCollection> sortedCollection = packetCollections.stream().sorted(
        Comparator.reverseOrder()).toList();
    Collections.reverse(packetCollections);
    int aIndex = sortedCollection.indexOf(dividerA) + 1;
    int bIndex = sortedCollection.indexOf(dividerB) + 1;
    return aIndex * bIndex;
  }


}
