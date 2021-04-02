package ryleh.core;

import ryleh.common.P2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.GameObjectImpl;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.Component;
import ryleh.view.GraphicComponent;

public class EntityBuilder {
    private GameObject object;
    private GraphicComponent graphic;

    public EntityBuilder() {
        object = new GameObjectImpl();
        graphic = null; //TODO
    }

    public EntityBuilder type(final Type type) {
        object.setType(type);
        return this;
    }

    public EntityBuilder position(final P2d position) {
        object.setPosition(position);
        return this;
    }
    public EntityBuilder position(final int x, final int y) {
        object.setPosition(new P2d(x, y));
        return this;
    }

    public EntityBuilder with(final Component component) {
        object.addComponent(component);
        return this;
    }

    public EntityBuilder view(final GraphicComponent view) {
        graphic = view;
        return this;
    }

    public Entity build() {
        return new Entity(graphic, object);
    }
}