package ryleh.controller.events;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.core.GameState;
import ryleh.core.factories.BasicFactory;
import ryleh.model.GameObject;

public class BulletSpawnEvent implements Event {
	
	private P2d position;
	private V2d velocity;
	private GameObject target;
	public BulletSpawnEvent(final GameObject spawner, final P2d position, final V2d velocity) {
		this.target = spawner;
		this.position = position;
		this.velocity = velocity;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(final GameState state) {
		state.addEntity(
                BasicFactory.getInstance().createBullet(state, 
                this.position, this.velocity, this.target.getType()));
	}
}
