package dev.jbriggs.aoc.util;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ArrayEmptySplitter {

  public List<List<String>> split(List<String> input) {
    if(input.isEmpty()){
      return Collections.emptyList();
    }

    List<List<String>> result = new ArrayList<>();
    List<String> entry = null;
    for(String row : input){
      if(isNull(entry) || isNullOrEmpty(row)){
        if(isNullOrEmpty(row)){
          result.add(entry);
        }
        entry = new ArrayList<>();
      }
      if(!isNullOrEmpty(row)){
        entry.add(row);
      }
    }
    result.add(entry);



    return result;
  }
}
