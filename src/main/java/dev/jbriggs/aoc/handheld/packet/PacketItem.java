package dev.jbriggs.aoc.handheld.packet;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class PacketItem<T>{
  T item;
}
