package dev.jbriggs.aoc.handheld.storage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.jbriggs.aoc.handheld.storage.core.TerminalDirectory;
import dev.jbriggs.aoc.handheld.storage.core.TerminalFile;
import dev.jbriggs.aoc.handheld.storage.core.TerminalReaderState;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TerminalReaderTest {

  TerminalReader reader;

  @BeforeEach
  public void beforeEach() {
    reader = new TerminalReader();
  }

  @Nested
  @DisplayName("Terminal reader state test")
  class TerminalReaderStateTest {

    @Test
    @DisplayName("Should start in reading state")
    void shouldStartInWaitingState() {
      // Then
      assertThat("Reader should be WRITING", reader.getCurrentState(),
          is(TerminalReaderState.WRITING));
    }

    @Test
    @DisplayName("Should be in writing state when change directory command sent")
    void shouldBeInWritingStateWhenChangeDirectoryCommandSent()
        throws TerminalException {
      // Given
      // When
      reader.read("$ cd /");
      // Then
      assertThat("Reader should be WRITING", reader.getCurrentState(),
          is(TerminalReaderState.WRITING));
    }

    @Test
    @DisplayName("Should be in listening state when list command sent")
    void shouldBeInListeningStateWhenListCommandSent()
        throws TerminalException {
      reader.read("$ cd /");
      // When
      reader.read("$ ls");
      // Then
      assertThat("Reader should be listening", reader.getCurrentState(),
          is(TerminalReaderState.LISTENING));
    }

  }

  @Nested
  @DisplayName("Change directory tests")
  class ChangeDirectoriesTests {

    @Test
    @DisplayName("Should go in correct directory")
    void shouldGoInCorrectDirectory() throws TerminalException {
      // Given
      String input = "$ cd /";
      // When
      reader.read(input);
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("/"));
    }

    @Test
    @DisplayName("Should go in to second directory")
    void shouldGoIntoSecondDirectory() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "$ cd a");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/a/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("a"));
    }

    @Test
    @DisplayName("Should go in to third directory")
    void shouldGoIntoThirdDirectory() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "$ cd a",
          "$ ls", "dir b", "$ cd b");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/a/b/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("b"));
    }

    @Test
    @DisplayName("Should go back to second directory")
    void shouldGoBackToSecondDirectory() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "$ cd a",
          "$ ls", "dir b", "$ cd b", "$ cd ..");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/a/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("a"));
    }

    @Test
    @DisplayName("Should throw exception when trying to go to directory which isnt found")
    void ShouldThrowExceptionWhenTryingToGoToDirectoryWhichIsntFound()
        throws TerminalException {
      // Given
      reader.read("$ cd /");
      String input = "$ cd a";
      // When
      TerminalException thrown = assertThrows(TerminalException.class, () -> reader.read(input));
      // Then
      assertThat("Exception should be thrown with message",
          thrown.getMessage(), is("Directory not found yet - a"));
    }

  }

  @Nested
  @DisplayName("List tests")
  class ListTests {

    @Test
    @DisplayName("Should store listed folders")
    void shouldStoreListedFolders() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/"));
      assertThat("Current folder should have one child",
          reader.getCurrentTerminalDirectory().getContents().size(), is(1));
      assertThat("Current folder should have directory in contents",
          reader.getCurrentTerminalDirectory().getContents(),
          hasItem(new TerminalDirectory(null, "a")));
    }

    @Test
    @DisplayName("Should store listed files")
    void shouldStoreListedFiles() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "123 hello.txt");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory",
          reader.getCurrentDirectoryPath(), is("/"));
      assertThat("Current folder should have one child",
          reader.getCurrentTerminalDirectory().getContents().size(), is(1));
      assertThat("Current folder should have directory in contents",
          reader.getCurrentTerminalDirectory().getContents(),
          hasItem(new TerminalFile(null, "hello.txt", 123L)));
    }
  }

  @Nested
  @DisplayName("Get all directories tests")
  class GetAllDirectoriesTests{

    @Test
    @DisplayName("Should return zero directories when only root")
    void shouldReturnOneDirectoryWhenOnlyRoot() throws TerminalException {
      // Given
      String input = "$ cd /";
      // When
      reader.read(input);
      // Then
      assertThat("Should only no directories", reader.findAllDirectories().isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should return two directories")
    void shouldReturnTwoDirectories(){
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "dir b", "123 a.txt", "$ cd a");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (TerminalException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Should contain two directories", reader.findAllDirectories().size(), is(2));
    }
  }
}