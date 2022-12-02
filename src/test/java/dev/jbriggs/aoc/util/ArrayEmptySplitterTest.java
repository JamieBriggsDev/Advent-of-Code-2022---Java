package dev.jbriggs.aoc.util;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Array empty slitter test")
class ArrayEmptySplitterTest {

  ArrayEmptySplitter arrayEmptySplitter;

  @BeforeEach
  public void beforeEach(){
    arrayEmptySplitter = new ArrayEmptySplitter();
  }

  @Test
  @DisplayName("Should return empty list when empty input")
  void shouldReturnEmptyListWhenEmptyInput(){
    // Given
    List<String> input = emptyList();
    // When
    List<List<String>> result = arrayEmptySplitter.split(input);
    // Then
    assertThat("Result should be empty", result.size(), is(0));
  }

  @Test
  @DisplayName("Should return list with one item when dont need to split")
  void shouldReturnListWithOneItemWhenDontNeedToSplit(){
    // Given
    List<String> input = Arrays.asList("1", "2", "3");
    // When
    List<List<String>> result = arrayEmptySplitter.split(input);
    // Then
    assertThat("Result should have one list", result.size(), is(1));
    assertThat("Result first entry should have correct values", result.get(0), is(input));
  }

  @Test
  @DisplayName("Should return two lists when break in input")
  void shouldReturnTwoListsWhenBreakInInput(){
    // Given
    List<String> input = Arrays.asList("1", "2", "3", "", "4", "5", "6");
    // When
    List<List<String>> result = arrayEmptySplitter.split(input);
    // Then
    assertThat("Result should have two lists", result.size(), is(2));
    assertThat("Result first entry should have correct values", result.get(0), is(Arrays.asList("1", "2", "3")));
    assertThat("Result second entry should have correct values", result.get(1), is(Arrays.asList("4", "5", "6")));
  }
}