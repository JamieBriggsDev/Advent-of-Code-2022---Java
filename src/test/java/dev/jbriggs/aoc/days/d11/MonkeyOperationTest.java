package dev.jbriggs.aoc.days.d11;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import dev.jbriggs.aoc.core.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Monkey operation test")
class MonkeyOperationTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize with addition operator")
    void shouldInitializeWithAdditionOperator() {
      // Given
      MonkeyOperation operation = new MonkeyOperation("old + 24");
      // Then
      assertThat("Operation modification value should be 24", operation.getModificationValue(),
          is(24));
      assertThat("Operation operator should be ADDITION",
          operation.getOperator(), is(Operator.ADDITION));
    }

    @Test
    @DisplayName("Should initialize with subtract operator")
    void shouldInitializeWithSubtractOperator() {
      // Given
      MonkeyOperation operation = new MonkeyOperation("old - 12");
      // Then
      assertThat("Operation modification value should be 12", operation.getModificationValue(),
          is(12));
      assertThat("Operation operator should be SUBTRACT",
          operation.getOperator(), is(Operator.SUBTRACT));
    }

    @Test
    @DisplayName("Should initialize with multiply operator")
    void shouldInitializeWithMultiplyOperator() {
      // Given
      MonkeyOperation operation = new MonkeyOperation("old * 5");
      // Then
      assertThat("Operation modification value should be 5", operation.getModificationValue(),
          is(5));
      assertThat("Operation operator should be MULTIPLY",
          operation.getOperator(), is(Operator.MULTIPLY));
    }

    @Test
    @DisplayName("Should initialize with divide operator")
    void shouldInitializeWithDivideOperator() {
      // Given
      MonkeyOperation operation = new MonkeyOperation("old / 2");
      // Then
      assertThat("Operation modification value should be 2", operation.getModificationValue(),
          is(2));
      assertThat("Operation operator should be DIVIDE",
          operation.getOperator(), is(Operator.DIVIDE));
    }

    @Test
    @DisplayName("Should set modification value to null when multiply 'old'")
    void shouldSetModificationValueToNullWhenMultiplyOld(){
      // Given
      MonkeyOperation operation = new MonkeyOperation("old * old");
      // Then
      assertThat("Operation modification value should be null", operation.getModificationValue(),
          is(nullValue()));
      assertThat("Operation operator should be MULTIPLY",
          operation.getOperator(), is(Operator.MULTIPLY));
    }
  }

  @Nested
  @DisplayName("Perform operation tests")
  class PerformOperationTests{

    @Nested
    @DisplayName("When modify value is set")
    class PermanentModifyValue{
      @Test
      @DisplayName("Should add to input")
      void shouldAddFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.ADDITION).modificationValue(2).build();
        // When
        Long result = operation.perform(2L);
        // Then
        assertThat("Result should be 4", result, is(4));
      }

      @Test
      @DisplayName("Should subtract from input")
      void shouldSubtractFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.SUBTRACT).modificationValue(2).build();
        // When
        Long result = operation.perform(10L);
        // Then
        assertThat("Result should be 8", result, is(8));
      }

      @Test
      @DisplayName("Should multiply from input")
      void shouldMultiplyFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.MULTIPLY).modificationValue(3).build();
        // When
        Long result = operation.perform(10L);
        // Then
        assertThat("Result should be 30", result, is(30));
      }

      @Test
      @DisplayName("Should divide from input")
      void shouldDivideFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.DIVIDE).modificationValue(3).build();
        // When
        Long result = operation.perform(10L);
        // Then
        assertThat("Result should be 3", result, is(3));
      }

      @Test
      @DisplayName("Should divide from input (round down)")
      void shouldDivideFromInputRoundDown(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.DIVIDE).modificationValue(3).build();
        // When
        Long result = operation.perform(5L);
        // Then
        assertThat("Result should be 1", result, is(1));
      }
    }

    @Nested
    @DisplayName("When modify value is NOT set and should use old value")
    class OldModifyValue{
      @Test
      @DisplayName("Should add to input")
      void shouldAddFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.ADDITION).build();
        // When
        Long result = operation.perform(3L);
        // Then
        assertThat("Result should be 6", result, is(6));
      }

      @Test
      @DisplayName("Should subtract from input")
      void shouldSubtractFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.SUBTRACT).build();
        // When
        Long result = operation.perform(5L);
        // Then
        assertThat("Result should be 0", result, is(0));
      }

      @Test
      @DisplayName("Should multiply from input")
      void shouldMultiplyFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.MULTIPLY).build();
        // When
        Long result = operation.perform(10L);
        // Then
        assertThat("Result should be 100", result, is(100));
      }

      @Test
      @DisplayName("Should divide from input")
      void shouldDivideFromInput(){
        // Given
        MonkeyOperation operation = MonkeyOperation.builder().operator(Operator.DIVIDE).build();
        // When
        Long result = operation.perform(10L);
        // Then
        assertThat("Result should be 1", result, is(1));
      }

    }

  }
}