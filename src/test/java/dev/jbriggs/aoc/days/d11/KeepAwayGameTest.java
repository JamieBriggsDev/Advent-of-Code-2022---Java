package dev.jbriggs.aoc.days.d11;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Operator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Keep away game test")
class KeepAwayGameTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize with 1 monkey")
    void shouldInitializeWith1Monkey() {
      // Given
      String input = """
          Monkey 1:
            Starting items: 79, 98
            Operation: new = old * 19
            Test: divisible by 23
              If true: throw to monkey 2
              If false: throw to monkey 3""";
      // When
      KeepAwayGame keepAwayGame = new KeepAwayGame(singletonList(input));
      // Then
      assertThat("Game should have 1 monkey", keepAwayGame.getMonkeys().size(),
          is(1));
      LinkedList<Long> currentlyHolding = new LinkedList<>();
      currentlyHolding.add(79L);
      currentlyHolding.add(98L);
      assertThat("Game should map monkey 1 correctly",
          keepAwayGame.getMonkeys().get(1),
          is(Monkey.builder().currentlyHolding(currentlyHolding).operation(
              MonkeyOperation.builder().operator(Operator.MULTIPLY)
                  .modificationValue(19).build()).test(
              MonkeyDivisibleTest.builder().denominatorValue(23).trueMonkeyId(2)
                  .falseMonkeyId(3).build()).build()));
    }

    @Test
    @DisplayName("Should initialize with 2 monkeys")
    void shouldInitializeWith2Monkeys() {
      // Given
      String input1 = """
          Monkey 1:
            Starting items: 79, 98
            Operation: new = old * 19
            Test: divisible by 23
              If true: throw to monkey 2
              If false: throw to monkey 3""";
      String input2 = """
          Monkey 2:
            Starting items: 54, 65, 75, 74
            Operation: new = old + 6
            Test: divisible by 19
             If true: throw to monkey 2
             If false: throw to monkey 0""";
      // When
      KeepAwayGame keepAwayGame = new KeepAwayGame(Arrays.asList(input1, input2));
      // Then
      assertThat("Game should have 2 monkeys", keepAwayGame.getMonkeys().size(),
          is(2));
      LinkedList<Long> currentlyHolding1 = new LinkedList<>();
      currentlyHolding1.add(79L);
      currentlyHolding1.add(98L);
      assertThat("Game should map monkey 1 correctly",
          keepAwayGame.getMonkeys().get(1),
          is(Monkey.builder().currentlyHolding(currentlyHolding1).operation(
              MonkeyOperation.builder().operator(Operator.MULTIPLY)
                  .modificationValue(19).build()).test(
              MonkeyDivisibleTest.builder().denominatorValue(23).trueMonkeyId(2)
                  .falseMonkeyId(3).build()).build()));
      LinkedList<Long> currentlyHolding2 = new LinkedList<>();
      currentlyHolding2.add(54L);
      currentlyHolding2.add(65L);
      currentlyHolding2.add(75L);
      currentlyHolding2.add(74L);
      assertThat("Game should map monkey 2 correctly",
          keepAwayGame.getMonkeys().get(2),
          is(Monkey.builder().currentlyHolding(currentlyHolding2).operation(
              MonkeyOperation.builder().operator(Operator.ADDITION)
                  .modificationValue(6).build()).test(
              MonkeyDivisibleTest.builder().denominatorValue(19).trueMonkeyId(2)
                  .falseMonkeyId(0).build()).build()));
    }
  }

  @Nested
  @DisplayName("Turn tests")
  class TurnTests{
    @Test
    @DisplayName("Monkey 0 should perform turn")
    void shouldPerformRound(){
      // Given
      KeepAwayGame keepAwayGame = new KeepAwayGame(getTestMonkeys());
      // When
      keepAwayGame.performTurn(0);
      // Then
      assertThat("Monkey 0 should be holding 0 items", keepAwayGame.getMonkeys().get(0).getCurrentlyHolding().size(), is(0));
      assertThat("Monkey 0 should should have inspection count of 2", keepAwayGame.getMonkeys().get(0).getInspectionCount(), is(2));
      assertThat("Monkey 1 should be holding 4 items", keepAwayGame.getMonkeys().get(1).getCurrentlyHolding().size(), is(4));
      assertThat("Monkey 2 should be holding 3 items", keepAwayGame.getMonkeys().get(2).getCurrentlyHolding().size(), is(3));
      assertThat("Monkey 3 should be holding 3 items", keepAwayGame.getMonkeys().get(3).getCurrentlyHolding().size(), is(3));
      assertThat("Monkey 3 should have new items", keepAwayGame.getMonkeys().get(3).getCurrentlyHolding(), hasItems(500L, 620L));
    }
  }

  @Nested
  @DisplayName("Round tests")
  class RoundTests{

    @Test
    @DisplayName("Should perform round")
    void shouldPerformRound(){
      // Given
      KeepAwayGame keepAwayGame = new KeepAwayGame(getTestMonkeys());
      // When
      keepAwayGame.performRound();
      // Then
      assertThat("Monkey 0 should be holding 4 items", keepAwayGame.getMonkeys().get(0).getCurrentlyHolding().size(), is(4));
      assertThat("Monkey 1 should be holding 6 items", keepAwayGame.getMonkeys().get(1).getCurrentlyHolding().size(), is(6));
      assertThat("Monkey 2 should be holding 0 items", keepAwayGame.getMonkeys().get(2).getCurrentlyHolding().size(), is(0));
      assertThat("Monkey 3 should be holding 0 items", keepAwayGame.getMonkeys().get(3).getCurrentlyHolding().size(), is(0));
    }
  }

  @Nested
  @DisplayName("Get total monkey business tests")
  class GetTotalMonkeyBusinessTests{

    @Test
    @DisplayName("Should get total monkey business after 20 rounds")
    void shouldGetTotalMonkeyBusinessAfter20Rounds(){
      // Given
      KeepAwayGame keepAwayGame = new KeepAwayGame(getTestMonkeys());
      keepAwayGame.runGame(20);
      // When
      Long result = keepAwayGame.getTotalMonkeyBusiness();
      // Then
      assertThat("Result should be 10605", result, is(10605L));
    }

  }


  public List<String> getTestMonkeys(){
    return Arrays.asList("""
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3""",
        """
          Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0""",
        """
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3""",
        """
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1""");
  }

}