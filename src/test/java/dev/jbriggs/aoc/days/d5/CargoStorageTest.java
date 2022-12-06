package dev.jbriggs.aoc.days.d5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Cargo storage test")
class CargoStorageTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test @DisplayName("Should be able to take one stack")
    void shouldBeAbleToTakeOneStack () {
      // Given
      String basicInput = """
          [A]
           1\s""";
      // When
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // Then
      assertThat("Storage should have one stack",
          cargoStorage.getStacks().size(), is(1));
      assertThat("Storage stack should have letter M",
          cargoStorage.getStack(1).hasItem("[A]"), is(true));
    }

    @Test @DisplayName("Should be able to take one stack with multiple items")
    void shouldBeAbleToTakeOneStackWithMultipleItems () {
      // Given
      String basicInput = """
          [A]
          [B]
          [C]
           1\s""";
      // When
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // Then
      assertThat("Storage should have one stack",
          cargoStorage.getStacks().size(), is(1));
      assertThat("Storage stack should have letter A",
          cargoStorage.getStack(1).hasItem("[A]"), is(true));
      assertThat("Storage stack should have letter B",
          cargoStorage.getStack(1).hasItem("[B]"), is(true));
      assertThat("Storage stack should have letter C",
          cargoStorage.getStack(1).hasItem("[C]"), is(true));
      assertThat("Storage stack should have letter A on top",
          cargoStorage.getStack(1).peekTop(), is("[A]"));
    }

    @Test @DisplayName("Should be able to take two stacks with one items each")
    void shouldBeAbleToTakeOneStackWithOneItemEach () {
      // Given
      String basicInput = """
          [A] [B]
           1   2\s""";
      // When
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // Then
      assertThat("Storage should have two stack",
          cargoStorage.getStacks().size(), is(2));
      assertThat("Storage stack 1 should have letter A",
          cargoStorage.getStack(1).hasItem("[A]"), is(true));
      assertThat("Storage stack 2 should have letter B",
          cargoStorage.getStack(2).hasItem("[B]"), is(true));
    }

    @Test @DisplayName("Should be able to take two stacks with multiple items each")
    void shouldBeAbleToTakeOneStackWithMultipleItemsEach () {
      // Given
      String basicInput = """
          [A] [X]
          [B] [Y]
           1   2\s""";
      // When
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // Then
      assertThat("Storage should have two stack",
          cargoStorage.getStacks().size(), is(2));
      assertThat("Storage stack 1 should have letter A",
          cargoStorage.getStack(1).hasItem("[A]"), is(true));
      assertThat("Storage stack 1 should have letter B",
          cargoStorage.getStack(1).hasItem("[B]"), is(true));
      assertThat("Storage stack 2 should have letter X",
          cargoStorage.getStack(2).hasItem("[X]"), is(true));
      assertThat("Storage stack 2 should have letter Y",
          cargoStorage.getStack(2).hasItem("[Y]"), is(true));
    }

    @Test
    @DisplayName("Should be able to skip a stack")
    void shouldBeAbleToSkipAStack () {
      // Given
      String basicInput = """
          [A]     [B]
           1   2   3\s""";
      // When
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // Then
      assertThat("Storage should have three stacks",
          cargoStorage.getStacks().size(), is(3));
      assertThat("Storage stack 1 should have letter A",
          cargoStorage.getStack(1).hasItem("[A]"), is(true));
      assertThat("Storage stack 2 should be empty",
          cargoStorage.getStack(2).size(), is(0));
      assertThat("Storage stack 3 should have letter B",
          cargoStorage.getStack(3).hasItem("[B]"), is(true));
    }
  }

  @Nested
  @DisplayName("Moving tests")
  class MovingTests{
    @Test
    @DisplayName("Should move 1 from 1 to 2")
    void shouldMove1From1To2 () {
      // Given
      String basicInput = """
          [A]     [B]
           1   2   3\s""";
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // When
      cargoStorage.moveCargo(1, 1, 2);
      // Then
      assertThat("Storage should have three stacks",
          cargoStorage.getStacks().size(), is(3));
      assertThat("Storage stack 1 be empty",
          cargoStorage.getStack(1).isEmpty(), is(true));
      assertThat("Storage stack 2 should have letter A",
          cargoStorage.getStack(2).hasItem("[A]"), is(true));
      assertThat("Storage stack 3 should have letter B",
          cargoStorage.getStack(3).hasItem("[B]"), is(true));
    }

    @Test
    @DisplayName("Should move 2 from 1 to 2")
    void shouldMove2From1To2() {
      // Given
      String basicInput = """
          [A]     [D]
          [B]     [F]
           1   2   3\s""";
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // When
      cargoStorage.moveCargo(2, 1, 2);
      // Then
      assertThat("Storage should have three stacks",
          cargoStorage.getStacks().size(), is(3));
      assertThat("Storage stack 1 be empty",
          cargoStorage.getStack(1).isEmpty(), is(true));
      assertThat("Storage stack 2 should have letter B on top",
          cargoStorage.getStack(2).peekTop(), is("[B]"));
      assertThat("Storage stack 2 should have letter A",
          cargoStorage.getStack(2).hasItem("[A]"), is(true));
      assertThat("Storage stack 3 should have letter D on top",
          cargoStorage.getStack(3).peekTop(), is("[D]"));
    }
  }

  @Nested
  @DisplayName("Moving enhanced tests")
  class MovingEnhancedTests{
    @Test
    @DisplayName("Should move 1 from 1 to 2")
    void shouldMove1From1To2 () {
      // Given
      String basicInput = """
          [A]     [B]
           1   2   3\s""";
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // When
      cargoStorage.moveCargoRetainOrder(1, 1, 2);
      // Then
      assertThat("Storage should have three stacks",
          cargoStorage.getStacks().size(), is(3));
      assertThat("Storage stack 1 be empty",
          cargoStorage.getStack(1).isEmpty(), is(true));
      assertThat("Storage stack 2 should have letter A",
          cargoStorage.getStack(2).hasItem("[A]"), is(true));
      assertThat("Storage stack 3 should have letter B",
          cargoStorage.getStack(3).hasItem("[B]"), is(true));
    }

    @Test
    @DisplayName("Should move 2 from 1 to 2")
    void shouldMove2From1To2() {
      // Given
      String basicInput = """
          [A]     [D]
          [B]     [F]
           1   2   3\s""";
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // When
      cargoStorage.moveCargoRetainOrder(2, 1, 2);
      // Then
      assertThat("Storage should have three stacks",
          cargoStorage.getStacks().size(), is(3));
      assertThat("Storage stack 1 be empty",
          cargoStorage.getStack(1).isEmpty(), is(true));
      assertThat("Storage stack 2 should have letter A on top",
          cargoStorage.getStack(2).peekTop(), is("[A]"));
      assertThat("Storage stack 2 should have letter B",
          cargoStorage.getStack(2).hasItem("[B]"), is(true));
      assertThat("Storage stack 3 should have letter D on top",
          cargoStorage.getStack(3).peekTop(), is("[D]"));
    }
  }

  @Nested
  @DisplayName("Read all top tests")
  class ReadAllTopTests{
    @Test
    @DisplayName("Should read all the top")
    void shouldReadAllTheTop() {
      // Given
      String basicInput = """
                          [O]
          [H]     [L]     [Y]
          [B] [E] [F] [L] [X]
           1   2   3   4   5\s""";
      CargoStorage cargoStorage = new CargoStorage(basicInput);
      // When
      String result = cargoStorage.readTopOfStorage();
      // Then
      assertThat("Should read top of stacks",
          result, is("HELLO"));
    }
  }
}
