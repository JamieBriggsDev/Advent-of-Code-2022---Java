package dev.jbriggs.aoc.handheld.packet;

import java.util.List;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PacketCollection extends PacketItem<List<PacketValue>> implements
    Comparable<PacketCollection> {

  @Override
  public int compareTo(PacketCollection o) {
    for (int x = 0; x < this.item.size(); x++) {
      int result = this.item.get(x).compareTo(o.item.get(x));
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }
}
