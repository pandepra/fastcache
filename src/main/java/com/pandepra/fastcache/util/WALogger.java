package com.pandepra.fastcache.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class WALogger<K, V> {

  private FileOutputStream fos;
  private BufferedOutputStream bos;
  private PrintWriter pw;
  private ObjectMapper objectMapper = new ObjectMapper();

  public WALogger() throws IOException {
    fos = new FileOutputStream("/Users/prashantpandey/Desktop/vimscripts/tempfiles/log", true);
    bos = new BufferedOutputStream(fos);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  try {
                    fos.close();
                    bos.close();
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                }));
  }

  public void append(K key, V val) throws IOException {
    WALog log = new WALog(key, val);
    byte[] bytes = objectMapper.writeValueAsBytes(log);
    bos.write(bytes);
    bos.flush();
  }

  private class WALog {

    private K key;
    private V val;

    public WALog(K key, V val) {
      this.key = key;
      this.val = val;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getVal() {
      return val;
    }

    public void setVal(V val) {
      this.val = val;
    }
  }
}
