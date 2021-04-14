package ryleh.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ryleh.core.GameState;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.components.ShootingComponent;
import ryleh.model.events.NewLevelEvent;
import ryleh.model.physics.Direction;
import ryleh.view.PlayerGraphicComponent;

public class InputController {
	
	private boolean isMoveUp;
	private boolean isMoveDown;
	private boolean isMoveLeft;
	private boolean isMoveRight;
	private boolean isShooting;
	private boolean newLevel;
	private Direction lastDir;
	private Direction currentDir;
	private final PlayerGraphicComponent graphic;
	private final PhysicsComponent physics; 
	private final Scene scene;
	private final Entity player;
	private final World world;
	
	public InputController(final GameState state) {
		this.scene = state.getView().getScene();
		this.world = state.getWorld();
		this.player = state.getPlayer();
		this.graphic = (PlayerGraphicComponent) this.player.getView();
		this.physics = (PhysicsComponent) this.player.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
		this.lastDir = Direction.UP;
		this.currentDir = Direction.IDLE;
	}
	
	public void initInput(){
		this.scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			case A: this.currentDir = Direction.LEFT;
				break;
			case D: this.currentDir = Direction.RIGHT;
				break;
			case W: this.currentDir = Direction.UP;
				break;
			case S: this.currentDir = Direction.DOWN;
				break;
			case L: this.newLevel = true;
				break;
			case SPACE: this.isShooting = true;;
			break;
			default:
				break;
			}
		});
		scene.setOnKeyReleased(key -> {
			switch (key.getCode()) {
			case A: this.currentDir = Direction.IDLE;
				break;
			case D: this.currentDir = Direction.IDLE;
				break;
			case W: this.currentDir = Direction.IDLE;
				break;
			case S: this.currentDir = Direction.IDLE;
				break;
			case L: this.newLevel = false;
				break;
			case SPACE: this.isShooting = false;
			break;
			default:
				break;
			}
		});
	}
	public void updateInput() {
		if (newLevel) {
			world.notifyWorldEvent(new NewLevelEvent(this.player.getGameObject()));
		}
		if (isShooting) {
			if (this.physics.getDirection().equals(Direction.IDLE)) {
				this.shoot(this.lastDir);
			} else {
				this.shoot(this.physics.getDirection());
			}
		}
		this.move(this.currentDir);
//		if (this.notMoving()) {
//			this.physics.setVelocity(Direction.IDLE.getPoint());
//			this.physics.setDirection(Direction.IDLE);
//			this.graphic.setDirection(Direction.IDLE);
//			this.physics.resetBlocked();
//		}
	}

	private void setMoving(final KeyCode key) {
//		switch (key) {
//		case A: this.currentDir = Direction.LEFT;
//			break;
//		case D: this.currentDir = Direction.RIGHT;
//			break;
//		case W: this.currentDir = Direction.UP;
//			break;
//		case S: this.currentDir = Direction.DOWN;
//			break;
//		case L: this.newLevel = isMoving;
//			break;
//		case SPACE: this.isShooting = isMoving;
//		break;
//		default:
//			break;
//		}
	}
	private void shoot(final Direction direction) {
		((ShootingComponent) player.getGameObject().getComponent(ShootingComponent.class).get())
		    .shoot(direction.getPoint());
	}
	
	private void move(final Direction direction) {
			this.lastDir = direction;
			this.physics.setVelocity(direction.getPoint());
			this.physics.setDirection(direction);
			if (!this.physics.getBlocked().equals(direction)) {
                            this.physics.resetBlocked();
                        }
			this.graphic.setDirection(direction);
	}
	
	private boolean notMoving() {
		return !this.isMoveDown && !this.isMoveUp && !this.isMoveLeft && !this.isMoveRight;
	}
}
