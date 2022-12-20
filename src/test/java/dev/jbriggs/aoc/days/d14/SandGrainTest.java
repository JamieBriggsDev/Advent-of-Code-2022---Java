package dev.jbriggs.aoc.days.d14;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Sand grain test")
class SandGrainTest {
  @Test
  @DisplayName("Should return false when does not collide")
  void shouldReturnFalseWhenDoesNotCollide(){
    // Given
    SandGrain sandGrain = SandGrain.builder().x(0).y(0).build();
    // When
    boolean result = sandGrain.collidesWith(Vector2.builder().x(1).y(1).build());
    // Then
    assertThat("Result should be false", result, is(false));
  }

  @Test
  @DisplayName("Should return true when does not collide")
  void shouldReturnTrueWhenDoesNotCollide(){
    // Given
    SandGrain sandGrain = SandGrain.builder().x(0).y(0).build();
    // When
    boolean result = sandGrain.collidesWith(Vector2.builder().x(0).y(0).build());
    // Then
    assertThat("Result should be true", result, is(true));
  }
}