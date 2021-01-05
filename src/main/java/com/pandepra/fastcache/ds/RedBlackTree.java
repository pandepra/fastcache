package com.pandepra.fastcache.ds;

import java.util.Comparator;
import java.util.Objects;

public class RedBlackTree<K extends Comparable<K>, V> {

  private Comparator<K> keyComparator;
  private Node<K, V> root;

  public RedBlackTree() {}

  public RedBlackTree(Comparator<K> keyComparator) {
    this.keyComparator = keyComparator;
  }

  public boolean put(K key, V value) {
    return false;
  }

  public V get(K key) {
    return null;
  }

  private void leftRotate(Node<K, V> x) {}

  static class Node<K, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> parent;
    Color color;

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public String toString() {
      return key + "=" + value;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Node) {
        Node<?, ?> o1 = (Node<?, ?>) o;
        // a tree cannot have duplicate keys, so we compare just on the key
        return Objects.equals(key, o1.key);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hash(key);
    }
  }

  enum Color {
    RED,
    BLACK
  }
}
