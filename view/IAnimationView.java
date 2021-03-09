package cs5004.animator.view;

import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.IShape;

/**
 * This interface represents the operations offered by the  IAnimationView
 * object. One object of this view potentially represents the view of an animated canvas.
 */
public interface IAnimationView {

  /**
   * This method is called when the view is setup to start up the animation.
   *
   * @param shapes a list of IShape objects which is fed to the animation panel's drawShapes
   *               method.
   */
  void render(List<IShape> shapes);


  JPanel providePanel();

  /**
   * This method stops the animation timer.
   */
  void stopTimer();

  /**
   * This method starts the animation timer.
   */
  void startTimer();

  /**
   * This method changes the delay between timer steps for the animation.
   *
   * @param change the degree to change the delay.
   */
  void changeTimerDelay(int change);

  /**
   * This method sets the animation frame back to zero.
   */
  void restartFrame();

  /**
   * This method is called to check the animation's looping status.
   *
   * @return true if set to loop.
   */
  boolean isLooping();

  /**
   * This method is called to toggle the looping of the animation.
   */
  void loopToggle();

  /**
   * This method is called to begin the animation.
   */
  void produceAniView();
}
