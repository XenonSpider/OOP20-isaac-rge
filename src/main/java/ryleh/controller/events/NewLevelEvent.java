package ryleh.controller.events;

import ryleh.core.GameState;

public class NewLevelEvent implements Event {
	
	/**
	 * {@inheritDoc}
	 * Generate a new random level
	 */
	@Override
	public void handle(final GameState state) {
        state.generateNewLevel();
	}
	
}