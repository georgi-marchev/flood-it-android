package com.gmarchev.floodit.core.strategy;

import java.util.ArrayDeque;
import java.util.Queue;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.utils.BoardMovementUtil;

/**
 * Uses a simple BFS algorithm for flooding the board, with one adjustment - it uses the boards ability to track flooded
 * cells instead of keeping a "visited" collection.
 */
public class BfsFloodStrategy implements FloodStrategy {

	@Override
	public void flood(Board board, int floodColor) {

		board.setFloodColor(floodColor);
		board.setCellColor(0, 0, floodColor);

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{0, 0});

		while (!queue.isEmpty()) {

			int[] cell = queue.poll();
			int row = cell[0], col = cell[1];

			for (int[] move : BoardMovementUtil.getPossibleMoves(board, row, col)) {

				int newRow = move[0], newCol = move[1];

				if (board.isCellFlooded(newRow, newCol) && board.getCellColor(newRow, newCol) != floodColor) {

					// If a flooded cell with different from current flood color - we haven't visited it yet so mark
					// and put in queue
					board.setCellColor(newRow, newCol, floodColor);
					queue.add(new int[]{newRow, newCol});

				} else if (board.getCellColor(newRow, newCol) == floodColor && !board.isCellFlooded(newRow, newCol)) {

					// We have a matching color on a non-captured cell - we need to capture it.
					board.markCellFlooded(newRow, newCol);
					queue.add(new int[]{newRow, newCol});
				}
			}
		}
	}
}
