package dev.jbriggs.aoc.handheld.packet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Packet pair test")
class PacketPairTest {


  @Nested
  @DisplayName("In correct order tests")
  class InCorrectOrderTests{
    @Test
    @DisplayName("Should return true when left side smaller value")
    void shouldReturnTrueWhenLeftSideSmallerValue(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[1,1,3,1,1]")).right(
          new PacketCollection("[1,1,5,1,1]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be true as in correct order", result, is(true));
    }
    @Test
    @DisplayName("Should return true when left side smaller value with collections")
    void shouldReturnTrueWhenLeftSideSmallerValueWithCollections(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[[1],[2,3,4]]")).right(
          new PacketCollection("[[1],4]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be true as in correct order", result, is(true));
    }

    @Test
    @DisplayName("Should return false when right side smaller value with mixed types")
    void shouldReturnTrueWhenRightSideSmallerValueWithMixedTypes(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[9]")).right(
          new PacketCollection("[[8,7,6]]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be false as incorrect order", result, is(false));
    }

    @Test
    @DisplayName("Should return true when left side run out of items")
    void shouldReturnTrueWhenLeftSideRunOutOfItems(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[[4,4],4,4]")).right(
          new PacketCollection("[[4,4],4,4,4]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be true as correct order", result, is(true));
    }
    @Test
    @DisplayName("Should return false when right side run out of items")
    void shouldReturnFalseWhenRightSideRunOutOfItems(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[7,7,7,7]")).right(
          new PacketCollection("[7,7,7]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be false as incorrect order", result, is(false));
    }

    @Test
    @DisplayName("Should return false when right side has less empty items")
    void shouldReturnFalseWhenRightSideHasLessEmptyItems(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[[[]]]")).right(
          new PacketCollection("[[]]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be false as incorrect order", result, is(false));
    }

    @Test
    @DisplayName("Should return false as right side is smaller with complicated items")
    void shouldReturnFalseAsRightSideIsSmallerWithComplicatedItems(){
      // Given
      PacketPair packetPair = PacketPair.builder().left(new PacketCollection("[1,[2,[3,[4,[5,6,7]]]],8,9]")).right(
          new PacketCollection("[1,[2,[3,[4,[5,6,0]]]],8,9]")
      ).build();
      // When
      boolean result = packetPair.isInRightOrder();
      // Then
      assertThat("Result should be false as incorrect order", result, is(false));
    }
  }
}