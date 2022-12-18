package dev.jbriggs.aoc.handheld.packet;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PacketValue extends PacketItem<Integer> implements Comparable<PacketValue>{

  @Override
  public int compareTo(PacketValue o) {
    return this.item.compareTo(o.item);
  }
}
