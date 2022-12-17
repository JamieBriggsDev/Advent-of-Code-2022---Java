package dev.jbriggs.aoc.days.d12;

import dev.jbriggs.aoc.pathfinding.Scorer;

public class HeuristicScorer implements Scorer<MountainNode> {

  @Override
  public double computeCost(MountainNode from, MountainNode to) {
    return from.heuristicDistance(to);
  }
}
