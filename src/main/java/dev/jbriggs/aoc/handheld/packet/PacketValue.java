package dev.jbriggs.aoc.handheld.packet;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PacketValue extends PacketItem<Integer> implements Comparable<PacketItem<?>>{

  public PacketValue(String value){
    this.item = Integer.valueOf(value);
  }

  @Override
  public int compareTo(PacketItem<?> o) {
    if(o instanceof PacketValue packetValue){
      return packetValue.item.compareTo(this.item);
    }
    return 0;
  }

  @Override
  public String toString() {
    return this.item.toString();
  }
}
