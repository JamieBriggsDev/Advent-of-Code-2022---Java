package dev.jbriggs.aoc.days.d11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class KeepAwayGame {

  private boolean shouldWorry = false;

  private static final Pattern ITEMS_OPERATION_PATTERN = Pattern.compile(
      "Monkey (\\d+):\\s+Starting items: ([\\d, ]+)\\s+"
          + "Operation: new = (old [+*-\\/] [\\d old]+)\\s+"
          + "Test: divisible by (\\d+)\\s+If true: throw to monkey (\\d+)\\s+"
          + "If false: throw to monkey (\\d+)");
  private final HashMap<Integer, Monkey> monkeys;

  public KeepAwayGame(List<String> rawInput, boolean shouldWorry) {
    this.shouldWorry = shouldWorry;
    monkeys = new HashMap<>();
    for (String input : rawInput) {
      Matcher itemsOperationMatcher = ITEMS_OPERATION_PATTERN.matcher(input);
      if (itemsOperationMatcher.matches()) {
        int monkeyId = Integer.parseInt(itemsOperationMatcher.group(1));
        String[] itemsList = itemsOperationMatcher.group(2).split(", ");
        String operationRaw = itemsOperationMatcher.group(3);
        Integer denominatorValue = Integer.valueOf(
            itemsOperationMatcher.group(4));
        Integer trueMonkeyId = Integer.valueOf(itemsOperationMatcher.group(5));
        Integer falseMonkeyId = Integer.valueOf(itemsOperationMatcher.group(6));

        LinkedList<Long> holding = new LinkedList<>();
        Arrays.stream(itemsList).mapToLong(Long::valueOf)
            .forEach(holding::add);
        MonkeyOperation operation = new MonkeyOperation(operationRaw);
        MonkeyDivisibleTest divisibleTest = new MonkeyDivisibleTest(
            denominatorValue,
            trueMonkeyId,
            falseMonkeyId);
        Monkey monkey = new Monkey(holding, operation, divisibleTest);
        monkeys.put(monkeyId, monkey);
      }
    }
  }
  public KeepAwayGame(List<String> rawInput) {
    this(rawInput, false);
  }

  public void performTurn(int monkeyId) {
    Monkey monkeyTurn = monkeys.get(monkeyId);
    while(!monkeyTurn.getCurrentlyHolding().isEmpty()){
      monkeyTurn.performInspection();
      Long itemBeingInspected = monkeyTurn.getCurrentlyHolding().poll();
      itemBeingInspected = monkeyTurn.getOperation().perform(itemBeingInspected);
      if(Boolean.FALSE.equals(shouldWorry)){
        itemBeingInspected /= 3;
      }
      monkeys.get(monkeyTurn.getTest().test(itemBeingInspected)).getCurrentlyHolding().add(itemBeingInspected);
    }
  }

  public void runGame(int rounds) {
    for(int i = 0; i < rounds; i++){
      performRound();
    }
  }
  public void performRound() {
    for(int i = 0; i < monkeys.size(); i++){
      performTurn(i);
    }
  }

  public Long getTotalMonkeyBusiness() {
    long[] ints = monkeys.values().stream().mapToLong(Monkey::getInspectionCount)
        .sorted().toArray();
    return ints[ints.length-2] * ints[ints.length-1];
  }
}
