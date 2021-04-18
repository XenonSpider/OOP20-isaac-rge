package ryleh.view.graphics.enemies;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ryleh.common.GameMath;
import ryleh.view.Textures;
import ryleh.view.graphics.GraphicComponent;

/**
 * A class that provides the GraphicComponent of the view related to the
 * EnemySpinner Entity.
 */
public class EnemySpinnerGraphicComponent implements GraphicComponent {
    private Rotate rotation;
    private Rectangle rectangle;
    private FadeTransition enemyFade;
    private static final int FADE_DURATION = 200;

    /**
     * Creates a new Instance of EnemySpinnerGraphicComponent.
     */
    public EnemySpinnerGraphicComponent() {
        this(new Point2D(0, 0));
    }

    /**
     * Creates a new Instance of EnemySpinnerGraphicComponent with the given initial
     * position.
     * 
     * @param position The position at which this GraphicCOmponent needs to be
     *                 initialized.
     */
    public EnemySpinnerGraphicComponent(final Point2D position) {
        this.rectangle = new Rectangle(Textures.ENEMY_SPINNER.getWidth(), Textures.ENEMY_SPINNER.getHeight());
        this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        this.rectangle.setFill(Textures.ENEMY_SPINNER.getImagePattern());
        this.rotation = new Rotate();
        this.rotation.setAngle(GameMath.toDegrees(Math.PI / 60));
        this.enemyFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
        this.enemyFade.setFromValue(1.0);
        this.enemyFade.setToValue(0.0);
        this.enemyFade.setCycleCount(4);
        this.enemyFade.setAutoReverse(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Point2D position, final double deltaTime) {
        rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        rotation.setPivotX(position.getX());
        rotation.setPivotY(position.getY());
        rectangle.getTransforms().add(rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAdded(final Scene scene) {
        final Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getNode() {
        return rectangle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRemoved(final EventHandler<ActionEvent> event) {
        enemyFade.setOnFinished(event);
        this.enemyFade.play();
    }
}
