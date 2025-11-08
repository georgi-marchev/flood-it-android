package com.gmarchev.floodit.core.engine;

public record GameState(int moveCount, boolean isComplete, int[][] board) {}
