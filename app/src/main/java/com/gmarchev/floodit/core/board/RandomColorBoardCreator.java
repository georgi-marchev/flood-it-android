package com.gmarchev.floodit.core.board;

import java.util.Random;

public class RandomColorBoardCreator {

	public static Board create(int rowCount, int colCount, int... colorIds) {

		Random random = new Random();

		int[][] colorGrid = new int[rowCount][colCount];

		for (int row = 0; row < rowCount; row++) {

			for (int col = 0; col < colCount; col++) {

				colorGrid[row][col] = colorIds[random.nextInt(colorIds.length)];
			}
		}

		return new BoardImpl(colorGrid);
	}
}
