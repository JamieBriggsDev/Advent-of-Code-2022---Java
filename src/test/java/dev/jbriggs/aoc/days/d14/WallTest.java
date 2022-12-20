package dev.jbriggs.aoc.days.d14;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Vector2;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Wall test")
class WallTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize wall with two points")
    void shouldInitializeWallWithTwoPoints() {
      // Given
      String input = "0,0 -> 10,0";
      Wall wall = new Wall(input);
      // When
      List<Vector2> points = wall.getPoints();
      // Then
      assertThat("Points should be in correct order", points,
          contains(Vector2.builder().x(0).y(0).build(),
              Vector2.builder().x(10).y(0).build()));
    }

    @Test
    @DisplayName("Should initialize wall with three points")
    void shouldInitializeWallWithThreePoints() {
      // Given
      String input = "0,0 -> 10,0 -> 10,10";
      Wall wall = new Wall(input);
      // When
      List<Vector2> points = wall.getPoints();
      // Then
      assertThat("Points should be in correct order", points,
          contains(
              Vector2.builder().x(0).y(0).build(),
              Vector2.builder().x(10).y(0).build(),
              Vector2.builder().x(10).y(10).build()
              ));
    }
  }

  @Nested
  @DisplayName("Collidable tests")
  class CollidableTests {

    @Test
    @DisplayName("Should return false if point does not collide")
    void shouldReturnFalseIfPointDoesNotCollide() {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(10).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(Vector2.builder().x(11).y(0).build());
      // Then
      assertThat("Should return false if vector does not collide", result,
          is(false));
    }

    @Test
    @DisplayName("Should return true when collides with point A")
    void shouldReturnTrueWhenCollidesWithPointA() {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(10).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(Vector2.builder().x(0).y(0).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }


    @Test
    @DisplayName("Should return true when collides with point B")
    void shouldReturnTrueWhenCollidesWithPointB() {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(10).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(Vector2.builder().x(10).y(0).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return true when collides with any point along line inclusive x axis")
    void shouldReturnTrueWhenCollidesWithAnyPointAlongLineInclusive(int xPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(10).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(xPos).y(0).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return true when collides with any point along line inclusive x axis backwards")
    void shouldReturnTrueWhenCollidesWithAnyPointAlongLineInclusiveBackwards(
        int xPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(10).y(0).build())
          .point(Vector2.builder().x(0).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(xPos).y(0).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return false when parallel to line along x axis")
    void shouldReturnFalseWhenParallelToLineAlongXAxis(int xPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(10).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(xPos).y(1).build());
      // Then
      assertThat("Should return false if vector doesn't collides", result,
          is(false));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return false when parallel to line along x axis backwards")
    void shouldReturnFalseWhenParallelToLineAlongXAxisBackwards(int xPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(10).y(0).build())
          .point(Vector2.builder().x(0).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(xPos).y(1).build());
      // Then
      assertThat("Should return false if vector doesn't collides", result,
          is(false));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return true when collides with any point along line inclusive y axis")
    void shouldReturnTrueWhenCollidesWithAnyPointAlongLineInclusiveYAxis(
        int yPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(0).y(10).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(0).y(yPos).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return true when collides with any point along line inclusive y axis backwards")
    void shouldReturnTrueWhenCollidesWithAnyPointAlongLineInclusiveYAxisBackwards(
        int yPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(10).build())
          .point(Vector2.builder().x(0).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(0).y(yPos).build());
      // Then
      assertThat("Should return true if vector collides", result, is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return false when parallel to line along y axis")
    void shouldReturnFalseWhenParallelToLineAlongYAxis(int yPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(0).build())
          .point(Vector2.builder().x(0).y(10).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(1).y(yPos).build());
      // Then
      assertThat("Should return false if vector doesn't collides", result,
          is(false));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Should return false when parallel to line along y axis backwards")
    void shouldReturnFalseWhenParallelToLineAlongYAxisBackwards(int yPos) {
      // Given
      Wall wall = Wall.builder().point(Vector2.builder().x(0).y(10).build())
          .point(Vector2.builder().x(0).y(0).build()).build();
      // When
      boolean result = wall.collidesWith(
          Vector2.builder().x(1).y(yPos).build());
      // Then
      assertThat("Should return false if vector doesn't collides", result,
          is(false));
    }

  }
}
