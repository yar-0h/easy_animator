package cs5004.animator.model;

/**
 * This class represents an extension of the model.AbstractMotion class,
 * in the form of a model.Scaling motion.
 */
public class Scaling extends AbstractMotion {
  private int startingWidth;
  private int startingHeight;

  private int endingWidth;
  private int endingHeight;

  /**
   * Constructs a model.Translation object and initializes it with specified parameters.
   * @param hostingShape the shape to be translated.
   * @param endingWidth The final width of the scaling.
   * @param endingHeight The final height of the scaling.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  public Scaling(IShape hostingShape, int endingWidth, int endingHeight,
                 int sFrame, int eFrame) {
    super(sFrame, eFrame);
    if (hostingShape == null) {
      throw new IllegalArgumentException("shape does not exist\n");
    }
    else if (endingWidth < 0 || endingHeight < 0) {
      throw new IllegalArgumentException("width and height may not be negative\n");
    }
    this.startingWidth = hostingShape.getW();
    this.startingHeight = hostingShape.getH();
    this.endingWidth = endingWidth;
    this.endingHeight = endingHeight;
  }

  /**
   * An alternate constructor. Constructs a model.Scaling object
   * using a previous model.Scaling object and initializes it with specified parameters.
   * @param previousScaling the last performed scaling on the host shape.
   * @param endingWidth The final width of the scaling.
   * @param endingHeight The final height of the scaling.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  Scaling(Scaling previousScaling, int endingWidth, int endingHeight,
          int sFrame, int eFrame) {
    super(sFrame, eFrame);
    if (endingWidth < 0 || endingHeight < 0) {
      throw new IllegalArgumentException("width and height may not be negative\n");
    }
    this.startingWidth = previousScaling.getEndHeight();
    this.startingHeight = previousScaling.getEndWidth();
    this.endingWidth = endingWidth;
    this.endingHeight = endingHeight;
  }

  @Override
  public int getStartWidth() {
    return this.startingWidth;
  }

  @Override
  public int getStartHeight() {
    return this.startingHeight;
  }

  @Override
  public int getEndHeight() {
    return this.endingHeight;
  }

  @Override
  public int getEndWidth() {
    return this.endingWidth;
  }

  @Override
  public String toString() {
    return " scales from Width: " + this.startingWidth + ", Height: " + this.startingHeight
            + " to Width: " + this.endingWidth + ", Height: " + this.endingHeight
            + super.toString();
  }

  @Override
  public String toFPSString(int fps) {
    StringBuilder sb = new StringBuilder();
    if (getEndFrame() > getStartFrame()) {
      sb.append(" scales from height ").append(this.startingHeight)
              .append(" and width ").append(this.startingWidth)
              .append(" to height ").append(this.endingHeight)
              .append(" and width ").append(this.endingWidth)
              .append(" from time t=").append(getStartFrame() / fps)
              .append(" to t=").append(getEndFrame() / fps).append("\n");
    }
    else {
      sb.append(" scales from height ").append(this.startingHeight)
              .append(" and width ").append(this.startingWidth)
              .append(" to height").append(this.endingHeight)
              .append(" and width ").append(this.endingWidth)
              .append(" from time t=").append(getStartFrame() / fps).append("\n");
    }
    return sb.toString();
  }
}
