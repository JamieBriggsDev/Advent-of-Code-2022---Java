package dev.jbriggs.aoc.days.d11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Monkey test")
class MonkeyTest {

  @Test
  @DisplayName("Should reduce currently holding levels")
  void shouldReduceCurrentlyHoldingLevels(){
    // Given
    Monkey monkey = Monkey.builder().currentlyHolding(new LinkedList<>(Arrays.asList(3L,5L,6L,7L))).build();
    // When
    monkey.reliefWorryLevels(3);
    // Then
    assertThat("Item 1 should be level 1", monkey.getCurrentlyHolding().poll(), is(1));
    assertThat("Item 2 should be level 1", monkey.getCurrentlyHolding().poll(), is(1));
    assertThat("Item 3 should be level 2", monkey.getCurrentlyHolding().poll(), is(2));
    assertThat("Item 4 should be level 2", monkey.getCurrentlyHolding().poll(), is(2));
  }
}