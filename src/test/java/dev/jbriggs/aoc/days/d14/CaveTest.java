package dev.jbriggs.aoc.days.d14;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import dev.jbriggs.aoc.core.Collidable;
import dev.jbriggs.aoc.core.Vector2;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Cave test")
class CaveTest {

  @Test
  @DisplayName("Should place grain of sand")
  void shouldPlaceGrainOfSand() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    boolean result = cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItem(SandGrain.builder().x(500).y(9).build()));
    assertThat("Result should be true", result, is(true));
  }

  @Test
  @DisplayName("Should place grain of sand with walls in between")
  void shouldPlaceGrainOfSandWithWallsInBetween() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(499).y(0).build())
            .point(Vector2.builder().x(499).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(501).y(0).build())
            .point(Vector2.builder().x(501).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    boolean result = cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItem(SandGrain.builder().x(500).y(9).build()));
    assertThat("Result should be true", result, is(true));
  }

  @Test
  @DisplayName("Should place two grains of sand with walls in between")
  void shouldPlaceTwoGrainsOfSandWithWallsInBetween() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(499).y(0).build())
            .point(Vector2.builder().x(499).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(501).y(0).build())
            .point(Vector2.builder().x(501).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    cave.dropSand();
    cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItems(SandGrain.builder().x(500).y(9).build(),
            SandGrain.builder().x(500).y(8).build()));
  }

  @Test
  @DisplayName("Should move down left when blocked down")
  void shouldMoveDownLeftWhenBlockedDown() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    cave.dropSand();
    cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItems(SandGrain.builder().x(500).y(9).build(),
            SandGrain.builder().x(499).y(9).build()));
  }

  @Test
  @DisplayName("Should move down right when blocked down and down left")
  void shouldMoveDownRightWhenBlockedDownAndDownLeft() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(499).y(0).build())
            .point(Vector2.builder().x(499).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    cave.dropSand();
    cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItems(SandGrain.builder().x(500).y(9).build(),
            SandGrain.builder().x(501).y(9).build()));
  }

  @Test
  @DisplayName("Should place 5 grains of sand with walls in between")
  void shouldPlace5GrainsOfSandWithWallsInBetween() {
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(495).y(10).build())
            .point(Vector2.builder().x(505).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(498).y(0).build())
            .point(Vector2.builder().x(498).y(10).build()).build());
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(502).y(0).build())
            .point(Vector2.builder().x(502).y(10).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    cave.dropSand();
    cave.dropSand();
    cave.dropSand();
    cave.dropSand();
    cave.dropSand();
    // Then
    assertThat("Sand should rest in correct position",
        cave.getCollidableCollection(),
        hasItems(
            SandGrain.builder().x(500).y(9).build(),
            SandGrain.builder().x(499).y(9).build(),
            SandGrain.builder().x(501).y(9).build(),
            SandGrain.builder().x(500).y(8).build(),
            SandGrain.builder().x(499).y(8).build()
        ));
  }

  @Test
  @DisplayName("Should return lowest Y value when grain passed")
  void shouldReturnLowestYValueWhenGrainPassed(){
    // Given
    List<Collidable<Vector2>> collidableList = new ArrayList<>();
    collidableList.add(
        Wall.builder().point(Vector2.builder().x(490).y(15).build())
            .point(Vector2.builder().x(495).y(15).build()).build());
    CollidableCollection collection = CollidableCollection.builder()
        .collidableList(collidableList).build();
    Cave cave = new Cave(collection);
    // When
    boolean result = cave.dropSand();
    // Then
    assertThat("Result should be false if sand falls past walls", result, is(false));
  }
}