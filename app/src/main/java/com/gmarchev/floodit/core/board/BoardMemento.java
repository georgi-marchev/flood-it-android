package com.gmarchev.floodit.core.board;

/**
 * Implementation of the Memento pattern, used to save the state of the game board on each move, which allows to undo
 * moves.
 */
public record BoardMemento (int[][] grid, boolean[][] flooded, int floodedCount) {}
