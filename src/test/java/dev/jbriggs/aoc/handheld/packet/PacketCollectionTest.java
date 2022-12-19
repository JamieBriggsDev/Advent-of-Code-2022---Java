package dev.jbriggs.aoc.handheld.packet;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Packet collection test")
class PacketCollectionTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize collection with single value")
    void shouldInitializeCollectionWithSingleValue() {
      // Given
      String input = "[1]";
      // When
      PacketCollection collection = new PacketCollection(input);
      // Then
      assertThat("Collection should have single value", collection.item,
          contains(PacketValue.builder().item(1).build()));
    }

    @Test
    @DisplayName("Should initialize collection with multiple values")
    void shouldInitializeCollectionWithMultipleValues() {
      // Given
      String input = "[1,12,24]";
      // When
      PacketCollection collection = new PacketCollection(input);
      // Then
      assertThat("Collection should have single value", collection.item,
          contains(
              PacketValue.builder().item(1).build(),
              PacketValue.builder().item(12).build(),
              PacketValue.builder().item(24).build()
          ));
    }

    @Test
    @DisplayName("Should initialize collection with nested collection")
    void shouldInitializeCollectionWithNextedCollection() {
      // Given
      String input = "[[1,12,24]]";
      // When
      PacketCollection collection = new PacketCollection(input);
      // Then
      assertThat("Collection should have single value", collection.item,
          contains(
              PacketCollection.builder().item(
                  Arrays.asList(
                      PacketValue.builder().item(1).build(),
                      PacketValue.builder().item(12).build(),
                      PacketValue.builder().item(24).build()
                  )).build()));
    }

    @Test
    @DisplayName("Should initialize collection with double nested collection")
    void shouldInitializeCollectionWithDoubleNextedCollection() {
      // Given
      String input = "[[[1,12,24]]]";
      // When
      PacketCollection collection = new PacketCollection(input);
      // Then
      assertThat("Collection should have single value", collection.item,
          contains(
              PacketCollection.builder().item(
                  singletonList(
                      PacketCollection.builder().item(

                          Arrays.asList(
                              PacketValue.builder().item(1).build(),
                              PacketValue.builder().item(12).build(),
                              PacketValue.builder().item(24).build()
                          )).build())).build()));

    }

    @Test
    @DisplayName("Should initialize collection with nested collection and item")
    void shouldInitializeCollectionWithNextedCollectionAndItem() {
      // Given
      String input = "[[1,12,24],2]";
      // When
      PacketCollection collection = new PacketCollection(input);
      // Then
      assertThat("Collection should have single value", collection.item,
          contains(
              PacketCollection.builder().item(
                  Arrays.asList(
                      PacketValue.builder().item(1).build(),
                      PacketValue.builder().item(12).build(),
                      PacketValue.builder().item(24).build()
                  )).build(),
              PacketValue.builder().item(2).build()));
    }
  }

  @Nested
  @DisplayName("Comparable tests")
  class ComparableTests {

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
      @DisplayName("Should return -1 if other item smaller")
      void shouldReturn1IfOtherItemSmaller() {
        // Given
        PacketCollection a = PacketCollection.builder()
            .item(Arrays.asList(PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder()
            .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be -1", result, is(-1));
      }

      @Test
      @DisplayName("Should return 1 if other item larger")
      void shouldReturn1IfOtherItemLarger() {
        // Given
        PacketCollection a = PacketCollection.builder()
            .item(Arrays.asList(PacketValue.builder().item(1).build())).build();
        PacketCollection b = PacketCollection.builder()
            .item(Arrays.asList(PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 1", result, is(1));
      }
    }

    @Nested
    @DisplayName("Double item collection tests")
    class DoubleItemCollectionTests {

      @Test
      @DisplayName("Should return 0 if lists are similar")
      void shouldReturn0IfListsAreSimilar() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 0", result, is(0));
      }

      @Test
      @DisplayName("Should return -1 if other item smaller")
      void shouldReturn1IfOtherItemSmaller() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be -1", result, is(-1));
      }

      @Test
      @DisplayName("Should return 1 if other item larger")
      void shouldReturn1IfOtherItemLarger() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 1", result, is(1));
      }

    }

    @Nested
    @DisplayName("Triple item collection tests")
    class TripleItemCollectionTests {

      @Test
      @DisplayName("Should return 0 if lists are similar")
      void shouldReturn0IfListsAreSimilar() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 0", result, is(0));
      }

      @Test
      @DisplayName("Should return -1 if other item smaller")
      void shouldReturn1IfOtherItemSmaller() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be -1", result, is(-1));
      }

      @Test
      @DisplayName("Should return 1 if other item larger")
      void shouldReturn1IfOtherItemLarger() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 1", result, is(1));
      }

    }

    @Nested
    @DisplayName("Collection size tests")
    class CollectionSizeTests {

      @Test
      @DisplayName("Should return 1 when source collection runs out of items")
      void shouldReturn1WhenSourceCollectionRunsOutOfItems() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 1", result, is(1));
      }

      @Test
      @DisplayName("Should return -1 when target collection runs out of items")
      void shouldReturn1WhenTargetCollectionRunsOutOfItems() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build(),
                PacketValue.builder().item(2).build())).build();
        PacketCollection b = PacketCollection.builder().item(
            Arrays.asList(PacketValue.builder().item(1).build(),
                PacketValue.builder().item(1).build())).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be -1", result, is(-1));
      }
    }

    @Nested
    @DisplayName("Miscellaneous tests")
    class MiscellaneousTests {

      @Test
      @DisplayName("Should change target to collection when comparing and return -1")
      void shouldChangeSourceToCollectionWhenComparingAgainstListAndReturn1() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
                singletonList(PacketValue.builder().item(2).build()))
            .build();
        PacketValue b = PacketValue.builder().item(1).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be -1", result, is(-1));
      }

      @Test
      @DisplayName("Should change target to collection when comparing and return 1")
      void shouldChangeSourceToCollectionWhenComparingAgainstListAndReturnNegative1() {
        // Given
        PacketCollection a = PacketCollection.builder().item(
                singletonList(PacketValue.builder().item(1).build()))
            .build();
        PacketValue b = PacketValue.builder().item(2).build();
        // When
        int result = a.compareTo(b);
        // Then
        assertThat("Result should be 1", result, is(1));
      }
    }
  }
}
