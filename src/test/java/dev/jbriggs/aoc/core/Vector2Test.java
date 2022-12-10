package dev.jbriggs.aoc.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Vector2 test")
class Vector2Test {

  @Nested
  @DisplayName("Is vector touching other")
  class IsVectorTouchingOther{

    @ParameterizedTest
    @CsvSource({
        "0,0",
        "1,0",
        "2,0",
        "0,1",
        "2,1",
        "0,2",
        "1,2",
        "2,2",
    })
    @DisplayName("Should return false when not touching")
    void shouldReturnFalseWhenNotTouching(int x, int y){
      // Given
      Vector2 origin = Vector2.builder().x(5).y(5).build();
      Vector2 other = Vector2.builder().x(x).y(y).build();
      // When
      boolean isTouching = origin.isTouching(other);
      // Then
      assertThat("Vectors should not be touching", isTouching, is(false));
    }

    @ParameterizedTest
    @CsvSource({
        "0,0",
        "1,0",
        "2,0",
        "0,1",
        "2,1",
        "0,2",
        "1,2",
        "2,2",
    })
    @DisplayName("Should return true when touching")
    void shouldReturnTrueWhenTouching(int x, int y){
      // Given
      Vector2 origin = Vector2.builder().x(1).y(1).build();
      Vector2 other = Vector2.builder().x(x).y(y).build();
      // When
      boolean isTouching = origin.isTouching(other);
      // Then
      assertThat("Vectors should be touching", isTouching, is(true));
    }

    @Test
    @DisplayName("Should touch when overlap")
    void shouldReturnTrueWhenTouching(){
      // Given
      Vector2 origin = Vector2.builder().x(1).y(1).build();
      Vector2 other = Vector2.builder().x(1).y(1).build();
      // When
      boolean isTouching = origin.isTouching(other);
      // Then
      assertThat("Vectors should be touching", isTouching, is(true));
    }
  }

}