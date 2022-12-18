package dev.jbriggs.aoc.handheld.packet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Packet value test")
class PacketValueTest {

  @Test
  @DisplayName("Should return 1 when comparing positive to negative")
  void shouldReturn1WhenComparingPositiveToNegative(){
    // Given
    PacketValue a = PacketValue.builder().item(2).build();
    PacketValue b = PacketValue.builder().item(1).build();
    // When
    int result = a.compareTo(b);
    // Then
    assertThat("Result should be 1", result, is(1));
  }

  @Test
  @DisplayName("Should return 0 when comparing equal values")
  void shouldReturn0WhenComparingEqualValues(){
    // Given
    PacketValue a = PacketValue.builder().item(1).build();
    PacketValue b = PacketValue.builder().item(1).build();
    // When
    int result = a.compareTo(b);
    // Then
    assertThat("Result should be 0", result, is(0));
  }

  @Test
  @DisplayName("Should return -1 when comparing negative to positive")
  void shouldReturn0WhenComparingNegatuveToPositive(){
    // Given
    PacketValue a = PacketValue.builder().item(1).build();
    PacketValue b = PacketValue.builder().item(2).build();
    // When
    int result = a.compareTo(b);
    // Then
    assertThat("Result should be -1", result, is(-1));
  }
}