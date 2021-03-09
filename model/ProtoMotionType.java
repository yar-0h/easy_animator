package cs5004.animator.model;

/**
 * This enumerated type represents the 3 current motions supported by the EasyAnimatorModel with
 * the addition of a 'START' type used to initialize a shape's state.
 * START is used to set initial properties of shape.
 * COLORCHANGE represents a changeColor motion.
 * TRANSLATION represents a translation motion.
 * SCALING represents a scaling motion.
 */

public enum ProtoMotionType {
  START, COLORCHANGE, TRANSLATION, SCALING
}
