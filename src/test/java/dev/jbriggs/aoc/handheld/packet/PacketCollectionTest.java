package dev.jbriggs.aoc.handheld.packet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Packet collection test")
class PacketCollectionTest {

  @Nested
  @DisplayName("Single item collection tests")
  class SingleItemCollectionTests {

    @Test
    @DisplayName("Should return 0 if lists are similar")
    void shouldReturn0IfListsAreSimilar() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 0", result, is(0));
    }

    @Test
    @DisplayName("Should return 1 if other item smaller")
    void shouldReturn1IfOtherItemSmaller() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(2).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 1", result, is(1));
    }

    @Test
    @DisplayName("Should return -1 if other item larger")
    void shouldReturn1IfOtherItemLarger() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(2).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be -1", result, is(-1));
    }
  }

  @Nested
  @DisplayName("Double item collection tests")
  class DoubleItemCollectionTests {

    @Test
    @DisplayName("Should return 0 if lists are similar")
    void shouldReturn0IfListsAreSimilar() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 0", result, is(0));
    }

    @Test
    @DisplayName("Should return 1 if other item smaller")
    void shouldReturn1IfOtherItemSmaller() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 1", result, is(1));
    }

    @Test
    @DisplayName("Should return -1 if other item larger")
    void shouldReturn1IfOtherItemLarger() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be -1", result, is(-1));
    }

  }

  @Nested
  @DisplayName("Triple item collection tests")
  class TripleItemCollectionTests {

    @Test
    @DisplayName("Should return 0 if lists are similar")
    void shouldReturn0IfListsAreSimilar() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 0", result, is(0));
    }

    @Test
    @DisplayName("Should return 1 if other item smaller")
    void shouldReturn1IfOtherItemSmaller() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be 1", result, is(1));
    }

    @Test
    @DisplayName("Should return -1 if other item larger")
    void shouldReturn1IfOtherItemLarger() {
      // Given
      PacketCollection a = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build())).build();
      PacketCollection b = PacketCollection.builder()
          .item(Arrays.asList(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(2).build())).build();
      // When
      int result = a.compareTo(b);
      // Then
      assertThat("Result should be -1", result, is(-1));
    }

  }

  @Nested
  @DisplayName("Target collection smaller tests")
  class TargetCollectionSmallerTests {
    // TODO: 18/12/2022 Exit early target smaller
    // TODO: 18/12/2022 Exit early target larger
    // TODO: 18/12/2022 Ran out of items
  }

  // TODO: 18/12/2022 Target list larger, is this possible though?
}