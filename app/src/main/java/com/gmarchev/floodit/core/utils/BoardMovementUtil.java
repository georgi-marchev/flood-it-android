package com.gmarchev.floodit.core.utils;

import java.util.Arrays;

import com.gmarchev.floodit.core.board.Board;

public class BoardMovementUtil {

	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static int[][] getPossibleMoves(Board board, int row, int col) {

		return Arrays
				.stream(DIRECTIONS)
				.map(direction -> new int[] {row + direction[0], col + direction[1]})
				.filter(newCell -> board.isInBounds(newCell[0], newCell[1]))
				.toArray(int[][]::new);
	}
}
