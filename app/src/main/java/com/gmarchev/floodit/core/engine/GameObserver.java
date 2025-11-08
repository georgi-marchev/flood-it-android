package com.gmarchev.floodit.core.engine;

/**
 * An Observer pattern implementation used by the game engine to notify observers about game state changes.
 */
public interface GameObserver {

	void onGameStateUpdate(GameState gameState);
}
