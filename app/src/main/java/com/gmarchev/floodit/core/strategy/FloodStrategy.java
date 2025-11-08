package com.gmarchev.floodit.core.strategy;

import com.gmarchev.floodit.core.board.Board;

/**
 * An implementation of the Strategy pattern, that can be used to define different strategies for flooding the board.
 */
public interface FloodStrategy {

	void flood(Board board, int color);
}
