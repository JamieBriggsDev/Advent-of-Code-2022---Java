package dev.jbriggs.aoc.days.d14;

import dev.jbriggs.aoc.core.Collidable;
import dev.jbriggs.aoc.core.Vector2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CollidableCollection implements Collection<Collidable<Vector2>> {
  private List<Collidable<Vector2>> collidableList;
  public CollidableCollection(){
    this.collidableList = new ArrayList<>();
  }

  public boolean collidesWithAnyObject(Vector2 col){
    return collidableList.stream().anyMatch(x -> x.collidesWith(col));
  }

  @Override
  public int size() {
    return collidableList.size();
  }

  @Override
  public boolean isEmpty() {
    return collidableList.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return collidableList.contains(o);
  }

  @Override
  public Iterator<Collidable<Vector2>> iterator() {
    return collidableList.iterator();
  }

  @Override
  public Object[] toArray() {
    return collidableList.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return collidableList.toArray(a);
  }

  public boolean add(Collidable<Vector2> collidable){
    return this.collidableList.add(collidable);
  }

  @Override
  public boolean remove(Object o) {
    return this.collidableList.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return new HashSet<>(this.collidableList).containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends Collidable<Vector2>> c) {
    return this.collidableList.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.collidableList.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.collidableList.retainAll(c);
  }

  @Override
  public void clear() {
    this.collidableList.clear();
  }
}
