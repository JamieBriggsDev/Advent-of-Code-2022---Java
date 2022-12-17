package dev.jbriggs.aoc.handheld.storage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import dev.jbriggs.aoc.handheld.DeviceException;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Terminal storage handler test")
class TerminalStorageTest {

  @Nested
  @DisplayName("ChangeDirection()")
  class ChangeDirectoryTests {

    @Test
    @DisplayName("Should create current directory")
    public void shouldCreateCurrentDirectory()
        throws StorageException {
      // Given
      TerminalStorage handler = TerminalStorage.builder().build();
      // When
      handler.changeDirectory("/new-directory");
      // Then
      assertThat("Current directory should be /new-directory",
          handler.getCurrentTerminalDirectory().getName(), is("/new-directory"));
    }

    @Test
    @DisplayName("Should change directory")
    public void shouldChangeDirectory() throws StorageException {
      // Given
      TerminalDirectory child = TerminalDirectory.builder().name("/child").build();
      HashSet<TerminalItem> contents = new HashSet<>();
      contents.add(child);
      TerminalDirectory root = TerminalDirectory.builder().name("/").contents(contents).build();
      TerminalStorage handler = TerminalStorage.builder()
          .currentTerminalDirectory(root).build();
      // When
      handler.changeDirectory("/child");
      // Then
      assertThat("Current directory should be /child",
          handler.getCurrentTerminalDirectory().getName(), is("/child"));
    }

    @Test
    @DisplayName("Should go out one directory")
    public void shouldGoOutOneDirectory() throws StorageException {
      // Given
      TerminalDirectory root = TerminalDirectory.builder().name("/parent").build();
      TerminalDirectory child = TerminalDirectory.builder().name("/child").parentDirectory(root).build();
      TerminalStorage handler = TerminalStorage.builder()
          .currentTerminalDirectory(child).build();
      // When
      handler.changeDirectory("..");
      // Then
      assertThat("Current directory should be /parent",
          handler.getCurrentTerminalDirectory().getName(), is("/parent"));
    }
  }
}