package dev.jbriggs.aoc.handheld.register;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Memory register handler tests")
class MemoryRegisterHolderTest {

  @Test
  @DisplayName("Should add current x value to register values")
  public void shouldAddCurrentXValueToRegisterValues() {
    // Given
    MemoryRegisterHolder handler = new MemoryRegisterHolder();
    // When
    handler.updateRegisterValues();
    // Then
    assertThat("Current cycle value should be 1", handler.getCurrentCycle(), is(1));
    assertThat("Current x value", handler.getXRegister(), is(1));
    assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(1), is(1));
  }

  @Test
  @DisplayName("Should add current x value to register values multiple times")
  public void shouldAddCurrentXValueToRegisterValuesMultipleTimes() {
    // Given
    MemoryRegisterHolder handler = new MemoryRegisterHolder();
    // When
    handler.updateRegisterValues();
    handler.updateRegisterValues();
    handler.updateRegisterValues();
    // Then
    assertThat("Current cycle value should be 3", handler.getCurrentCycle(), is(3));
    assertThat("Current x value", handler.getXRegister(), is(1));
    assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(1), is(1));
    assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(2), is(1));
    assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(3), is(1));
  }

  @Nested
  @DisplayName("Add to register tests")
  class AddToRegisterTests{
    @Test
    @DisplayName("Should add to x value")
    public void shouldAddToXValue() {
      // Given
      MemoryRegisterHolder handler = new MemoryRegisterHolder();
      // When
      handler.updateRegisterValues();
      handler.addToXRegister(1);
      handler.updateRegisterValues();
      // Then
      assertThat("Current cycle value should be 1", handler.getCurrentCycle(), is(2));
      assertThat("Current x value", handler.getXRegister(), is(2));
      assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(1), is(1));
      assertThat("Register values should only contain 1", handler.getXRegisterValueAtCycle(2), is(2));
    }

    @Test
    @DisplayName("Should add to x value multiple times")
    public void shouldAddToXValueMultipleTimes() {
      // Given
      MemoryRegisterHolder handler = new MemoryRegisterHolder();
      // When
      handler.updateRegisterValues();
      handler.updateRegisterValues();
      handler.addToXRegister(1);
      handler.updateRegisterValues();
      handler.addToXRegister(2);
      handler.updateRegisterValues();
      handler.addToXRegister(3);
      handler.updateRegisterValues();
      // Then
      assertThat("Current cycle value should be 5", handler.getCurrentCycle(), is(5));
      assertThat("Current x value", handler.getXRegister(), is(7));
      assertThat("Register values at cycle at 1 is 1", handler.getXRegisterValueAtCycle(1), is(1));
      assertThat("Register values at cycle at 2 is 1", handler.getXRegisterValueAtCycle(2), is(1));
      assertThat("Register values at cycle at 3 is 2", handler.getXRegisterValueAtCycle(3), is(2));
      assertThat("Register values at cycle at 4 is 4", handler.getXRegisterValueAtCycle(4), is(4));
      assertThat("Register values at cycle at 5 is 7", handler.getXRegisterValueAtCycle(5), is(7));
    }
  }
}