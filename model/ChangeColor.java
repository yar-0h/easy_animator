package cs5004.animator.model;

/**
 * This class represents an extension of the model.AbstractMotion class, in the form of a
 * model.ChangeColor motion.
 */
public class ChangeColor extends AbstractMotion {

  private int startingR;
  private int startingG;
  private int startingB;

  private int endingR;
  private int endingG;
  private int endingB;

  /**
   * Constructs a model.ChangeColor object and initializes it with specified parameters.
   * @param hostingShape the shape to be translated.
   * @param endingR The final red value of the Color change.
   * @param endingG The final green value of the color change.
   * @param endingB The final blue value of the color change.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  public ChangeColor(IShape hostingShape, int endingR, int endingG, int endingB,
                     int sFrame, int eFrame) throws IllegalArgumentException {
    super(sFrame, eFrame);
    if (hostingShape == null) {
      throw new IllegalArgumentException("shape does not exist\n");
    }
    else if (endingR < 0 || endingG < 0 || endingB < 0) {
      throw new IllegalArgumentException("RGB values may not be negative\n");
    }
    else if (endingR > 255 || endingG > 255 || endingB > 255) {
      throw new IllegalArgumentException("RGB values may not exceed 255\n");
    }
    this.startingR = hostingShape.getR();
    this.startingG = hostingShape.getG();
    this.startingB = hostingShape.getB();

    this.endingR = endingR;
    this.endingG = endingG;
    this.endingB = endingB;
  }

  /**
   * Alternate constructor for creating a model.ChangeColor object using a previous changeColor
   * instance's final values and initializes it with specified parameters.
   * @param previousColorChange the previous colorChange motion to be used as a template.
   * @param endingR The final red value of the Color change.
   * @param endingG The final green value of the color change.
   * @param endingB The final blue value of the color change.
   * @param sFrame The frame the motion starts.
   * @param eFrame The frame the motion ends.
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  ChangeColor(ChangeColor previousColorChange, int endingR, int endingG, int endingB,
              int sFrame, int eFrame) throws IllegalArgumentException {
    super(sFrame, eFrame);
    if (endingR < 0 || endingG < 0 || endingB < 0) {
      throw new IllegalArgumentException("RGB values may not be negative\n");
    }
    else if (endingR > 255 || endingG > 255 || endingB > 255) {
      throw new IllegalArgumentException("RGB values may not exceed 255\n");
    }
    this.startingR = previousColorChange.getEndR();
    this.startingG = previousColorChange.getEndG();
    this.startingB = previousColorChange.getEndB();

    this.endingR = endingR;
    this.endingG = endingG;
    this.endingB = endingB;
  }

  @Override
  public int getStartR() {
    return this.startingR;
  }

  @Override
  public int getStartG() {
    return this.startingG;
  }

  @Override
  public int getStartB() {
    return this.startingB;
  }

  @Override
  public int getEndR() {
    return this.endingR;
  }

  @Override
  public int getEndG() {
    return this.endingG;
  }

  @Override
  public int getEndB() {
    return this.endingB;
  }

  @Override
  public String toString() {
    return " changes color from (" + this.startingR + "," + this.startingG + "," + this.startingB
            + ") to (" + this.endingR + "," + this.endingG + "," + this.endingB + ")"
            + super.toString();
  }

  @Override
  public String toFPSString(int fps) {
    StringBuilder sb = new StringBuilder();
    if (getEndFrame() > getStartFrame()) {
      sb.append(" changes color from (").append(this.startingR).append(",")
              .append(this.startingG).append(",")
              .append(this.startingB).append(") to (")
              .append(this.endingR).append( ",")
              .append(this.endingG).append(",")
              .append(this.endingB).append(")")
              .append(" from time t=").append(getStartFrame() / fps)
              .append(" to t=").append(getEndFrame() / fps).append("\n");
    }
    else {
      sb.append(" changes color from (").append(this.startingR).append(",")
              .append(this.startingG).append(",")
              .append(this.startingB).append(") to (")
              .append(this.endingR).append( ",")
              .append(this.endingG).append(",")
              .append(this.endingB).append(")")
              .append(" from time t=").append(getStartFrame() / fps);
    }

    return sb.toString();
  }
}


