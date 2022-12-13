package dev.jbriggs.aoc.days.d11;

import com.google.common.base.Objects;
import lombok.Builder;
import lombok.ToString;

@Builder
public record MonkeyDivisibleTest(int denominatorValue, int trueMonkeyId,
                                  int falseMonkeyId) {

  public int test(Long subject) {
    if (subject % denominatorValue == 0) {
      return trueMonkeyId;
    }
    return falseMonkeyId;
  }

  @Override
  public String toString() {
    return "MonkeyDivisibleTest{" +
        "denominatorValue=" + denominatorValue +
        ", trueMonkeyId=" + trueMonkeyId +
        ", falseMonkeyId=" + falseMonkeyId +
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
    MonkeyDivisibleTest that = (MonkeyDivisibleTest) o;
    return denominatorValue == that.denominatorValue
        && trueMonkeyId == that.trueMonkeyId
        && falseMonkeyId == that.falseMonkeyId;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(denominatorValue, trueMonkeyId, falseMonkeyId);
  }
}
