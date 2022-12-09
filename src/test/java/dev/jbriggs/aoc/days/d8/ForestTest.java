package dev.jbriggs.aoc.days.d8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Forest test")
class ForestTest {

  @Nested
  @DisplayName("Is tree visible tests")
  class IsTreeVisibleTests {

    @Test
    @DisplayName("Should initialize forest of 4 trees")
    void shouldInitializeForestOf4Trees() {
      // Given
      // When
      Forest forest = new Forest(2, 2);
      // Then
      assertThat("Height should be 2", forest.getHeight(), is(2));
      assertThat("Length should be 2", forest.getLength(), is(2));
    }

    @Test
    @DisplayName("Should place trees in correct position")
    void shouldPlaceTreesInCorrectPosition() {
      // Given
      Forest forest = new Forest(2, 2);
      // When
      forest.placeItem(Tree.builder().height(1).build(), 0, 0);
      forest.placeItem(Tree.builder().height(2).build(), 1, 0);
      forest.placeItem(Tree.builder().height(3).build(), 0, 1);
      forest.placeItem(Tree.builder().height(4).build(), 1, 1);
      // Then
      assertThat("Tree 0,0 height should be 1",
          forest.getItem(0, 0).getHeight(), is(1));
      assertThat("Tree 1,0 height should be 2",
          forest.getItem(1, 0).getHeight(), is(2));
      assertThat("Tree 0,1 height should be 3",
          forest.getItem(0, 1).getHeight(), is(3));
      assertThat("Tree 1,1 height should be 4",
          forest.getItem(1, 1).getHeight(), is(4));
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "1,0", "2,0", "0,1", "2,1", "0,2", "1,2",
        "2,2",})
    @DisplayName("Middle tree should be visible from sides")
    void middleTreeShouldBeVisibleFromSides(int x, int y) {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(9).build(), 0, 0);
      forest.placeItem(Tree.builder().height(9).build(), 1, 0);
      forest.placeItem(Tree.builder().height(9).build(), 2, 0);
      forest.placeItem(Tree.builder().height(9).build(), 0, 1);
      forest.placeItem(Tree.builder().height(9).build(), 1, 1);
      forest.placeItem(Tree.builder().height(9).build(), 2, 1);
      forest.placeItem(Tree.builder().height(9).build(), 0, 2);
      forest.placeItem(Tree.builder().height(9).build(), 1, 2);
      forest.placeItem(Tree.builder().height(9).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(x, y);
      // Then
      assertThat("Tree should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should be visible")
    void middleTreeShouldBeVisible() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(1).build(), 0, 0);
      forest.placeItem(Tree.builder().height(1).build(), 1, 0);
      forest.placeItem(Tree.builder().height(1).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(1).build(), 0, 2);
      forest.placeItem(Tree.builder().height(1).build(), 1, 2);
      forest.placeItem(Tree.builder().height(1).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should be visible from left")
    void middleTreeShouldBeVisibleFromLeft() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(9).build(), 0, 0);
      forest.placeItem(Tree.builder().height(9).build(), 1, 0);
      forest.placeItem(Tree.builder().height(9).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(9).build(), 2, 1);
      forest.placeItem(Tree.builder().height(9).build(), 0, 2);
      forest.placeItem(Tree.builder().height(9).build(), 1, 2);
      forest.placeItem(Tree.builder().height(9).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should be visible from right")
    void middleTreeShouldBeVisibleFromRight() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(9).build(), 0, 0);
      forest.placeItem(Tree.builder().height(9).build(), 1, 0);
      forest.placeItem(Tree.builder().height(9).build(), 2, 0);
      forest.placeItem(Tree.builder().height(9).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(9).build(), 0, 2);
      forest.placeItem(Tree.builder().height(9).build(), 1, 2);
      forest.placeItem(Tree.builder().height(9).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should be visible from top")
    void middleTreeShouldBeVisibleFromTop() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(9).build(), 0, 0);
      forest.placeItem(Tree.builder().height(1).build(), 1, 0);
      forest.placeItem(Tree.builder().height(9).build(), 2, 0);
      forest.placeItem(Tree.builder().height(9).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(9).build(), 2, 1);
      forest.placeItem(Tree.builder().height(9).build(), 0, 2);
      forest.placeItem(Tree.builder().height(9).build(), 1, 2);
      forest.placeItem(Tree.builder().height(9).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should be visible from bottom")
    void middleTreeShouldBeVisibleFromBottom() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(9).build(), 0, 0);
      forest.placeItem(Tree.builder().height(9).build(), 1, 0);
      forest.placeItem(Tree.builder().height(9).build(), 2, 0);
      forest.placeItem(Tree.builder().height(9).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(9).build(), 2, 1);
      forest.placeItem(Tree.builder().height(9).build(), 0, 2);
      forest.placeItem(Tree.builder().height(1).build(), 1, 2);
      forest.placeItem(Tree.builder().height(9).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should be visible", treeVisible, is(true));
    }

    @Test
    @DisplayName("Middle tree should not be visible")
    void middleTreeShouldNotBeVisible() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(5).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(5).build(), 0, 1);
      forest.placeItem(Tree.builder().height(1).build(), 1, 1);
      forest.placeItem(Tree.builder().height(5).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should not be visible", treeVisible, is(false));
    }

    @Test
    @DisplayName("Middle tree should not be visible when matching")
    void middleTreeShouldNotBeVisibleWhenMatching() {
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(5).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(5).build(), 0, 1);
      forest.placeItem(Tree.builder().height(5).build(), 1, 1);
      forest.placeItem(Tree.builder().height(5).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      boolean treeVisible = forest.isTreeVisible(1, 1);
      // Then
      assertThat("Tree (1,1) should not be visible", treeVisible, is(false));
    }
  }

  @Nested
  @DisplayName("Get total viewing distance tests")
  class GetTotalViewingDistanceTests{

    @Test
    @DisplayName("Should return 1 when blocked")
    void shouldReturn0WhenBlocked(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(5).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(5).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(5).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 1);
      // Then
      assertThat("Tree (1,1) viewing distance should be 1", viewingDistance, is(1L));
    }

    @Test
    @DisplayName("Should return 1 when not blocked left")
    void shouldReturn1WhenNotBlockedLeft(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(5).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(5).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 1);
      // Then
      assertThat("Tree (1,1) viewing distance should be 1", viewingDistance, is(1L));
    }

    @Test
    @DisplayName("Should return 1 when not blocked left or right")
    void shouldReturn2WhenNotBlockedLeftOrRight(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(5).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 1);
      // Then
      assertThat("Tree (1,1) viewing distance should be 1", viewingDistance, is(1L));
    }

    @Test
    @DisplayName("Should return 1 when not blocked left, right or top")
    void shouldReturn2WhenNotBlockedLeftRightOrTop(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(1).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(5).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 1);
      // Then
      assertThat("Tree (1,1) viewing distance should be 1", viewingDistance, is(1L));
    }

    @Test
    @DisplayName("Should return 1 when not blocked left, right, top or bottom")
    void shouldReturn2WhenNotBlockedLeftRightTopOrBottom(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(5).build(), 0, 0);
      forest.placeItem(Tree.builder().height(1).build(), 1, 0);
      forest.placeItem(Tree.builder().height(5).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(2).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(1).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 1);
      // Then
      assertThat("Tree (1,1) viewing distance should be 4", viewingDistance, is(1L));
    }

    @Test
    @DisplayName("Should return 0 when top middle of forest and open")
    void shouldReturn6WhenTopMiddleOfForestAndOpen(){
      // Given
      Forest forest = new Forest(3, 3);
      forest.placeItem(Tree.builder().height(1).build(), 0, 0);
      forest.placeItem(Tree.builder().height(2).build(), 1, 0);
      forest.placeItem(Tree.builder().height(1).build(), 2, 0);
      forest.placeItem(Tree.builder().height(1).build(), 0, 1);
      forest.placeItem(Tree.builder().height(1).build(), 1, 1);
      forest.placeItem(Tree.builder().height(1).build(), 2, 1);
      forest.placeItem(Tree.builder().height(5).build(), 0, 2);
      forest.placeItem(Tree.builder().height(1).build(), 1, 2);
      forest.placeItem(Tree.builder().height(5).build(), 2, 2);
      // When
      Long viewingDistance = forest.getScenicScore(1, 0);
      // Then
      assertThat("Tree (1,1) viewing distance should be 0", viewingDistance, is(0L));
    }
  }
}
