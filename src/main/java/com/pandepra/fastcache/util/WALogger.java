package com.pandepra.fastcache.util;

import com.pandepra.proto.Wal.WalLog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WALogger {

  private FileOutputStream fos;
  private static final String PATH = "/var/log/wal.log";

  public WALogger() {
    try {
      fos = new FileOutputStream(WALogger.PATH, true);
    } catch (FileNotFoundException e) {
      // should create a new file or exit, tbd
    }
  }

  public void append(String key, String val) throws IOException {
    WalLog log = WalLog.newBuilder().setKey(key).setVal(val).build();
    log.writeTo(fos);
  }
}
