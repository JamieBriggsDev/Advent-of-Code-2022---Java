package dev.jbriggs.aoc.handheld.packet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PacketPair {
  private final int index;
  private final PacketCollection left;
  private final PacketCollection right;

  public boolean isInRightOrder() {
    int compareTo = left.compareTo(right);
    return compareTo > 0;
  }

  @Override
  public String toString() {
    return left.toString() + "\n" + right.toString();
  }
}
