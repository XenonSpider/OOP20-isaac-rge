package ryleh.model.items;

import ryleh.core.GameState;
import ryleh.model.components.HealthIntComponent;

public class MaxHealthItem implements Item {

    /**
     * {@inheritDoc}
     * Increases the max health of the player
     */
    @Override
    public void apply(final GameState state) {
        ((HealthIntComponent) state.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get()).increaseMaxHp(1);
        System.out.println("max heal");
    }
}