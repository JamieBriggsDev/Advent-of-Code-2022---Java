package dev.jbriggs.aoc.handheld;

import dev.jbriggs.aoc.handheld.reader.TerminalReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Device test")
class DeviceTest {

  Device device;

  @BeforeEach
  public void beforeEach() {
    device = Device.builder().addReaderModule(new TerminalReader())
        .addMemoryModule(new CRTScreen()).build();
  }



}
