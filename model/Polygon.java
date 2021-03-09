package cs5004.animator.model;

import java.util.List;

/**
 * This class represents an extension of the model.AbstractShape class,
 * in the form of an polygon shape.
 */
public class Polygon extends AbstractShape {
  ShapeType type = ShapeType.POLYGON;

  private final List<CartesianPoint> listOfVertices;

  Polygon(int x, int y, int w, int h, int sFrame, List<CartesianPoint> listOfVertices) {
    super(x, y, w, h, sFrame);
    this.listOfVertices = listOfVertices;
  }

  Polygon(int x, int y, int w, int h, int r, int g, int b, int sFrame,
                    List<CartesianPoint> listOfVertices) {
    super(x, y, w, h, r, g, b, sFrame);
    this.listOfVertices = listOfVertices;
  }

  Polygon(int x, int y, int w, int h, int sFrame, int eFrame,
                    List<CartesianPoint> listOfVertices) {
    super(x, y, w, h, sFrame, eFrame);
    this.listOfVertices = listOfVertices;
  }

  Polygon(int x, int y, int w, int h, int r, int g, int b, int sFrame, int eFrame,
                    List<CartesianPoint> listOfVertices) {
    super(x, y, w, h, r, g, b, sFrame, eFrame);
    this.listOfVertices = listOfVertices;
  }

  @Override
  public List<CartesianPoint> getListOfVertices() {
    return this.listOfVertices;
  }

  @Override
  public ShapeType getType() {
    return ShapeType.POLYGON;
  }

  @Override
  public String toString() {
    return "Type: model.Polygon\n"
            + "Origin: (" + this.x + ", " + this.y + "), "
            + "Width: " + this.w + ", Height: " + this.h + ", "
            + "Color: (" + this.r + ", " + this.g + ", " + this.b + ")\n"
            + "Number of Vertices: " + this.listOfVertices.size() + super.toString();
  }
}

