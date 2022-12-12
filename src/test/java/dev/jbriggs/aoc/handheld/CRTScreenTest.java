package dev.jbriggs.aoc.handheld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CRT Screen tests")
class CRTScreenTest {

  CRTScreen screen;

  @BeforeEach
  public void beforeEach() {
    screen = new CRTScreen();
  }

  @Nested
  @DisplayName("IsPixelLitTests")
  class IsPixelLitTests {
    @Test
    @DisplayName("Should return true if pixel is lit")
    void shouldReturnTrueIfPixelIsLit() {
      // Given
      screen.updateRegisterValues();
      // When
      boolean result = screen.isPixelLitAtCycle(1);
      // Then
      assertThat("Result should be true", result, is(true));
    }

    @Test
    @DisplayName("Should return false if pixel is not lit")
    void shouldReturnFalseIfPixelIsNotLit() {
      // Given
      screen.updateRegisterValues();
      screen.addToXRegister(15);
      screen.updateRegisterValues();
      // When
      boolean result = screen.isPixelLitAtCycle(2);
      // Then
      assertThat("Result should be true", result, is(false));
    }
  }

  @Nested
  @DisplayName("Print screen tests")
  class PrintScreenTests{

    @Test
    @DisplayName("Should print a screen of dimensions 1x1")
    void shouldPrintAScreenOfDimensions1X1(){
      // Given
      screen.updateRegisterValues();
      // When
      String currentScreen = screen.printScreen(1, 1);
      // Then
      assertThat("Result should be #", currentScreen, is("#"));
    }

    @Test
    @DisplayName("Should print a screen of dimensions 3x1")
    void shouldPrintAScreenOfDimensions3X1(){
      // Given
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      // When
      String currentScreen = screen.printScreen(3, 1);
      // Then
      assertThat("Result should be #", currentScreen, is("###"));
    }

    @Test
    @DisplayName("Should print a screen of dimensions 4x1")
    void shouldPrintAScreenOfDimensions4X1(){
      // Given
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      // When
      String currentScreen = screen.printScreen(4, 1);
      // Then
      assertThat("Result should be #", currentScreen, is("###."));
    }

    @Test
    @DisplayName("Should print a screen of dimensions 4x2")
    void shouldPrintAScreenOfDimensions4X2(){
      // Given
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      screen.updateRegisterValues();
      // When
      String currentScreen = screen.printScreen(4, 2);
      // Then
      assertThat("Result should be #", currentScreen, is("""
          ###.
          ###."""));
    }
  }
}
