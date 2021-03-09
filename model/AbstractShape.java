package cs5004.animator.model;

import java.util.List;

/**
 * This class represents the operations offered by the abstract class, model.AbstractShape.
 * One model.AbstractShape object represents a generic shape with a location, color,
 * time of appearance and potentially, a time of disappearance.
 */
public abstract class AbstractShape implements IShape {

  final int x;
  final int y;
  final int w;
  final int h;

  final int r;
  final int g;
  final int b;

  private ShapeType type;

  private int startFrame;
  // Shape's default ending frame is -1 if it has no frame of disappearance
  private int endFrame = -1;

  /**
   * Constructs an model.AbstractShape object and initializes it with specified parameters.
   * @param x the horizontal location of the shape on an x/y plane
   * @param y the vertical location of the shape on an x/y plane
   * @param w the shape's width.
   * @param h the shape's height.
   * @param startFrame the frame when the shape first appears.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  AbstractShape(int x, int y, int w, int h, int startFrame)
          throws IllegalArgumentException {
    if (w <= 0 || h <= 0 || startFrame < 0) {
      throw new IllegalArgumentException("height/width may not be non-negative\n");
    }
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;

    this.r = 0;
    this.g = 0;
    this.b = 0;

    this.startFrame = startFrame;
  }

  /**
   * An alternate model.AbstractShape constructor.
   * Allows for the constructing of a shape with specific color.
   * Constructs an model.AbstractShape object and initializes it with specified parameters.
   * @param x the horizontal location of the shape on an x/y plane
   * @param y the vertical location of the shape on an x/y plane
   * @param w the shape's width.
   * @param h the shape's height.
   * @param r the red value of the shape's color.
   * @param g the green value of the shape's color.
   * @param b the blue value of the shape's color.
   * @param startFrame the frame when the shape first appears.
   * @throws IllegalArgumentException in the event of illegal parameters.
   */
  AbstractShape(int x, int y, int w,int h,
                          int r, int g, int b,
                          int startFrame) throws IllegalArgumentException {
    if (w <= 0 || h <= 0 || r < 0 || g < 0 || b < 0 || startFrame < 0) {
      throw new IllegalArgumentException("parameters may not be non-negative\n");
    }
    else if (r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("color values may not exceed 255\n");
    }
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;

    this.r = r;
    this.g = g;
    this.b = b;

    this.startFrame = startFrame;
  }

  /**
   * An alternate model.AbstractShape constructor.
   * Allows for the constructing of a shape with specific endFrame in mind.
   * Constructs an model.AbstractShape object and initializes it with specified parameters.
   * @param x the horizontal location of the shape on an x/y plane
   * @param y the vertical location of the shape on an x/y plane
   * @param w the shape's width.
   * @param h the shape's height.
   * @param startFrame the frame when the shape first appears.
   * @param endFrame the frame when the shape disappears.
   * @throws IllegalArgumentException in the event of illegal parameters.
   */
  AbstractShape(int x, int y, int w,int h,
                          int startFrame, int endFrame) throws IllegalArgumentException {
    if (w <= 0 || h <= 0 || endFrame < 0 || startFrame < 0) {
      throw new IllegalArgumentException("parameters may not be non-negative\n");
    }
    else if (startFrame > endFrame) {
      throw new IllegalArgumentException("start frame must precede end frame\n");
    }

    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;

    this.r = 0;
    this.g = 0;
    this.b = 0;

    this.startFrame = startFrame;
    this.endFrame = endFrame;
  }

  /**
   * An alternate model.AbstractShape constructor.
   * Allows for the constructing of a shape with specific color and endFrame.
   * Constructs an model.AbstractShape object and initializes it with specified parameters.
   * @param x the horizontal location of the shape on an x/y plane
   * @param y the vertical location of the shape on an x/y plane
   * @param w the shape's width.
   * @param h the shape's height.
   * @param r the red value of the shape's color.
   * @param g the green value of the shape's color.
   * @param b the blue value of the shape's color.
   * @param startFrame the frame when the shape first appears.
   * @param endFrame the frame when the shape disappears.
   * @throws IllegalArgumentException in the event of illegal parameters.
   */
  AbstractShape(int x, int y, int w,int h,
                          int r, int g, int b,
                          int startFrame, int endFrame) {
    if (w <= 0 || h <= 0 || r < 0 || g < 0 || b < 0
            || endFrame < 0 || startFrame < 0) {
      throw new IllegalArgumentException("parameters may not be non-negative\n");
    }
    else if (r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("color values may not exceed 255\n");
    }
    else if (startFrame > endFrame) {
      throw new IllegalArgumentException("start frame must precede end frame\n");
    }
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;

    this.r = r;
    this.g = g;
    this.b = b;

    this.startFrame = startFrame;
    this.endFrame = endFrame;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getW() {
    return this.w;
  }

  @Override
  public int getH() {
    return this.h;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public String toString() {
    if (this.endFrame > this.startFrame) {
      return "Appears at t=" + this.startFrame + "\n"
              + "Disappears at t=" + this.endFrame + "\n\n";
    } else {
      return "Appears at t=" + this.startFrame + "\n\n";
    }
  }

  @Override
  public String toFPSString(float fps) {
    if (this.endFrame > this.startFrame) {
      return "appears at time t=" + (int)(this.startFrame / fps)
              + " and disappears at time t=" + (int)(this.endFrame / fps) + "\n";
    } else {
      return "appears at time t=" + (int)(this.startFrame / fps) + "\n";
    }
  }

  @Override
  public int getFirstAppearance() {
    return this.startFrame;
  }

  @Override
  public int getLastAppearance() {
    return this.endFrame;
  }

  @Override
  public List<CartesianPoint> getListOfVertices() {
    // currently only returns a meaningful value if the shape is a polygon
    return null;
  }
}
