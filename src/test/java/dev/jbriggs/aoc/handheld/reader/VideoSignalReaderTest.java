package dev.jbriggs.aoc.handheld.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.handheld.HandheldException;
import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Video signal reader test")
class VideoSignalReaderTest {

  VideoSignalReader reader;

  @BeforeEach
  public void beforeEach() {
    reader = new VideoSignalReader();
    reader.setMemoryRegisterHolder(new MemoryRegisterHolder());
  }


  @Nested
  @DisplayName("Add to register 'addx' tests")
  class AddTests {

    @Test
    @DisplayName("Should add to register")
    void shouldAddToRegister() throws HandheldException {
      // Given
      String input = "$ addx 1";
      // When
      reader.read(input);
      // Then
      assertThat("Memory register X should have value 1 at cycle 1", reader.getRegisterValueAtEndOfCycle(1),
          is(1));
      assertThat("Memory register X should have value 2 at cycle 2", reader.getRegisterValueAtEndOfCycle(2),
          is(2));
    }

    @Test
    @DisplayName("Should add to register twice")
    void shouldAddToRegisterTwice() throws HandheldException {
      // Given
      List<String> input = Arrays.asList("$ addx 1", "$ addx 2");
      // When
      for(String command : input){
        reader.read(command);
      }
      // Then
      assertThat("Memory register X should have value 1 at cycle 1", reader.getRegisterValueAtEndOfCycle(1),
          is(1));
      assertThat("Memory register X should have value 2 at cycle 2", reader.getRegisterValueAtEndOfCycle(2),
          is(2));
      assertThat("Memory register X should have value 2 at cycle 3", reader.getRegisterValueAtEndOfCycle(3),
          is(2));
      assertThat("Memory register X should have value 4 at cycle 4", reader.getRegisterValueAtEndOfCycle(4),
          is(4));
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
      reader.readAll(commands);
      // Then
      assertThat("X should be 1 at end cycle 1",
          reader.getRegisterValueAtEndOfCycle(1),
          is(1));
      assertThat("X should be 1 at end cycle 2",
          reader.getRegisterValueAtEndOfCycle(2),
          is(1));
      assertThat("X should be 4 at end cycle 3",
          reader.getRegisterValueAtEndOfCycle(3),
          is(4));
      assertThat("X should be 4 at end cycle 4",
          reader.getRegisterValueAtEndOfCycle(4),
          is(4));
      assertThat("X should be -1 at end cycle 5",
          reader.getRegisterValueAtEndOfCycle(5),
          is(-1));
    }

    @Test
    @DisplayName("Should read register value during cycle")
    void shouldReadRegisterValueDuringCycle() throws HandheldException {
      // Given
      List<String> commands = Arrays.asList("$ noop", "$ addx 3", "$ addx -5");
      // When
      reader.readAll(commands);
      // Then
      assertThat("X should be 1 during cycle 1",
          reader.getRegisterValueDuringCycle(1),
          is(1));
      assertThat("X should be 1 during cycle 2",
          reader.getRegisterValueDuringCycle(2),
          is(1));
      assertThat("X should be 1 during cycle 3",
          reader.getRegisterValueDuringCycle(3),
          is(1));
      assertThat("X should be 4 during cycle 4",
          reader.getRegisterValueDuringCycle(4),
          is(4));
      assertThat("X should be 4 during cycle 5",
          reader.getRegisterValueDuringCycle(5),
          is(4));
    }
  }
}