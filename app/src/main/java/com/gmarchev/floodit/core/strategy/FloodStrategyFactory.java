package com.gmarchev.floodit.core.strategy;

public class FloodStrategyFactory {

	public static FloodStrategy create(int rowCount, int colCount) {

		if (rowCount < 20) {

			return new DfsFloodStrategy(rowCount, colCount);

		} else {

			return new BfsFloodStrategy();
		}
	}
}
