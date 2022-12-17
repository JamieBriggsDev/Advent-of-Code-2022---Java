package dev.jbriggs.aoc.handheld.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.DeviceException;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MarkerReaderTest {

  private MarkerReader markerReader;

  @BeforeEach
  public void beforeEach(){
    markerReader = new MarkerReader();
  }

  @Nested
  @DisplayName("Marker length 4")
  class MarkerLength4 {

    @Test
    @DisplayName("Should return marker position when only 4 unique characters")
    void shouldReturnMarkerPositionWhenOnly4Characters()
        throws ReaderException {
      // Given
      String input = "abcd";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position when last 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenLast4CharactersUniqueIn5CharacterInput()
        throws ReaderException {
      // Given
      String input = "aacde";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(4);
      // Then
      assertThat("Marker should be on character 5", result, is(5));
    }

    @Test
    @DisplayName("Should return marker position when first 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenFirst4CharactersUniqueIn5CharacterInput()
        throws ReaderException {
      // Given
      String input = "abcde";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position on complex input")
    void shouldReturnMarkerPositionOnComplexInput() throws ReaderException {
      // Given
      String input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(4);
      // Then
      assertThat("Marker should be on character 10", result, is(10));
    }
  }

  @Nested
  @DisplayName("Marker length 14")
  class MarkerLength14 {

    @Test
    @DisplayName("Should return marker position when only 14 unique characters")
    void shouldReturnMarkerPositionWhenOnly14Characters()
        throws ReaderException {
      // Given
      String input = "abcdefghijklmn";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(14);
      // Then
      assertThat("Marker should be on character 14", result, is(14));
    }

    @Test
    @DisplayName("Should return marker position when only 14 unique characters and 1 duplicate at start")
    void shouldReturnMarkerPositionWhenOnly14CharactersAndOneDuplicateAtStart()
        throws ReaderException {
      // Given
      String input = "aabcdefghijklmn";
      markerReader.readAll(Collections.singletonList(input));
      // When
      int result = markerReader.findMarkerPosition(15);
      // Then
      assertThat("Marker should be on character 15", result, is(15));
    }

  }


}