package dev.jbriggs.aoc.days.d3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItems;

import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Rucksack tests")
class RucksackTest {

  @Test
  @DisplayName("Should split two items into two compartments")
  void shouldSplitContentsIntoTwoCompartments(){
    // Given
    // When
    Rucksack rucksack = new Rucksack("ab");
    // Then
    assertThat("Compartment one has correct contents", rucksack.getCompartmentOne(), hasItems('a'));
    assertThat("Compartment one has correct contents", rucksack.getCompartmentTwo(), hasItems('b'));
  }

  @Test
  @DisplayName("Should split contents in two")
  void shouldSplitContentsInTwo(){
    // Given
    // When
    Rucksack rucksack = new Rucksack("abcdef");
    // Then
    assertThat("Compartment one has correct contents", rucksack.getCompartmentOne(), hasItems('a','b','c'));
    assertThat("Compartment one has correct contents", rucksack.getCompartmentTwo(), hasItems('d','e','f'));
  }

  @Nested
  class GetCommonItems{

    @Test
    @DisplayName("Should return no common items")
    void shouldReturnNoCommonItems(){
      // Given
      Rucksack rucksack = new Rucksack("abcdef");
      // When
      Collection<Character> result = rucksack.findUniqueCommonItems();
      // Then
      assertThat("Result should be empty", result, is(empty()));
    }

    @Test
    @DisplayName("Should return one common item")
    void shouldReturnOneCommonItem(){
      // Given
      Rucksack rucksack = new Rucksack("abcdea");
      // When
      Collection<Character> result = rucksack.findUniqueCommonItems();
      // Then
      assertThat("Result should have one item", result.size(), is(1));
      assertThat("Result should have correct item", result, hasItems('a'));
    }

    @Test
    @DisplayName("Should return one unique common item")
    void shouldReturnOneUniqueCommonItem(){
      // Given
      Rucksack rucksack = new Rucksack("aaaa");
      // When
      Collection<Character> result = rucksack.findUniqueCommonItems();
      // Then
      assertThat("Result should have one item", result.size(), is(1));
      assertThat("Result should have correct item", result, hasItems('a'));
    }
  }
}