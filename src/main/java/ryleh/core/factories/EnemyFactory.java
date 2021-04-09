package ryleh.core.factories;

import ryleh.common.Circle2d;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.core.GameEngine;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.model.components.PhysicsComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.ViewHandler;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;
import ryleh.view.enemies.EnemyLurkerGraphicComponent;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.enemies.EnemySpinnerGraphicComponent;
import ryleh.view.other.RockGraphicComponent;

public class EnemyFactory {
     private static EnemyFactory instance;

     private EnemyFactory() { }

     public static EnemyFactory getInstance() {
            if (instance == null) {
                    instance = new EnemyFactory();
            }
            return instance;
    }

     public Entity createEnemyShooter(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(300, 800)
                 .with(new ShooterComponent(world, view, BasicFactory.getInstance().getPlayer()))
                 .with(new HealthIntComponent(world, 5))
                 .view(new EnemyShooterGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemySpinner(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(900, 500)
                 .with(new SpinnerComponent(world, view))
                 .with(new HealthIntComponent(world, 5))
                 .view(new EnemySpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunk(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNK)
                 .position(960, 540)
                 .with(new DrunkComponent(world))
                 .with(new HealthIntComponent(world, 5))
                 .view(new EnemyDrunkGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createBullet(final World world, final ViewHandler view, final P2d origin, final V2d direction) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_BULLET)
                 .position(0, 0)
                 .with(new BulletComponent(world, origin, direction))
                 .view(new EnemyShooterGraphicComponent() )
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyLurker(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(800, 200)
                 .with(new LurkerComponent(world, BasicFactory.getInstance().getPlayer()))
                 .with(new HealthIntComponent(world, 5))
                 .view(new EnemyLurkerGraphicComponent(
                         BasicFactory.getInstance().getPlayer().getGameObject()))
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunkSpinner(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNKSPINNER)
                 .position(1200, 540)
                 .with(new DrunkComponent(world))
                 .with(new HealthIntComponent(world, 5))
                 .view(new EnemySpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
}