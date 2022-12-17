package dev.jbriggs.aoc.handheld.storage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.jbriggs.aoc.handheld.HandheldException;
import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import dev.jbriggs.aoc.handheld.core.register.MemoryRegisterHolder;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TerminalReaderTest {

  TerminalReader reader;

  @BeforeEach
  public void beforeEach() {
    reader = new TerminalReader();
    reader.setMemoryRegisterHolder(new MemoryRegisterHolder());
  }

  @Nested
  @DisplayName("Terminal state tests")
  class TerminalStateTests {

    @Test
    @DisplayName("Should start in reading state")
    void shouldStartInWaitingState() {
      // Then
      assertThat("Reader should be WRITING", reader.getCurrentState(),
          is(TerminalReaderState.WRITING));
    }


    @Test
    @DisplayName("Should be in writing state when change directory command sent")
    void shouldBeInWritingStateWhenChangeDirectoryCommandSent() throws HandheldException {
      // Given
      // When
      reader.read("$ cd /");
      // Then
      assertThat("Reader should be WRITING", reader.getCurrentState(),
          is(TerminalReaderState.WRITING));
    }

    @Test
    @DisplayName("Should be in listening state when list command sent")
    void shouldBeInListeningStateWhenListCommandSent() throws HandheldException {
      reader.read("$ cd /");
      // When
      reader.read("$ ls");
      // Then
      assertThat("Reader should be listening", reader.getCurrentState(),
          is(TerminalReaderState.LISTENING));
    }
  }

  @Nested
  @DisplayName("Change directory 'cd' tests")
  class ChangeDirectoriesTests {

    @Test
    @DisplayName("Should go in correct directory")
    void shouldGoInCorrectDirectory() throws HandheldException {
      // Given
      String input = "$ cd /";
      // When
      reader.read(input);
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/"));
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
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/a/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("a"));
    }

    @Test
    @DisplayName("Should go in to third directory")
    void shouldGoIntoThirdDirectory() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "$ cd a", "$ ls", "dir b",
          "$ cd b");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/a/b/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("b"));
    }

    @Test
    @DisplayName("Should go back to second directory")
    void shouldGoBackToSecondDirectory() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "$ cd a", "$ ls", "dir b",
          "$ cd b", "$ cd ..");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/a/"));
      assertThat("Reader should be in correct current folder name",
          reader.getCurrentTerminalDirectory().getName(), is("a"));
    }

    @Test
    @DisplayName("Should throw exception when trying to go to directory which isnt found")
    void ShouldThrowExceptionWhenTryingToGoToDirectoryWhichIsntFound() throws HandheldException {
      // Given
      reader.read("$ cd /");
      String input = "$ cd a";
      // When
      HandheldException thrown = assertThrows(HandheldException.class, () -> reader.read(input));
      // Then
      assertThat("Exception should be thrown with message", thrown.getMessage(),
          is("Unable to change directory"));
    }

  }

  @Nested
  @DisplayName("List 'ls' tests")
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
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/"));
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
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Reader should be in correct directory", reader.getCurrentDirectoryPath(),
          is("/"));
      assertThat("Current folder should have one child",
          reader.getCurrentTerminalDirectory().getContents().size(), is(1));
      assertThat("Current folder should have directory in contents",
          reader.getCurrentTerminalDirectory().getContents(),
          hasItem(new TerminalFile(null, "hello.txt", 123L)));
    }
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
  @DisplayName("Get all directories tests")
  class GetAllDirectoriesTests {

    @Test
    @DisplayName("Should return zero directories when only root")
    void shouldReturnOneDirectoryWhenOnlyRoot() throws HandheldException {
      // Given
      String input = "$ cd /";
      // When
      reader.read(input);
      // Then
      assertThat("Should only one directory", reader.findAllDirectories().size(), is(1));
    }

    @Test
    @DisplayName("Should return three directories")
    void shouldReturnTwoDirectories() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "dir b", "123 a.txt", "$ cd a");
      // When
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // Then
      assertThat("Should contain 3 directories", reader.findAllDirectories().size(), is(3));
    }
  }

  @Nested
  @DisplayName("Smallest directory above size tests")
  class SmallestDirectoryAboveSizeTests {

    @Test
    @DisplayName("Should return inner directory")
    void shouldReturnInnerDirectory() throws HandheldException {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "dir b", "123 a.txt", "$ cd a",
          "$ ls", "50 b.txt");
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // When
      TerminalDirectory result = reader.findSmallestDirectoryAboveSpecificSize(49L);
      // Then
      assertThat("Result should be directory a", result.getName(), is("a"));
    }

    @Test
    @DisplayName("Should return root directory")
    void shouldReturnRootDirectory() throws HandheldException {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "dir b", "123 a.txt", "$ cd a",
          "$ ls", "50 b.txt");
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // When
      TerminalDirectory result = reader.findSmallestDirectoryAboveSpecificSize(130L);
      // Then
      assertThat("Result should be directory /", result.getName(), is("/"));
    }

    @Test
    @DisplayName("Should throw exception when nothing found")
    void shouldThrowExceptionWhenNothingFound() {
      // Given
      List<String> input = Arrays.asList("$ cd /", "$ ls", "dir a", "123 a.txt", "$ cd a", "$ ls",
          "50 b.txt");
      input.forEach(x -> {
        try {
          reader.read(x);
        } catch (HandheldException e) {
          throw new RuntimeException(e);
        }
      });
      // When
      HandheldException thrown = assertThrows(HandheldException.class,
          () -> reader.findSmallestDirectoryAboveSpecificSize(10000L));
      // Then
      assertThat("Exception should be thrown with message", thrown.getMessage(),
          is("No directories found above size 10000"));
    }
  }
}