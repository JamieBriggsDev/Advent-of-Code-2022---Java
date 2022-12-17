package dev.jbriggs.aoc.days.d12;

import dev.jbriggs.aoc.core.Vector2;
import dev.jbriggs.aoc.pathfinding.GraphNodeWithPosition;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MountainNode implements GraphNodeWithPosition {

  private final String id;
  private final Vector2 position;

  public Character getRawId(){
    if(isStart()){
      return 'a';
    }else if(isEnd()){
      return 'z';
    }else{
      return id.charAt(0);
    }
  }
  public MountainNode(String id, Vector2 position) {
    this.id = id;
    this.position = position;
  }


  public boolean isStart(){
    return Objects.equals(getId(), "S");
  }

  public boolean isEnd(){
    return Objects.equals(getId(), "E");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MountainNode that = (MountainNode) o;
    return com.google.common.base.Objects.equal(id, that.id)
        && com.google.common.base.Objects.equal(position, that.position);
  }

  @Override
  public int hashCode() {
    return com.google.common.base.Objects.hashCode(id, position);
  }
}
