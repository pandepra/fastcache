package com.pandepra.fastcache;

import com.pandepra.fastcache.util.WALogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FastCacheApplication {
  public static void main(String[] args) throws IOException {
    //    SpringApplication.run(FastCacheApplication.class, args);
    WALogger<String, String> logger = new WALogger<>();
    logger.append("hello", "world");
  }
}
