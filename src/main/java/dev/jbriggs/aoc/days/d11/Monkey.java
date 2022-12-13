package dev.jbriggs.aoc.days.d11;

import com.google.common.base.Objects;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class Monkey {

  private final LinkedList<Long> currentlyHolding;
  private final MonkeyOperation operation;
  private final MonkeyDivisibleTest test;
  private int inspectionCount;

  public Monkey(LinkedList<Long> currentlyHolding,
      MonkeyOperation operation, MonkeyDivisibleTest test) {
    this.currentlyHolding = currentlyHolding;
    this.operation = operation;
    this.test = test;
    this.inspectionCount = 0;
  }

  public void reliefWorryLevels(int denominator) {
    for (int i = 0; i < currentlyHolding.size(); i++) {
      currentlyHolding.add(currentlyHolding.poll() / denominator);
    }
  }

  public void performInspection() {
    inspectionCount++;
  }

  @Override
  public String toString() {
    return "Monkey{" +
        "currentlyHolding=" + currentlyHolding +
        ", operation=" + operation +
        ", test=" + test +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Monkey monkey = (Monkey) o;
    return Objects.equal(currentlyHolding,
        monkey.currentlyHolding) && Objects.equal(
        operation, monkey.operation) && Objects.equal(test,
        monkey.test);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(currentlyHolding, operation, test);
  }



}
