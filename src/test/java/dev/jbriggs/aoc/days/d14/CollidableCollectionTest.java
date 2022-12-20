package dev.jbriggs.aoc.days.d14;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.jbriggs.aoc.core.Vector2;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Collidable collection test")
class CollidableCollectionTest {

  @Test
  @DisplayName("Should return false when no objects in collection")
  void shouldReturnFalseWhenNoObjectsInCollection() {
    // Given
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(Collections.emptyList()).build();
    // When
    boolean result = collection.collidesWithAnyObject(
        Vector2.builder().x(0).y(0).build());
    // Then
    assertThat("Result should be false", result, is(false));
  }

  @Test
  @DisplayName("Should return false when one object in collection which doesn't collide")
  void shouldReturnFalseWhenOneObjectInCollectionWhichDoesntCollide() {
    // Given
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(singletonList(SandGrain.builder().x(1).y(1).build()))
        .build();
    // When
    boolean result = collection.collidesWithAnyObject(
        Vector2.builder().x(0).y(0).build());
    // Then
    assertThat("Result should be false", result, is(false));
  }

  @Test
  @DisplayName("Should return true when one object in collection which does collide")
  void shouldReturnTrueWhenOneObjectInCollectionWhichDoesCollide() {
    // Given
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(singletonList(SandGrain.builder().x(0).y(0).build()))
        .build();
    // When
    boolean result = collection.collidesWithAnyObject(
        Vector2.builder().x(0).y(0).build());
    // Then
    assertThat("Result should be true", result, is(true));
  }

  @Test
  @DisplayName("Should not check all elements if found early")
  void shouldNotCheckAllElementsIfFoundEarly() {
    // Given
    Vector2 input = Vector2.builder().x(0).y(0).build();
    SandGrain a = Mockito.mock(SandGrain.class);
    SandGrain b = Mockito.mock(SandGrain.class);
    when(a.collidesWith(input)).thenReturn(true);
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(Arrays.asList(a, b))
        .build();
    // When
    boolean result = collection.collidesWithAnyObject(
        input);
    // Then
    assertThat("Result should be true", result, is(true));
    verify(a, times(1)).collidesWith(input);
    verify(b, times(0)).collidesWith(input);
  }

}