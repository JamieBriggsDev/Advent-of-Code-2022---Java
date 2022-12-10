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

@DisplayName("Rope test")
class RopeTest {

  @Test
  @DisplayName("Should start head and tail at 0,0")
  void shouldStartHeadAndTailAt00() {
    Rope rope = new Rope();
    // When
    Vector2 head = rope.getHead();
    // Then
    assertThat("Head should be at 0,0", head,
        is(Vector2.builder().x(0).y(0).build()));
  }

  @Nested
  @DisplayName("Move head tests")
  class MoveHeadTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head up")
    void shouldMoveHeadUp(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.UP, places);
      // Then
      assertThat("Head should have moved up " + places, rope.getHead(),
          is(Vector2.builder().x(0).y(places).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head down")
    void shouldMoveHeadDown(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.DOWN, places);
      // Then
      assertThat("Head should have moved down " + places, rope.getHead(),
          is(Vector2.builder().x(0).y(-places).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head right")
    void shouldMoveHeadRight(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.RIGHT, places);
      // Then
      assertThat("Head should have moved right " + places, rope.getHead(),
          is(Vector2.builder().x(places).y(0).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head left")
    void shouldMoveHeadLeft(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.LEFT, places);
      // Then
      assertThat("Head should have moved left " + places, rope.getHead(),
          is(Vector2.builder().x(-places).y(0).build()));
    }
  }

  @Nested
  @DisplayName("Move tail behind tests")
  class MoveTailBehindTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head up")
    void shouldMoveTailUp(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.UP, places);
      // Then
      assertThat("Tail should have moved up " + places, rope.getTail(),
          is(Vector2.builder().x(0).y(places - 1).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head down")
    void shouldMoveTailDown(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.DOWN, places);
      // Then
      assertThat("Tail should have moved down " + places, rope.getTail(),
          is(Vector2.builder().x(0).y(-places + 1).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head right")
    void shouldMoveTailRight(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.RIGHT, places);
      // Then
      assertThat("Tail should have moved right " + places, rope.getTail(),
          is(Vector2.builder().x(places - 1).y(0).build()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should move head left")
    void shouldMoveTailLeft(int places) {
      // Given
      Rope rope = new Rope();
      // When
      rope.move(Direction.LEFT, places);
      // Then
      assertThat("Tail should have moved left " + places, rope.getTail(),
          is(Vector2.builder().x(-places + 1).y(0).build()));
    }
  }

  @Nested
  @DisplayName("Move tail diagonally tests")
  class MoveTailDiagonallyTests {

    @Nested
    @DisplayName("Moving up")
    class MovingUp {

      @Test
      @DisplayName("Should move up right")
      void shouldMoveUpRight() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(1).y(1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.UP, 1);
        // Then
        assertThat("Tail should have moved up and diagonally", rope.getTail(),
            is(Vector2.builder().x(1).y(1).build()));
      }

      @Test
      @DisplayName("Should move up left")
      void shouldMoveUpLeft() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(-1).y(1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.UP, 1);
        // Then
        assertThat("Tail should have moved down and diagonally", rope.getTail(),
            is(Vector2.builder().x(-1).y(1).build()));
      }

    }

    @Nested
    @DisplayName("Moving down")
    class MovingDown {

      @Test
      @DisplayName("Should move down right")
      void shouldMoveDownRight() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(1).y(-1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.DOWN, 1);
        // Then
        assertThat("Tail should have moved up and diagonally", rope.getTail(),
            is(Vector2.builder().x(1).y(-1).build()));
      }

      @Test
      @DisplayName("Should move down left")
      void shouldMoveDownLeft() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(-1).y(-1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.DOWN, 1);
        // Then
        assertThat("Tail should have moved down and diagonally", rope.getTail(),
            is(Vector2.builder().x(-1).y(-1).build()));
      }
    }

    @Nested
    @DisplayName("Moving right")
    class MovingRight {

      @Test
      @DisplayName("Should move right up")
      void shouldMoveUpRight() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(1).y(1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.RIGHT, 1);
        // Then
        assertThat("Tail should have moved right and diagonally",
            rope.getTail(), is(Vector2.builder().x(1).y(1).build()));
      }

      @Test
      @DisplayName("Should move right down")
      void shouldMoveUpLeft() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(1).y(-1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.RIGHT, 1);
        // Then
        assertThat("Tail should have moved right and diagonally",
            rope.getTail(), is(Vector2.builder().x(1).y(-1).build()));
      }

    }

    @Nested
    @DisplayName("Moving left")
    class MovingLeft {

      @Test
      @DisplayName("Should move left up")
      void shouldMoveUpRight() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(-1).y(1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.LEFT, 1);
        // Then
        assertThat("Tail should have moved right and diagonally",
            rope.getTail(), is(Vector2.builder().x(-1).y(1).build()));
      }

      @Test
      @DisplayName("Should move left down")
      void shouldMoveUpLeft() {
        // Given
        Rope rope = Rope.builder().head(Vector2.builder().x(-1).y(-1).build())
            .tail(Vector2.builder().x(0).y(0).build()).build();
        // When
        rope.move(Direction.LEFT, 1);
        // Then
        assertThat("Tail should have moved right and diagonally",
            rope.getTail(), is(Vector2.builder().x(-1).y(-1).build()));
      }
    }
  }
}