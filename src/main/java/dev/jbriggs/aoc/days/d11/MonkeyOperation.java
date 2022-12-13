package dev.jbriggs.aoc.days.d11;

import static java.util.Objects.isNull;

import com.google.common.base.Objects;
import dev.jbriggs.aoc.AOCException;
import dev.jbriggs.aoc.core.Operator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class MonkeyOperation {

  private static final Pattern COMMAND_PATTERN = Pattern.compile(
      "^old ([+*-\\/]) ([\\d old]+)$");

  private final Integer modificationValue;
  private final Operator operator;

  public MonkeyOperation(String rawOperation) {
    Matcher matcher = COMMAND_PATTERN.matcher(rawOperation);
    if(matcher.matches()){
      operator = Operator.getBySymbol(matcher.group(1));
      modificationValue =
          "old".equals(matcher.group(2)) ? null : Integer.valueOf(matcher.group(2));
    }else{
      throw new AOCException("Could not read operation: " + rawOperation);
    }
  }

  public Long perform(Long old) {
    Long valueToUse = isNull(modificationValue) ? old : modificationValue;
    return switch (operator){
      case ADDITION -> old + valueToUse;
      case SUBTRACT -> old - valueToUse;
      case MULTIPLY -> old * valueToUse;
      case DIVIDE -> old / valueToUse;
    };
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MonkeyOperation operation = (MonkeyOperation) o;
    return Objects.equal(modificationValue,
        operation.modificationValue) && operator == operation.operator;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(modificationValue, operator);
  }
}
