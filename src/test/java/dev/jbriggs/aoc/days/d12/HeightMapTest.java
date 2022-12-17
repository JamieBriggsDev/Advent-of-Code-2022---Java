package dev.jbriggs.aoc.days.d12;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.pathfinding.GraphNode;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Height map test")
class HeightMapTest {

  @Nested
  @DisplayName("Initialization tests")
  class InitializationTests {

    @Test
    @DisplayName("Should initialize height map of 2x2")
    void shouldInitializeForestOf4Trees() {
      // Given
      List<String> rows = Arrays.asList("Sb", "cd");
      // When
      HeightMap heightMap = new HeightMap(rows);
      // Then
      assertThat("Height should be 2", heightMap.getHeight(), Matchers.is(2));
      assertThat("Length should be 2", heightMap.getLength(), Matchers.is(2));
      assertThat("S should be at 0,0", heightMap.getItem(0, 0).getId(),
          is("S"));
      assertThat("b should be at 1,0", heightMap.getItem(1, 0).getId(),
          is("b"));
      assertThat("c should be at 0,1", heightMap.getItem(0, 1).getId(),
          is("c"));
      assertThat("d should be at 1,1", heightMap.getItem(1, 1).getId(),
          is("d"));
    }

    @Test
    @DisplayName("Should set place links properly")
    void shouldSetPlaceLinksProperly() {
      // Given
      List<String> rows = Arrays.asList("Sb", "xc", "xE");
      // When
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(1,1), heightMap.getItem(1, 2));
      // Then
      assertThat("Height should be 2", heightMap.getHeight(), Matchers.is(3));
      assertThat("Length should be 2", heightMap.getLength(), Matchers.is(2));
      assertThat("S should have connected node b",
          heightMap.getConnections(heightMap.getItem(0, 0)),
          hasItem(hasProperty("id", is(equalTo("b")))));
      assertThat("S should have one connections",
          heightMap.getConnections(heightMap.getItem(0, 0)).size(),
          is(1));
      assertThat("b should have connected node c",
          heightMap.getConnections(heightMap.getItem(1, 0)),
          hasItems(hasProperty("id", is(equalTo("c"))),
              hasProperty("id", is(equalTo("S")))));
      assertThat("b should have two connections",
          heightMap.getConnections(heightMap.getItem(1, 0)).size(),
          is(2));
      assertThat("c should have connected node E",
          heightMap.getConnections(heightMap.getItem(1, 1)),
          hasItem(hasProperty("id", is(equalTo("E")))));
      assertThat("c should have two connections",
          heightMap.getConnections(heightMap.getItem(1, 1)).size(),
          is(2));
      assertThat("E should have two connections",
          heightMap.getConnections(heightMap.getItem(1, 2)).size(),
          is(2));
    }

    @Test
    @DisplayName("Should set start and end point")
    void shouldSetStartAndEndPoint() {
      // Given
      List<String> rows = Arrays.asList("Sb", "Ec");
      // When
      HeightMap heightMap = new HeightMap(rows);
      // Then
      assertThat("Height should be 2", heightMap.getHeight(), Matchers.is(2));
      assertThat("Length should be 2", heightMap.getLength(), Matchers.is(2));
      assertThat("Start point should be S",
          heightMap.getStartPoint(),
          hasItem(hasProperty("id", is(equalTo("S")))));
      assertThat("End point should be E",
          heightMap.getEndPoint(),
          hasProperty("id", is(equalTo("E"))));
    }
  }

  @Nested
  @DisplayName("Path finding tests")
  class PathFindingTests{
    @Test
    @DisplayName("Should find simple shortest path")
    void shouldSetPlaceLinksProperly() {
      // Given
      List<String> rows = Arrays.asList("Sb", "xc", "zE");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(1,1), heightMap.getItem(1, 2));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("b"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }

    @Test
    @DisplayName("Should find shortest path with 3x3 input")
    void shouldFindShortestPathWith3x3Input() {
      // Given
      List<String> rows = toList("""
          Sbc
          fed
          Exx""");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(0,1), heightMap.getItem(0, 2));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("b"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("d"))),
          hasProperty("id", is(equalTo("e"))),
          hasProperty("id", is(equalTo("f"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }

    @Test
    @DisplayName("Should find shortest path with 3x3 input and multiple paths")
    void shouldFindShortestPathWith3x3InputAndMultiplePaths() {
      // Given
      List<String> rows = toList("""
          Sbc
          bxd
          Efe""");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(1,2), heightMap.getItem(0, 2));
      heightMap.addConnection(heightMap.getItem(0,1), heightMap.getItem(0, 2));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("b"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }

    @Test
    @DisplayName("Should find shortest path when start and end in middle")
    void shouldFindShortestPathWhenStartAndEndInMiddle() {
      // Given
      List<String> rows = toList("""
          xxxx
          xSEx
          xxxx""");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(1,1), heightMap.getItem(2, 1));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }

    @Test
    @DisplayName("Should be able to go down")
    void shouldBeAbleToGoDown() {
      // Given
      List<String> rows = toList("SabcdecaE");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(7,0), heightMap.getItem(8, 0));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("b"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("d"))),
          hasProperty("id", is(equalTo("e"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }

    @Test
    @DisplayName("Should find shortest path when duplicate letters")
    void shouldFindShortestPathWhenDuplicateLetters() {
      // Given
      List<String> rows = toList("""
          Saaaaaaaaa
          Eccccccccb""");
      HeightMap heightMap = new HeightMap(rows);
      heightMap.addConnection(heightMap.getItem(1,1), heightMap.getItem(0, 1));
      // When
      List<GraphNode> path = heightMap.getShortestPath();
      // Then
      assertThat("Path should be in correct order", path, contains(
          hasProperty("id", is(equalTo("S"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("a"))),
          hasProperty("id", is(equalTo("b"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("c"))),
          hasProperty("id", is(equalTo("E")))
      ));
    }
  }

  private List<String> toList(String raw) {
    return Arrays.stream(raw.split("\n")).toList();
  }
}