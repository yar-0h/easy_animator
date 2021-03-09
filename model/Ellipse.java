package cs5004.animator.model;

/**
 * This class represents an extension of the model.AbstractShape class,
 * in the form of an ellipse shape.
 */
public class Ellipse extends AbstractShape {

  ShapeType type = ShapeType.ELLIPSE;

  public Ellipse(int x, int y, int w, int h, int sFrame) {
    super(x, y, w, h, sFrame);
  }

  public Ellipse(int x, int y, int w, int h, int r, int g, int b, int sFrame) {
    super(x, y, w, h, r, g, b, sFrame);
  }

  public Ellipse(int x, int y, int w, int h, int sFrame, int eFrame) {
    super(x, y, w, h, sFrame, eFrame);
  }

  public Ellipse(int x, int y, int w, int h,
                 int r, int g, int b, int sFrame, int eFrame) {
    super(x, y, w, h, r, g, b, sFrame, eFrame);
  }

  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }

  @Override
  public String toString() {
    return "Type: model.Ellipse\n"
            + "Center: (" + this.x + "," + this.y + "), "
            + "X Radius: " + this.w + ", Y Radius: " + this.h + ", "
            + "Color: (" + this.r + ", " + this.g + ", " + this.b + ")\n"
            + super.toString();
  }
}


