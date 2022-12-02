package dev.jbriggs.aoc.days.d1;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Calorie counter test")
class CalorieCounterTest {

  CalorieCounter calorieCounter;

  @BeforeEach
  public void beforeEach() {
    calorieCounter = new CalorieCounter();
  }

  @Test
  @DisplayName("Should count zero when empty list passed")
  void shouldCountZeroWhenEmptyListPassed(){
    // Given
    List<String> emptyList = emptyList();
    // When
    Long result = calorieCounter.countCalories(emptyList);
    // Then
    assertThat("No calories should be counted", result, is(0L));
  }

  @Test
  @DisplayName("Should return same amount of calories when list of size one passed")
  void shouldReturnSameAmountOfCaloriesWhenListOfSizeOnePassed(){
    // Given
    List<String> emptyList = singletonList("1000");
    // When
    Long result = calorieCounter.countCalories(emptyList);
    // Then
    assertThat("Calories should be counted correctly", result, is(1000L));
  }

  @Test
  @DisplayName("Should count multiple sets of calories")
  void shouldCountMultipleSetsOfCalories(){
    // Given
    List<String> emptyList = Arrays.asList("1000", "2500");
    // When
    Long result = calorieCounter.countCalories(emptyList);
    // Then
    assertThat("Calories should be counted correctly", result, is(3500L));
  }
}