package cs5004.animator.model;

import java.util.List;

/**
 * This interface represents the operations offered by a Shape object.
 * Objects represent a geographic shape.
 */
public interface IShape {

  int getX();

  int getY();

  int getW();

  int getH();

  int getR();

  int getG();

  int getB();

  int getFirstAppearance();

  int getLastAppearance();

  ShapeType getType();

  List<CartesianPoint> getListOfVertices();

  /**
   * This method is meant to act similarly as toString() method except that it specially formats the
   * frame signatures into real time as per an fps spec.
   *
   * @param fps the decided ratio of frames per seconds
   * @throws IllegalArgumentException if the fps argument is non-positive.
   */
  String toFPSString(float fps);
}


