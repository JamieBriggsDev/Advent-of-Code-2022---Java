package dev.jbriggs.aoc.core;

public interface Collidable<T> {

  boolean collidesWith(T other);
}
