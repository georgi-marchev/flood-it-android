package com.gmarchev.floodit.core.engine;

public interface GameEngine {

	void addObserver(GameObserver observer);

	void removeObserver(GameObserver observer);

	void start();

	boolean flood(int color) throws InvalidInputException;

	boolean isCompleted();

	void undo();
}
