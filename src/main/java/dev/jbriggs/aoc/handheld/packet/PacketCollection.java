package dev.jbriggs.aoc.handheld.packet;

import static com.google.common.base.Strings.isNullOrEmpty;

import dev.jbriggs.aoc.AOCException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PacketCollection extends PacketItem<List<? extends PacketItem<?>>> implements
    Comparable<PacketItem<?>> {


  public List<String> getList(String rawInput){
    List<String> result = new ArrayList<>();
    if(isNullOrEmpty(rawInput)){
      return result;
    }
    List<String> contents = Arrays.stream(rawInput.substring(1, rawInput.length()-1).split(",")).toList();
    int levelsDeep = 0;
    String toAdd = "";
    for(String item : contents){
      toAdd += item;
      for(Character character : item.toCharArray()){
        if(character.equals('[')){
          levelsDeep++;
        }else if(character.equals(']')){
          levelsDeep--;
        }
      }
      if(levelsDeep == 0){
        result.add(toAdd);
        toAdd = "";
      }else{
        toAdd += ",";
      }
    }
    return result;
  }

  public PacketCollection(String rawInput) {
    List<String> result = new ArrayList<>();
    List<String> contents = getList(rawInput);
    for(String item : contents){
      result.add(item);
    }

    this.item = result.stream()
        .map(x -> {
          if(x.contains("[") || isNullOrEmpty(x)){
            return new PacketCollection(x);
          }else{
            return new PacketValue(x);
          }
        }).toList();

  }

  public PacketCollection(PacketValue value) {
    this("["+value.item+"]");
  }

  @Override
  public int compareTo(PacketItem<?> o) {
    PacketCollection otherCollection;
    if (o instanceof PacketCollection packetCollection) {
      otherCollection = packetCollection;
    } else if (o instanceof PacketValue packetValue) {
      otherCollection = PacketCollection.builder()
          .item(Collections.singletonList(packetValue)).build();
    } else {
      throw new AOCException("Unknown item being compared");
    }
    for (int x = 0; x < this.item.size(); x++) {
      if (x < otherCollection.item.size()) {
        int result = this.item.get(x).compareTo(otherCollection.item.get(x));
        if (result != 0) {
          return result;
        }
      } else {
        return -1;
      }
    }
    if (otherCollection.item.size() > this.item.size()) {
      return 1;
    }
    return 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    StringJoiner sj = new StringJoiner(",");
    this.item.forEach(x -> sj.add(x.toString()));
    sb.append(sj);
    sb.append("]");
    return sb.toString();
  }
}
