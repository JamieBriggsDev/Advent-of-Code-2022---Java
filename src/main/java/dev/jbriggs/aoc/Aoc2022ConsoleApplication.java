package dev.jbriggs.aoc;

import dev.jbriggs.aoc.util.PuzzleInputParser;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
public class Aoc2022ConsoleApplication implements CommandLineRunner {

  private final PuzzleInputParser puzzleInputParser;
  private final List<Day> days;

  public Aoc2022ConsoleApplication(PuzzleInputParser puzzleInputParser,
      List<Day> days) {
    this.puzzleInputParser = puzzleInputParser;
    this.days = days;
  }

  public static void main(String[] args) {
    log.info("STARTING THE APPLICATION");
    SpringApplication.run(Aoc2022ConsoleApplication.class, args);
    log.info("APPLICATION FINISHED");
  }

  @Override
  public void run(String... args) {
    log.info("EXECUTING : command line runner");
    log.info("\n\n\n\n");
    log.info("What day do you want to run: ");
    Scanner scanner = new Scanner(System.in);
    Integer lineNumber = Integer.valueOf(scanner.nextLine());

    Optional<Day> dayOptional = days.stream()
        .filter(x -> x.getDayNumber().equals(lineNumber)).findFirst();
    if (dayOptional.isPresent()) {
      Day day = dayOptional.get();

      log.info("Working out Part 1...");
      LocalTime partOneStartTime = LocalTime.now();
      String partOneAnswer = day.partOneAnswer();
      LocalTime partOneEndTime = LocalTime.now();
      log.info("Part 1 answer: {}\t\t{}ms",
          partOneAnswer,
          Duration.between(partOneStartTime, partOneEndTime).toMillis());

      log.info("Working out Part 2...");
      LocalTime partTwoStartTime = LocalTime.now();
      String partTwoAnswer = day.partTwoAnswer();
      LocalTime partTwoEndTime = LocalTime.now();
      log.info("Part 2 answer: {}\t\t{}ms", partTwoAnswer,
          Duration.between(partTwoStartTime, partTwoEndTime).toMillis());

    } else {
      log.error("Could not find day {}", lineNumber);
    }

  }
}
