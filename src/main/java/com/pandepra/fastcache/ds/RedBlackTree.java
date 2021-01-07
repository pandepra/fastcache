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

  public void put(K key, V value) {
    if (null == key || null == value) {
      throw new IllegalArgumentException("Neither key nor value can be null");
    }
    Node<K, V> z = new Node<>(key, value);
    Node<K, V> y = null, x = root;
    while (null != x) {
      y = x;
      if (compareKeys(z.getKey(), x.getKey()) < 0) {
        x = x.getLeft();
      } else {
        x = x.getRight();
      }
    }
    z.setParent(y);
    if (null == y) {
      root = z;
    } else if (compareKeys(z.getKey(), y.getKey()) < 0) {
      y.setLeft(z);
    } else {
      y.setRight(z);
    }
    z.setLeft(null);
    z.setRight(null);
    z.setColor(Color.RED);
    insertFixup(z);
  }

  private void insertFixup(Node<K, V> z) {
    while (z.getParent().getColor().equals(Color.RED)) {
      if(z.getParent().equals(z.getParent().getParent().getLeft())) {
        Node<K, V> y = z.getParent().getParent().getRight();
        if(y.getColor().equals(Color.RED)) {
          z.getParent().setColor(Color.BLACK);
          y.setColor(Color.BLACK);
          z.getParent().getParent().setColor(Color.RED);
          z = y.getParent().getParent();
        } else if(z.equals(z.getParent().getRight())) {
          z = z.getParent();
          leftRotate(z);

        }
      } else {
        z = z.getParent();
        leftRotate(z);
      }
    }
  }

  private int compareKeys(K key1, K key2) {
    if (null == keyComparator) {
      return key1.compareTo(key2);
    } else {
      return keyComparator.compare(key1, key2);
    }
  }

  public V get(K key) {
    return null;
  }

  private void leftRotate(Node<K, V> x) {
    Node<K, V> y = x.getRight();
    x.setRight(y.getLeft());
    if (null != y.getLeft()) {
      y.getLeft().setParent(x);
    }
    y.setParent(x.getParent());
    if (null == x.getParent()) {
      root = y;
    } else if (x.equals(x.getParent().getLeft())) {
      x.getParent().setLeft(y);
    } else {
      x.getParent().setRight(y);
    }
    y.setLeft(x);
    x.setParent(y);
  }

  private void rightRotate(Node<K, V> x) {
    Node<K, V> y = x.getLeft();
    x.setLeft(y.getRight());
    if (null != y.getRight()) {
      y.getRight().setParent(x);
    }
    y.setParent(x.getParent());
    if (null == x.getParent()) {
      root = y;
    } else if (x.equals(x.getParent().getLeft())) {
      x.getParent().setLeft(y);
    } else {
      x.getParent().setRight(y);
    }
    y.setRight(x);
    x.setParent(y);
  }

  static class Node<K, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> parent;
    Color color;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    public Node<K, V> getRight() {
      return right;
    }

    public void setRight(Node<K, V> right) {
      this.right = right;
    }

    public Node<K, V> getLeft() {
      return left;
    }

    public void setLeft(Node<K, V> left) {
      this.left = left;
    }

    public void setParent(Node<K, V> parent) {
      this.parent = parent;
    }

    public Node<K, V> getParent() {
      return parent;
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
