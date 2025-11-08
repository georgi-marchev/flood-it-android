package com.gmarchev.floodit.core.board;

public class BoardImpl implements Board {

	public static final int MINIMUM_BOARD_SIZE_ROW = 2;

	public static final int MINIMUM_BOARD_SIZE_COL = 2;

	private int[][] grid;

	private boolean[][] flooded;

	private int floodColor;

	private int floodedCount;

	public BoardImpl(int[][] grid) {

		validateBoard(grid);

		this.grid = grid;
		this.flooded = new boolean[grid.length][grid[0].length];
		this.floodColor = grid[0][0];

		markCellFlooded(0, 0);
	}

	private static void validateBoard(int[][] board) {

		if (board == null || board.length < MINIMUM_BOARD_SIZE_ROW) {

			throw new IllegalArgumentException("Board cannot be null or empty");
		}

		int firstRowColCount = board[0].length;

		for (int row = 1; row < board.length; row++) {

			if (board[row].length != firstRowColCount) {

				throw new IllegalArgumentException("Board must be a rectangle");
			}
		}

		if (firstRowColCount < MINIMUM_BOARD_SIZE_COL) {

			throw new IllegalArgumentException("Board size must be at least 2x2");
		}
	}

	@Override
	public int[][] getGrid() {

		return grid;
	}

	@Override
	public void setFloodColor(int floodColor) {

		this.floodColor = floodColor;
	}

	@Override
	public int getFloodColor() {

		return floodColor;
	}

	@Override
	public int getCellColor(int row, int col) {

		validateCell(row, col);

		return grid[row][col];
	}

	private void validateCell(int row, int col) {

		if (!isInBounds(row, col)) {

			throw new IllegalArgumentException(String.format("Cell out of bounds [%d][%d]", row, col));
		}
	}

	@Override
	public void setCellColor(int row, int col, int color) {

		validateCell(row, col);

		grid[row][col] = color;
	}

	@Override
	public boolean isInBounds(int row, int col) {

		return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
	}

	@Override
	public void markCellFlooded(int row, int col) {

		validateCell(row, col);

		if (!flooded[row][col]) {

			flooded[row][col] = true;
			floodedCount++;
		}
	}

	@Override
	public boolean isCellFlooded(int row, int col) {

		validateCell(row, col);

		return flooded[row][col];
	}

	@Override
	public boolean isCompleted() {

		return floodedCount == grid.length * grid[0].length;
	}

	@Override
	public BoardMemento createMemento() {

		int[][] gridCopy = new int[grid.length][];
		for (int i = 0; i < grid.length; i++) {

			gridCopy[i] = grid[i].clone();
		}

		boolean[][] floodedCopy = new boolean[flooded.length][];
		for (int i = 0; i < flooded.length; i++) {

			floodedCopy[i] = flooded[i].clone();
		}

		return new BoardMemento(gridCopy, floodedCopy, floodedCount);
	}

	@Override
	public void restoreFromMemento(BoardMemento memento) {

		this.grid = memento.grid();
		this.flooded = memento.flooded();
		this.floodedCount = memento.floodedCount();
		this.floodColor = grid[0][0];
	}
}
