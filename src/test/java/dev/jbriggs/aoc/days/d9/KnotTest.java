package dev.jbriggs.aoc.days.d9;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Knot test")
class KnotTest {
  
  
  @Nested
  @DisplayName("Move knot tests")
  class MoveHeadTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move knot up")
    void shouldMoveHeadUp(int places) {
      // Given
      Knot knot = Knot.builder().x(0).y(0).build();;
      // When
      knot.moveAll(Direction.UP, places);
      // Then
      assertThat("Head should have moved up " + places, knot,
          is(Vector2.builder().x(0).y(places).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move knot down")
    void shouldMoveHeadDown(int places) {
      // Given
      Knot knot = Knot.builder().x(0).y(0).build();;
      // When
      knot.moveAll(Direction.DOWN, places);
      // Then
      assertThat("Head should have moved down " + places, knot,
          is(Vector2.builder().x(0).y(-places).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move knot right")
    void shouldMoveHeadRight(int places) {
      // Given
      Knot knot = Knot.builder().x(0).y(0).build();;
      // When
      knot.moveAll(Direction.RIGHT, places);
      // Then
      assertThat("Head should have moved right " + places, knot,
          is(Vector2.builder().x(places).y(0).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move knot left")
    void shouldMoveHeadLeft(int places) {
      // Given
      Knot knot = Knot.builder().x(0).y(0).build();;
      // When
      knot.moveAll(Direction.LEFT, places);
      // Then
      assertThat("Head should have moved left " + places, knot,
          is(Vector2.builder().x(-places).y(0).build()));
    }
  }



    @Nested
    @DisplayName("Move child behind tests")
    class MoveChildBehindTests {

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5})
      @DisplayName("Should move knot up")
      void shouldMoveChildUp(int places) {
        // Given
        Knot child = Knot.builder().x(0).y(0).build();
        Knot knot = Knot.builder().x(0).y(0).childKnot(child).build();
        // When
        knot.moveAll(Direction.UP, places);
        // Then
        assertThat("Child should have moved up " + places, child,
            is(Vector2.builder().x(0).y(places - 1).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5})
      @DisplayName("Should move knot down")
      void shouldMoveChildDown(int places) {
        // Given
        Knot child = Knot.builder().x(0).y(0).build();
        Knot knot = Knot.builder().x(0).y(0).childKnot(child).build();
        // When
        knot.moveAll(Direction.DOWN, places);
        // Then
        assertThat("Child should have moved down " + places, child,
            is(Vector2.builder().x(0).y(-places + 1).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5})
      @DisplayName("Should move knot right")
      void shouldMoveChildRight(int places) {
        // Given
        Knot child = Knot.builder().x(0).y(0).build();
        Knot knot = Knot.builder().x(0).y(0).childKnot(child).build();
        // When
        knot.moveAll(Direction.RIGHT, places);
        // Then
        assertThat("Child should have moved right " + places, child,
            is(Vector2.builder().x(places - 1).y(0).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5})
      @DisplayName("Should move knot left")
      void shouldMoveChildLeft(int places) {
        // Given
        Knot child = Knot.builder().x(0).y(0).build();
        Knot knot = Knot.builder().x(0).y(0).childKnot(child).build();
        // When
        knot.moveAll(Direction.LEFT, places);
        // Then
        assertThat("Child should have moved left " + places, child,
            is(Vector2.builder().x(-places + 1).y(0).build()));
      }
    }

    @Nested
    @DisplayName("Move child diagonally tests")
    class MoveChildDiagonallyTests {

      @Nested
      @DisplayName("Moving up")
      class MovingUp {

        @Test
        @DisplayName("Should move up right")
        void shouldMoveUpRight() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(1).y(1).childKnot(child).build();
          // When
          knot.moveAll(Direction.UP, 1);
          // Then
          assertThat("Child should have moved up and diagonally", child,
              is(Vector2.builder().x(1).y(1).build()));
        }

        @Test
        @DisplayName("Should move up left")
        void shouldMoveUpLeft() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(-1).y(1).childKnot(child).build();
          // When
          knot.moveAll(Direction.UP, 1);
          // Then
          assertThat("Child should have moved down and diagonally",
              child, is(Knot.builder().x(-1).y(1).build()));
        }

      }

      @Nested
      @DisplayName("Moving down")
      class MovingDown {

        @Test
        @DisplayName("Should move down right")
        void shouldMoveDownRight() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(1).y(-1).childKnot(child).build();
          // When
          knot.moveAll(Direction.DOWN, 1);
          // Then
          assertThat("Child should have moved up and diagonally", child,
              is(Vector2.builder().x(1).y(-1).build()));
        }

        @Test
        @DisplayName("Should move down left")
        void shouldMoveDownLeft() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(-1).y(-1).childKnot(child).build();
          // When
          knot.moveAll(Direction.DOWN, 1);
          // Then
          assertThat("Child should have moved down and diagonally",
              child, is(Vector2.builder().x(-1).y(-1).build()));
        }
      }

      @Nested
      @DisplayName("Moving right")
      class MovingRight {

        @Test
        @DisplayName("Should move right up")
        void shouldMoveUpRight() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(1).y(1).childKnot(child).build();
          // When
          knot.moveAll(Direction.RIGHT, 1);
          // Then
          assertThat("Child should have moved right and diagonally",
              child, is(Vector2.builder().x(1).y(1).build()));
        }

        @Test
        @DisplayName("Should move right down")
        void shouldMoveUpLeft() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(1).y(-1).childKnot(child).build();
          // When
          knot.moveAll(Direction.RIGHT, 1);
          // Then
          assertThat("Child should have moved right and diagonally",
              child, is(Vector2.builder().x(1).y(-1).build()));
        }

      }

      @Nested
      @DisplayName("Moving left")
      class MovingLeft {

        @Test
        @DisplayName("Should move left up")
        void shouldMoveUpRight() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(-1).y(1).childKnot(child).build();
          // When
          knot.moveAll(Direction.LEFT, 1);
          // Then
          assertThat("Child should have moved right and diagonally",
              child, is(Vector2.builder().x(-1).y(1).build()));
        }

        @Test
        @DisplayName("Should move left down")
        void shouldMoveUpLeft() {
          // Given
          Knot child = Knot.builder().x(0).y(0).build();
          Knot knot = Knot.builder().x(-1).y(-1).childKnot(child).build();
          // When
          knot.moveAll(Direction.LEFT, 1);
          // Then
          assertThat("Child should have moved right and diagonally",
              child, is(Vector2.builder().x(-1).y(-1).build()));
        }
      }
    }
  
    
}