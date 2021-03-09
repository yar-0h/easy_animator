package cs5004.animator.model;

/**
 * This interface represents the operations offered by an instance of a 'motion'
 * object. One object of 'motion' represents an act of animation/transformation
 * to be executed by a 'shape' object.
 */
public interface IMotion {

  int getStartFrame();

  int getEndFrame();

  int getStartX();

  int getStartY();

  int getStartWidth();

  int getStartHeight();

  int getStartR();

  int getStartG();

  int getStartB();

  int getEndX();

  int getEndY();

  int getEndWidth();

  int getEndHeight();

  int getEndR();

  int getEndG();

  int getEndB();

  /**
   * This method is meant to act similarly as toString() method except that it specially formats the
   * frame signatures into real time as per an fps spec.
   *
   * @param fps the decided ratio of frames per seconds
   * @throws IllegalArgumentException if the fps argument is non-positive.
   */
  String toFPSString(int fps);
}



