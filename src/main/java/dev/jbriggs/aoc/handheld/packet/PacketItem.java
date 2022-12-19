package dev.jbriggs.aoc.handheld.packet;

import com.google.common.base.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public abstract class PacketItem<T>{
  T item;

  public abstract int compareTo(PacketItem<?> o);
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PacketItem<?> that = (PacketItem<?>) o;
    return Objects.equal(item, that.item);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(item);
  }


}
