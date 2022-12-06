package dev.jbriggs.aoc.days.d6;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Handheld device test")
class HandheldDeviceTest {

  HandheldDevice handheldDevice;

  @BeforeEach
  public void beforeEach() {
    handheldDevice = new HandheldDevice();
  }

  @Nested
  @DisplayName("Marker length 4")
  class MarkerLength4 {

    @Test
    @DisplayName("Should return marker position when only 4 unique characters")
    void shouldReturnMarkerPositionWhenOnly4Characters() {
      // Given
      String input = "abcd";
      // When
      int result = handheldDevice.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position when last 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenLast4CharactersUniqueIn5CharacterInput() {
      // Given
      String input = "aacde";
      // When
      int result = handheldDevice.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 5", result, is(5));
    }

    @Test
    @DisplayName("Should return marker position when first 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenFirst4CharactersUniqueIn5CharacterInput() {
      // Given
      String input = "abcde";
      // When
      int result = handheldDevice.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position on complex input")
    void shouldReturnMarkerPositionOnComplexInput() {
      // Given
      String input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
      // When
      int result = handheldDevice.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 10", result, is(10));
    }
  }

  @Nested
  @DisplayName("Marker length 14")
  class MarkerLength14 {

    @Test
    @DisplayName("Should return marker position when only 14 unique characters")
    void shouldReturnMarkerPositionWhenOnly14Characters() {
      // Given
      String input = "abcdefghijklmn";
      // When
      int result = handheldDevice.findMarkerPosition(input, 14);
      // Then
      assertThat("Marker should be on character 14", result, is(14));
    }

    @Test
    @DisplayName("Should return marker position when only 14 unique characters and 1 duplicate at start")
    void shouldReturnMarkerPositionWhenOnly14CharactersAndOneDuplicateAtStart() {
      // Given
      String input = "aabcdefghijklmn";
      // When
      int result = handheldDevice.findMarkerPosition(input, 15);
      // Then
      assertThat("Marker should be on character 15", result, is(15));
    }

  }
}
