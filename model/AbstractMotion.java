package cs5004.animator.model;

/**
 * This class represents the operations offered by the abstract class, model.AbstractMotion.
 * One model.AbstractMotion object represents a generic motion with a frame at
 * which it starts and a frame at which it ends
 */
public abstract class AbstractMotion implements IMotion {
  private final int startFrame;
  private final int endFrame;

  /**
   * Constructs an model.AbstractMotion object and initializes it with specified parameters.
   *
   * @param startFrame the horizontal location of the shape on an x/y plane
   * @param endFrame   the vertical location of the shape on an x/y plane
   * @throws IllegalArgumentException in the event of invalid parameters.
   */
  AbstractMotion(int startFrame, int endFrame) throws IllegalArgumentException {
    if (startFrame < 0 || endFrame < 0) {
      throw new IllegalArgumentException("motion frames may not be non-negative");
    } else if (startFrame > endFrame) {
      throw new IllegalArgumentException("motion end may not precede start");
    }
    this.startFrame = startFrame;
    this.endFrame = endFrame;
  }

  @Override
  public int getStartFrame() {
    return this.startFrame;
  }

  @Override
  public int getEndFrame() {
    return this.endFrame;
  }

  @Override
  public int getStartX() {
    return 0;
  }

  @Override
  public int getStartY() {
    return 0;
  }

  @Override
  public int getStartWidth() {
    return 0;
  }

  @Override
  public int getStartHeight() {
    return 0;
  }

  @Override
  public int getStartR() {
    return 0;
  }

  @Override
  public int getStartG() {
    return 0;
  }

  @Override
  public int getStartB() {
    return 0;
  }

  @Override
  public int getEndX() {
    return 0;
  }

  @Override
  public int getEndY() {
    return 0;
  }

  @Override
  public int getEndWidth() {
    return 0;
  }

  @Override
  public int getEndHeight() {
    return 0;
  }

  @Override
  public int getEndR() {
    return 0;
  }

  @Override
  public int getEndG() {
    return 0;
  }

  @Override
  public int getEndB() {
    return 0;
  }

  @Override
  public String toString() {
    if (this.endFrame > this.startFrame) {
      return " from t=" + this.startFrame + " to t=" + this.endFrame + "\n";
    } else {
      return " from t=" + this.startFrame + "\n";
    }
  }
}





