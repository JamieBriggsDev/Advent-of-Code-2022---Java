package dev.jbriggs.aoc.handheld.register;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Memory register handler tests")
class MemoryRegisterHandlerTest {

  @Test
  @DisplayName("Should add current x value to register values")
  public void shouldAddCurrentXValueToRegisterValues() {
    // Given
    MemoryRegisterHandler handler = new MemoryRegisterHandler();
    // When
    handler.modifyNothing();
    // Then
    assertThat("Current cycle value should be 1", handler.getCurrentCycle(), is(1));
    assertThat("Current x value", handler.getXValue(), is(1));
    assertThat("Register values should only contain 1", handler.getXValueAtCycle(1), is(1));
  }

  @Test
  @DisplayName("Should add current x value to register values multiple times")
  public void shouldAddCurrentXValueToRegisterValuesMultipleTimes() {
    // Given
    MemoryRegisterHandler handler = new MemoryRegisterHandler();
    // When
    handler.modifyNothing();
    handler.modifyNothing();
    handler.modifyNothing();
    // Then
    assertThat("Current cycle value should be 3", handler.getCurrentCycle(), is(3));
    assertThat("Current x value", handler.getXValue(), is(1));
    assertThat("Register values should only contain 1", handler.getXValueAtCycle(1), is(1));
    assertThat("Register values should only contain 1", handler.getXValueAtCycle(2), is(1));
    assertThat("Register values should only contain 1", handler.getXValueAtCycle(3), is(1));
  }

  @Nested
  @DisplayName("Add to register tests")
  class AddToRegisterTests{
    @Test
    @DisplayName("Should add to x value")
    public void shouldAddToXValue() {
      // Given
      MemoryRegisterHandler handler = new MemoryRegisterHandler();
      // When
      handler.modifyNothing();
      handler.addToRegister(1);
      // Then
      assertThat("Current cycle value should be 1", handler.getCurrentCycle(), is(2));
      assertThat("Current x value", handler.getXValue(), is(2));
      assertThat("Register values should only contain 1", handler.getXValueAtCycle(1), is(1));
      assertThat("Register values should only contain 1", handler.getXValueAtCycle(2), is(2));
    }

    @Test
    @DisplayName("Should add to x value multiple times")
    public void shouldAddToXValueMultipleTimes() {
      // Given
      MemoryRegisterHandler handler = new MemoryRegisterHandler();
      // When
      handler.modifyNothing();
      handler.addToRegister(1);
      handler.addToRegister(2);
      handler.addToRegister(3);
      // Then
      assertThat("Current cycle value should be 4", handler.getCurrentCycle(), is(4));
      assertThat("Current x value", handler.getXValue(), is(7));
      assertThat("Register values at cycle at 1 is 1", handler.getXValueAtCycle(1), is(1));
      assertThat("Register values at cycle at 2 is 2", handler.getXValueAtCycle(2), is(2));
      assertThat("Register values at cycle at 3 is 4", handler.getXValueAtCycle(3), is(4));
      assertThat("Register values at cycle at 4 is 7", handler.getXValueAtCycle(4), is(7));
    }
  }
}