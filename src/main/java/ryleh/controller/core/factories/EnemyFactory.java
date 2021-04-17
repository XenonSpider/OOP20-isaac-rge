package ryleh.controller.core.factories;

import ryleh.common.Circle2d;
import ryleh.common.GameMath;
import ryleh.common.Point2d;
import ryleh.common.Vector2d;
import ryleh.controller.EntityImpl;
import ryleh.controller.core.GameEngine;
import ryleh.controller.core.GameState;
import ryleh.model.Type;
import ryleh.model.components.CollisionComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;
import ryleh.view.enemies.EnemyDrunkSpinnerGraphicComponent;
import ryleh.view.enemies.EnemyLurkerGraphicComponent;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.enemies.EnemySpinnerGraphicComponent;

public class EnemyFactory {
     private static EnemyFactory instance;

     private EnemyFactory() { }

     public static EnemyFactory getInstance() {
            if (instance == null) {
                    instance = new EnemyFactory();
            }
            return instance;
    }
     public EntityImpl createEnemyShooter(final GameState state, final Point2d position) {
         EntityImpl e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(position)
                 .with(new ShooterComponent(state.getWorld(), state.getPlayer()))
                 .with(new HealthIntComponent(state.getWorld(),1))
                 .with(new CollisionComponent(state.getWorld(), Type.ENEMY_SHOOTER))
                 .view(new EnemyShooterGraphicComponent(GameMath.toPoint2D(position)))
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(1)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public EntityImpl createEnemySpinner(final GameState state, final Point2d position) {
         EntityImpl e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(position)
                 .with(new SpinnerComponent(state.getWorld()))
                 .with(new HealthIntComponent(state.getWorld(),1))
                 .with(new CollisionComponent(state.getWorld(), Type.ENEMY_SPINNER))
                 .view(new EnemySpinnerGraphicComponent(GameMath.toPoint2D(position)))
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(10)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public EntityImpl createEnemyDrunk(final GameState state, final Point2d position) {
         EntityImpl e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNK)
                 .position(position)
                 .with(new DrunkComponent(state.getWorld()))
                 .with(new HealthIntComponent(state.getWorld(),1))
                 .with(new CollisionComponent(state.getWorld(), Type.ENEMY_DRUNK))
                 .view(new EnemyDrunkGraphicComponent(GameMath.toPoint2D(position)))
                 .bbox(new CircleHitBox(new Circle2d(55)))
                 .zIndex(0)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public EntityImpl createEnemyLurker(final GameState state, final Point2d position) {
         EntityImpl e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(position)
                 .with(new LurkerComponent(state.getWorld(), state.getPlayer()))
                 .with(new HealthIntComponent(state.getWorld(),1))
                 .with(new CollisionComponent(state.getWorld(), Type.ENEMY_LURKER))
                 .view(new EnemyLurkerGraphicComponent((PlayerGraphicComponent) state.getPlayer().getView(), GameMath.toPoint2D(position)))
                 .bbox(new CircleHitBox(new Circle2d(55)))
                 .zIndex(9)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public EntityImpl createEnemyDrunkSpinner(final GameState state, final Point2d position) {
         EntityImpl e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNKSPINNER)
                 .position(position)
                 .with(new DrunkComponent(state.getWorld()))
                 .with(new SpinnerComponent(state.getWorld()))
                 .with(new HealthIntComponent(state.getWorld(),1))
                 .with(new CollisionComponent(state.getWorld(), Type.ENEMY_DRUNKSPINNER))
                 .view(new EnemyDrunkSpinnerGraphicComponent(GameMath.toPoint2D(position)))
                 .bbox(new CircleHitBox(new Circle2d(55)))
                 .zIndex(8)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
}