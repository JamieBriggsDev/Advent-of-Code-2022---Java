package dev.jbriggs.aoc.core;

import java.util.ArrayList;
import java.util.List;

public class Stack <T>{
  private List<T> items;

  public Stack() {
    items = new ArrayList<>();
  }

  public T takeAndRemoveTop(){
    return items.remove(items.size()-1);
  }

  public T peekTop(){
    return items.get(items.size()-1);
  }

  public void addToTop(T item){
    items.add(item);
  }

  public int size(){
    return items.size();
  }

  public boolean hasItem(T item){
    return items.contains(item);
  }

  public boolean isEmpty() {
    return size() == 0;
  }
}
