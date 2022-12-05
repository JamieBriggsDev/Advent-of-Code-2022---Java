package dev.jbriggs.aoc.days.d3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rucksack group test")
class RucksackGroupTest {

  @Test
  @DisplayName("Should find most common item")
  void shouldFindMostCommonItem(){
    // Given
    Rucksack one = new Rucksack("abc");
    Rucksack two = new Rucksack("ade");
    Rucksack three = new Rucksack("afg");
    RucksackGroup group = new RucksackGroup(one, two, three);
    // When
    Character item = group.findMostCommonItem();
    // Then
    assertThat("Most common item should be found", item, is('a'));
  }

}