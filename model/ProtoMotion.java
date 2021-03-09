package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sort of preliminary motion object, used as a go-between for use with the
 * parser.
 */
public class ProtoMotion {
  List<ProtoMotionType> transformationTypes = new ArrayList<>();
  int start;
  int end;

  int startX;
  int startY;
  int startW;
  int startH;
  int startR;
  int startG;
  int startB;

  int endX;
  int endY;
  int endW;
  int endH;
  int endR;
  int endG;
  int endB;

  /**
   * Constructs a ProtoMotion object and initializes it with specified parameters.
   * @param t1 the motion's start time
   * @param x1 the the motion's starting x-coordinate
   * @param y1 the the motion's starting y-coordinate
   * @param w1 the the motion's starting width
   * @param r1 the the motion's starting red value
   * @param g1 the the motion's starting green value
   * @param b1 the the motion's starting blue value
   * @param t2 the motion's end time
   * @param x2 the the motion's ending x-coordinate
   * @param y2 the the motion's ending y-coordinate
   * @param w2 the the motion's ending width
   * @param r2 the the motion's ending red value
   * @param g2 the the motion's ending green value
   * @param b2 the the motion's ending blue value
   */
  public ProtoMotion(int t1, int x1, int y1, int w1, int h1,
                     int r1, int g1, int b1, int t2, int x2, int y2,
                     int w2, int h2, int r2, int g2, int b2) {

    if ((x1 != x2) || (y1 != y2)) {
      this.transformationTypes.add(ProtoMotionType.TRANSLATION);
    }

    if ((w1 != w2) || (h1 != h2)) {
      this.transformationTypes.add(ProtoMotionType.SCALING);
    }

    if ((r1 != r2) || (g1 != g2) || (b1 != b2)) {
      this.transformationTypes.add(ProtoMotionType.COLORCHANGE);
    }

    else {
      this.transformationTypes.add(ProtoMotionType.START);
    }

    this.start = t1;
    this.end = t2;

    this.startX = x1;
    this.startY = y1;
    this.startW = w1;
    this.startH = h1;
    this.startR = r1;
    this.startG = g1;
    this.startB = b1;

    this.endX = x2;
    this.endY = y2;
    this.endW = w2;
    this.endH = h2;
    this.endR = r2;
    this.endG = g2;
    this.endB = b2;
  }

  public List<ProtoMotionType> getTypes() {
    return transformationTypes;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public int getStartX() {
    return startX;
  }

  public int getStartY() {
    return startY;
  }

  public int getStartW() {
    return startW;
  }

  public int getStartH() {
    return startH;
  }

  public int getStartR() {
    return startR;
  }

  public int getStartG() {
    return startG;
  }

  public int getStartB() {
    return startB;
  }

  public int getEndX() {
    return endX;
  }

  public int getEndY() {
    return endY;
  }

  public int getEndW() {
    return endW;
  }

  public int getEndH() {
    return endH;
  }

  public int getEndR() {
    return endR;
  }

  public int getEndG() {
    return endG;
  }

  public int getEndB() {
    return endB;
  }
}
