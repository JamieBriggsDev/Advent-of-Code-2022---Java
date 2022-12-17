package dev.jbriggs.aoc.days.d9;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Direction;
import dev.jbriggs.aoc.core.Vector2;
import org.junit.jupiter.api.Disabled;
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
  @DisplayName("Rope length 2 tests")
  class RopeLength2 {

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
          Knot child = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(1).y(1).childKnot(child).build())
              .tail(child).build();
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
          Knot chlid = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(-1).y(1).childKnot(chlid).build())
              .tail(chlid).build();
          // When
          rope.move(Direction.UP, 1);
          // Then
          assertThat("Tail should have moved down and diagonally",
              rope.getTail(), is(Knot.builder().x(-1).y(1).build()));
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
          Rope rope = Rope.builder()
              .head(Knot.builder().x(1).y(-1).childKnot(child).build())
              .tail(child).build();
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
          Knot child = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(-1).y(-1).childKnot(child).build())
              .tail(child).build();
          // When
          rope.move(Direction.DOWN, 1);
          // Then
          assertThat("Tail should have moved down and diagonally",
              rope.getTail(), is(Vector2.builder().x(-1).y(-1).build()));
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
          Rope rope = Rope.builder()
              .head(Knot.builder().x(1).y(1).childKnot(child).build())
              .tail(child).build();
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
          Knot child = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(1).y(-1).childKnot(child).build())
              .tail(child).build();
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
          Knot child = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(-1).y(1).childKnot(child).build())
              .tail(child).build();
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
          Knot child = Knot.builder().x(0).y(0).build();
          Rope rope = Rope.builder()
              .head(Knot.builder().x(-1).y(-1).childKnot(child).build())
              .tail(child).build();
          // When
          rope.move(Direction.LEFT, 1);
          // Then
          assertThat("Tail should have moved right and diagonally",
              rope.getTail(), is(Vector2.builder().x(-1).y(-1).build()));
        }
      }
    }
  }

  @Nested
  @DisplayName("Rope length 10 tests")
  class RopeLength10 {

    @Nested
    @DisplayName("Move tail behind tests")
    class MoveTailBehindTests {

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
      @DisplayName("Should move head up")
      void shouldMoveTailUp(int places) {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.UP, places);
        // Then
        assertThat("Tail should have moved up " + places, rope.getTail(),
            is(Vector2.builder().x(0).y(0).build()));
      }

      @Test
      @DisplayName("Should finally move tail up 1 after 10 places")
      void shouldFinalMoveTailUp1After10Places() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.UP, 10);
        // Then
        assertThat("Tail should have moved up 1", rope.getTail(),
            is(Vector2.builder().x(0).y(1).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
      @DisplayName("Should move head down")
      void shouldMoveTailDown(int places) {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.DOWN, places);
        // Then
        assertThat("Tail should have moved down " + places, rope.getTail(),
            is(Vector2.builder().x(0).y(0).build()));
      }

      @Test
      @DisplayName("Should finally move tail down 1 after 10 places")
      void shouldFinalMoveTailDown1After10Places() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.DOWN, 10);
        // Then
        assertThat("Tail should have moved down 1", rope.getTail(),
            is(Vector2.builder().x(0).y(-1).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
      @DisplayName("Should move head right")
      void shouldMoveTailRight(int places) {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.RIGHT, places);
        // Then
        assertThat("Tail should have moved right " + places, rope.getTail(),
            is(Vector2.builder().x(0).y(0).build()));
      }

      @Test
      @DisplayName("Should finally move tail right 1 after 10 places")
      void shouldFinalMoveTailRight1After10Places() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.RIGHT, 10);
        // Then
        assertThat("Tail should have moved right 1", rope.getTail(),
            is(Vector2.builder().x(1).y(0).build()));
      }

      @ParameterizedTest
      @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
      @DisplayName("Should move head left")
      void shouldMoveTailLeft(int places) {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.LEFT, places);
        // Then
        assertThat("Tail should have moved left " + places, rope.getTail(),
            is(Vector2.builder().x(0).y(0).build()));
      }

      @Test
      @DisplayName("Should finally move tail left 1 after 10 places")
      void shouldFinalMoveTailLeft1After10Places() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.LEFT, 10);
        // Then
        assertThat("Tail should have moved left 1", rope.getTail(),
            is(Vector2.builder().x(-1).y(0).build()));
      }
    }

    @Nested
    @DisplayName("Trail rope tests")
    class TrailRopeTests {

      @Test
      @DisplayName("Should trail rope properly 1")
      void shouldTrailRopeProperly1() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.RIGHT, 4);
        rope.move(Direction.UP, 4);
        // Then
        assertThat("Head should be on 4,4", rope.getHead(),
            is(Vector2.builder().x(4).y(4).build()));
        assertThat("Second knot should be on 4,3",
            rope.getHead().getChildKnot(),
            is(Vector2.builder().x(4).y(3).build()));
        assertThat("Third knot should be on 4,2",
            rope.getHead().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(4).y(2).build()));
        assertThat("Fourth knot should be on 3,2",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(3).y(2).build()));
        assertThat("Fifth knot should be on 2,2",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot(), is(Vector2.builder().x(2).y(2).build()));
        assertThat("Sixth knot should be on 1,1",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot(),
            is(Vector2.builder().x(1).y(1).build()));
        assertThat("Seventh knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
        assertThat("Eight knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
        assertThat("Ninth knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot(), is(Vector2.builder().x(0).y(0).build()));
        assertThat("Tenth knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
      }

      @Test
      @Disabled(value = "Not finished")
      @DisplayName("Should trail rope properly 2")
      void shouldTrailRopeProperly2() {
        // Given
        Rope rope = new Rope(10);
        // When
        rope.move(Direction.RIGHT, 4);
        rope.move(Direction.UP, 4);
        rope.move(Direction.LEFT, 3);
        rope.move(Direction.DOWN, 1);
        rope.move(Direction.RIGHT, 4);
        rope.move(Direction.DOWN, 1);
        rope.move(Direction.LEFT, 5);
        rope.move(Direction.RIGHT, 2);
        // Then
        assertThat("Head should be on 4,4", rope.getHead(),
            is(Vector2.builder().x(4).y(4).build()));
        assertThat("Second knot should be on 4,3",
            rope.getHead().getChildKnot(),
            is(Vector2.builder().x(4).y(3).build()));
        assertThat("Third knot should be on 4,2",
            rope.getHead().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(4).y(2).build()));
        assertThat("Fourth knot should be on 3,2",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(3).y(2).build()));
        assertThat("Fifth knot should be on 2,2",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot(), is(Vector2.builder().x(2).y(2).build()));
        assertThat("Sixth knot should be on 1,1",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot(),
            is(Vector2.builder().x(1).y(1).build()));
        assertThat("Seventh knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
        assertThat("Eight knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
        assertThat("Ninth knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot(), is(Vector2.builder().x(0).y(0).build()));
        assertThat("Tenth knot should be on 0,0",
            rope.getHead().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot().getChildKnot().getChildKnot()
                .getChildKnot().getChildKnot(),
            is(Vector2.builder().x(0).y(0).build()));
      }
    }

  }
}
