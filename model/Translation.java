package cs5004.animator.model;

/**
 * This class represents an extension of the model.AbstractMotion class,
 * in the form of a model.Translation.
 */
public class Translation extends AbstractMotion {

  private int startingX;
  private int startingY;

  private int endingX;
  private int endingY;

  /**
   * Constructs a model.Translation object and initializes it with specified parameters.
   * @param hostingShape the shape to be translated.
   * @param endingX The final x location of the translation.
   * @param endingY The final y location of the translation.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  public Translation(IShape hostingShape, int endingX, int endingY,
                     int sFrame, int eFrame) throws IllegalArgumentException {
    super(sFrame, eFrame);
    if (hostingShape == null) {
      throw new IllegalArgumentException("shape does not exist\n");
    }
    this.startingX = hostingShape.getX();
    this.startingY = hostingShape.getY();
    this.endingX = endingX;
    this.endingY = endingY;
  }

  /**
   * Alternate constructor, creating a model.Translation object using the last performed translation
   * and initializes it with specified parameters.
   * @param previousTranslation the shape to be translated.
   * @param endingX The final x location of the translation.
   * @param endingY The final y location of the translation.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  Translation(Translation previousTranslation, int endingX, int endingY,
              int sFrame, int eFrame) throws IllegalArgumentException {
    super(sFrame, eFrame);
    this.startingX = previousTranslation.getEndX();
    this.startingY = previousTranslation.getEndY();
    this.endingX = endingX;
    this.endingY = endingY;
  }

  @Override
  public int getStartX() {
    return this.startingX;
  }

  @Override
  public int getStartY() {
    return this.startingY;
  }

  @Override
  public int getEndX() {
    return this.endingX;
  }

  @Override
  public int getEndY() {
    return this.endingY;
  }

  @Override
  public String toString() {
    return " moves from (" + this.startingX + ", " + this.startingY
            + ") to (" + this.endingX + ", " + this.endingY + ")" + super.toString();
  }

  @Override
  public String toFPSString(int fps) {
    StringBuilder sb = new StringBuilder();
    if (getEndFrame() > getStartFrame()) {
      sb.append(" moves from (").append(this.startingX).append(",")
              .append(this.startingY).append(") to (")
              .append(this.endingX).append( ",")
              .append(this.endingY).append(")")
              .append(" from time t=").append(getStartFrame() / fps)
              .append(" to t=").append(getEndFrame() / fps).append("\n");
    }
    else {
      sb.append(" moves from (").append(this.startingX).append(",")
              .append(this.startingY).append(") to (")
              .append(this.endingX).append( ",")
              .append(this.endingY).append(")")
              .append(" from time t=").append(getStartFrame() / fps).append("\n");
    }

    return sb.toString();
  }
}


