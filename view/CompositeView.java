package cs5004.animator.view;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.List;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;

/**
 * This class represents a VisualView, which produces no String but instead an animation using the
 * JSwing library.
 */
public class CompositeView extends JFrame implements IView, IAnimationView {

  IModel model;
  int fps;
  int canvasX;
  int canvasY;
  int canvasW;
  int canvasH;
  int currentFrame;
  boolean looping;

  List<List<IShape>> animationMaster;
  VisualPanel vPanel;
  Timer timer;

  @Override
  public Void setUpView(IModel model, int fps) {
    this.model = model;
    this.fps = 100 / fps;
    this.canvasH = model.getCanvasH();
    this.canvasW = model.getCanvasW();
    this.canvasX = model.getTopLeftX();
    this.canvasY = model.getTopLeftY();

    this.currentFrame = 0;

    vPanel = new VisualPanel(canvasX, canvasY);


    animationMaster = model.getFrameByFrame();

    this.timer = new Timer(fps, e -> {

      List<IShape> viewShapes = animationMaster.get(currentFrame);

      render(viewShapes);
      currentFrame++;
      if ((currentFrame >= animationMaster.size()) && (!this.looping)) {
        timer.stop();
        currentFrame = 0;
      } else if (currentFrame >= animationMaster.size()) {
        currentFrame = 0;
      }
    });

    return null;
  }


  @Override
  public String produceView() {
    timer.setRepeats(true);
    timer.setInitialDelay(10);
    timer.setDelay(fps);
    this.looping = false;
    //timer.start();
    return null;
  }

  @Override
  public void produceAniView() {
    timer.setRepeats(true);
    timer.setInitialDelay(10);
    timer.setDelay(fps);
    this.looping = false;
  }

  @Override
  public JPanel providePanel() {
    return vPanel;
  }

  @Override
  public void render(List<IShape> shapes) {
    vPanel.drawShapes(shapes);
  }

  @Override
  public void stopTimer() {
    timer.stop();
  }

  @Override
  public void startTimer() {
    timer.start();
  }

  @Override
  public void changeTimerDelay(int change) {
    timer.setDelay(timer.getDelay() + change);
  }

  @Override
  public void restartFrame() {
    currentFrame = 0;
  }

  @Override
  public boolean isLooping() {
    return looping;
  }

  @Override
  public void loopToggle() {
    looping = !looping;
  }
}



