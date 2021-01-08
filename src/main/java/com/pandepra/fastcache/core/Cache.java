package com.pandepra.fastcache.core;

import com.pandepra.fastcache.ds.RBTreeMap;
import com.pandepra.fastcache.util.WALogger;

import java.io.IOException;

public class Cache {

  private RBTreeMap<String, String> treeMap;
  private WALogger waLogger;

  private Cache() {
    this.treeMap = new RBTreeMap<>();
    this.waLogger = new WALogger();
  }

  public static Cache getInstance() {
    return Creator.INSTANCE;
  }

  public String put(String key, String val) {
    try {
      waLogger.append(key, val);
    } catch (IOException e) {
      // log
      throw new RuntimeException("");
    }
    treeMap.put(key, val);
    return val;
  }

  public String get(String key) {
    return treeMap.get(key);
  }

  private static class Creator {
    private static final Cache INSTANCE = new Cache();
  }
}
