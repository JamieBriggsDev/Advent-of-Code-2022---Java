package dev.jbriggs.aoc.util;

import static java.util.Objects.isNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PuzzleInputParser {

  @SneakyThrows
  public String[] getPuzzleInputFromFile(String path) {
    InputStream is = getClass().getClassLoader().getResourceAsStream(path);
    if (isNull(is)) {
      return new String[] {};
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    List<String> lines = new ArrayList<>();
    while (reader.ready()) {
      lines.add(reader.readLine());
    }
    return lines.toArray(new String[0]);
  }

}
