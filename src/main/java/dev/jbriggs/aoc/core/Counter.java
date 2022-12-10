package dev.jbriggs.aoc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Counter<T> {

  HashMap<T, Integer> counter = new HashMap<>();

  public void countItem(T item) {
    int total = 1;
    if (counter.containsKey(item)) {
      total += counter.get(item) + 1;
    }
    counter.put(item, 1);
  }

  public List<T> getAllItems() {
    return new ArrayList<>(counter.keySet());
  }

  public List<T> getAllItemsAboveSpecificCount(int count) {
    return getAllItems().stream().filter(x -> counter.get(x) > count)
        .collect(Collectors.toList());
  }
}
