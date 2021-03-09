package cs5004.animator.model;

/**
 * This class represents an extension of the model.AbstractShape class,
 * in the form of a rectangle shape.
 */
public class Rectangle extends AbstractShape {

  ShapeType type = ShapeType.RECTANGLE;

  public Rectangle(int x, int y, int w, int h, int sFrame) {
    super(x, y, w, h, sFrame);
  }

  public Rectangle(int x, int y, int w, int h, int r, int g, int b, int sFrame) {
    super(x, y, w, h, r, g, b, sFrame);
  }

  public Rectangle(int x, int y, int w, int h, int sFrame, int eFrame) {
    super(x, y, w, h, sFrame, eFrame);
  }

  public Rectangle(int x, int y, int w, int h,
                   int r, int g, int b, int sFrame, int eFrame) {
    super(x, y, w, h, r, g, b, sFrame, eFrame);
  }


  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public String toString() {
    return "Type: model.Rectangle\n"
            + "Min corner: (" + this.x + "," + this.y + "), "
            + "Width: " + this.w + ", Height: " + this.h + ", "
            + "Color: (" + this.r + ", " + this.g + ", " + this.b + ")\n"
            + super.toString();
  }
}



