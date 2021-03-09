package cs5004.animator.view;

import cs5004.animator.model.IModel;

/**
 * This interface represents the operations offered by the Easy Animator View
 * object. One object of this view potentially represents the view of an animated canvas.
 */
public interface IView {

  /**
   * The method sets up the view so it can go on to produce the animation.
   *
   * @param model the model that will be output
   * @param fps the frames per seconds used to establish the speed of an animation
   */
  Void setUpView(IModel model, int fps);

  String produceView();

}
