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
    }else if(o instanceof PacketCollection packetCollection){
      PacketCollection collection = new PacketCollection(this);
      return collection.compareTo(packetCollection);
    }else{
      return -1;
    }
  }

  @Override
  public String toString() {
    return this.item.toString();
  }
}
