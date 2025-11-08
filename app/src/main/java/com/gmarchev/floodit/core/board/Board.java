package com.gmarchev.floodit.core.board;

public interface Board {

	int[][] getGrid();

	void setFloodColor(int color);

	int getFloodColor();

	int getCellColor(int row, int col);

	void setCellColor(int row, int col, int color);

	boolean isInBounds(int row, int col);

	void markCellFlooded(int row, int col);

	boolean isCellFlooded(int row, int col);

	boolean isCompleted();

	BoardMemento createMemento();

	void restoreFromMemento(BoardMemento memento);
}
