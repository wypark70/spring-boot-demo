package com.example.bootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class BootDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(BootDemoApplication.class, args);
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      log.info("/**");
      log.info(" * host: {}", localHost.getHostName());
      log.info(" * address: {}", localHost.getAddress());
      log.info(" * hostAddress: {}", localHost.getHostAddress());
      log.info(" */");
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
