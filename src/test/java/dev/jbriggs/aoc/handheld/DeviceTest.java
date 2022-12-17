package dev.jbriggs.aoc.handheld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.storage.TerminalStorage;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Device test")
class DeviceTest {

  Device device;

  @BeforeEach
  public void beforeEach() {
    device = Device.builder().addModule(new TerminalReader())
        .addModule(new CRTScreen()).build();
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
      int result = device.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position when last 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenLast4CharactersUniqueIn5CharacterInput() {
      // Given
      String input = "aacde";
      // When
      int result = device.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 5", result, is(5));
    }

    @Test
    @DisplayName("Should return marker position when first 4 characters unique in 5 character input")
    void shouldReturnMarkerPositionWhenFirst4CharactersUniqueIn5CharacterInput() {
      // Given
      String input = "abcde";
      // When
      int result = device.findMarkerPosition(input, 4);
      // Then
      assertThat("Marker should be on character 4", result, is(4));
    }

    @Test
    @DisplayName("Should return marker position on complex input")
    void shouldReturnMarkerPositionOnComplexInput() {
      // Given
      String input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
      // When
      int result = device.findMarkerPosition(input, 4);
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
      int result = device.findMarkerPosition(input, 14);
      // Then
      assertThat("Marker should be on character 14", result, is(14));
    }

    @Test
    @DisplayName("Should return marker position when only 14 unique characters and 1 duplicate at start")
    void shouldReturnMarkerPositionWhenOnly14CharactersAndOneDuplicateAtStart() {
      // Given
      String input = "aabcdefghijklmn";
      // When
      int result = device.findMarkerPosition(input, 15);
      // Then
      assertThat("Marker should be on character 15", result, is(15));
    }

  }

  @Nested
  @DisplayName("Signal strength tests")
  class SignalStrengthTests {

    @Test
    @DisplayName("Should read register value at end of cycle")
    void shouldReadSignalCommands() throws HandheldException {
      // Given
      List<String> commands = Arrays.asList("$ noop", "$ addx 3", "$ addx -5");
      // When
      device.readTerminalLines(commands);
      // Then
      assertThat("X should be 1 at end cycle 1",
          ((TerminalReader) device.getReader()).getRegisterValueAtEndOfCycle(1),
          is(1));
      assertThat("X should be 1 at end cycle 2",
          ((TerminalReader) device.getReader()).getRegisterValueAtEndOfCycle(2),
          is(1));
      assertThat("X should be 4 at end cycle 3",
          ((TerminalReader) device.getReader()).getRegisterValueAtEndOfCycle(3),
          is(4));
      assertThat("X should be 4 at end cycle 4",
          ((TerminalReader) device.getReader()).getRegisterValueAtEndOfCycle(4),
          is(4));
      assertThat("X should be -1 at end cycle 5",
          ((TerminalReader) device.getReader()).getRegisterValueAtEndOfCycle(5),
          is(-1));
    }

    @Test
    @DisplayName("Should read register value during cycle")
    void shouldReadRegisterValueDuringCycle() throws HandheldException {
      // Given
      List<String> commands = Arrays.asList("$ noop", "$ addx 3", "$ addx -5");
      // When
      device.readTerminalLines(commands);
      // Then
      assertThat("X should be 1 during cycle 1",
          ((TerminalReader) device.getReader()).getRegisterValueDuringCycle(1),
          is(1));
      assertThat("X should be 1 during cycle 2",
          ((TerminalReader) device.getReader()).getRegisterValueDuringCycle(2),
          is(1));
      assertThat("X should be 1 during cycle 3",
          ((TerminalReader) device.getReader()).getRegisterValueDuringCycle(3),
          is(1));
      assertThat("X should be 4 during cycle 4",
          ((TerminalReader) device.getReader()).getRegisterValueDuringCycle(4),
          is(4));
      assertThat("X should be 4 during cycle 5",
          ((TerminalReader) device.getReader()).getRegisterValueDuringCycle(5),
          is(4));
    }
  }
}
