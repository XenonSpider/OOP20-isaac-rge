package ryleh.common;

public class Rectangle2d {

    public int height;
    public int width;
    public P2d upperLeft;
    public P2d lowerLeft;
    public P2d upperRight;
    public P2d lowerRight;

    /*
     * This constructor is used to instantiate a 2D rectangle that is oriented horizontally
     */
    public Rectangle2d(final int width, final int height, final int upperLeftX, final int upperLeftY) {
        super();
        this.height = height;
        this.width = width;
        this.upperLeft = new P2d(upperLeftX, upperLeftY);
        this.lowerLeft = new P2d(upperLeftX, upperLeftY + height);
        this.upperRight = new P2d(upperLeftX + width, upperLeftY);
        this.lowerRight = upperLeft.sum(new V2d(this.width, this.height));

    }
    /*
     * This constructor is used to instantiate a 2D rectangle whose orientation is not "simply" horizontal
     */
    public Rectangle2d(final P2d upperLeft, final P2d lowerLeft, final P2d lowerRight, final P2d upperRight) {
        super();
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
        this.upperRight = upperRight;
        this.width = (int) new V2d(upperLeft, upperRight).module();
        this.height = (int) new V2d(upperLeft, lowerLeft).module();
    }

    public boolean contains(final P2d position) {
        return position.x > this.upperLeft.x
               && position.x < this.upperRight.x 
               && position.y > this.upperLeft.y 
               && position.y < this.lowerLeft.y;
    }

    public boolean contains(final Rectangle2d rectangle) {
        return this.contains(rectangle.upperLeft) 
                && this.contains(rectangle.lowerRight)
                && this.contains(rectangle.upperRight)
                && this.contains(rectangle.lowerLeft);
    }

}