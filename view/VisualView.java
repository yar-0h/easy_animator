package cs5004.animator.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.BorderLayout;

import java.util.List;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;

/**
 * This class represents a VisualView, which produces no String but instead an animation using the
 * JSwing library.
 */
public class VisualView extends JFrame implements IView, IAnimationView {

  IModel model;
  int fps;
  int canvasX;
  int canvasY;
  int canvasW;
  int canvasH;
  int currentFrame;

  List<List<IShape>> animationMaster;
  VisualPanel vPanel;
  Timer timer;
  boolean looping;


  @Override
  public Void setUpView(IModel model, int fps) {
    this.model = model;
    this.fps = 100 / fps;
    this.canvasH = model.getCanvasH();
    this.canvasW = model.getCanvasW();
    this.canvasX = model.getTopLeftX();
    this.canvasY = model.getTopLeftY();
    this.looping = false;

    this.currentFrame = 0;
    JFrame f = new JFrame();

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    vPanel = new VisualPanel(canvasX, canvasY);

    f.getContentPane().add(BorderLayout.CENTER, vPanel);
    f.setVisible(true);
    f.setSize(canvasW, canvasH);
    //f.setBounds(canvasX+100, canvasY+100, canvasW + 100, canvasH + 100);
    f.setLocation(0, 0);


    animationMaster = model.getFrameByFrame();

    this.timer = new Timer(fps, e -> {

      List<IShape> viewShapes = animationMaster.get(currentFrame);

      render(viewShapes);
      currentFrame++;
      if (currentFrame >= animationMaster.size()) {
        timer.stop();
      }
    });
    return null;
  }

  @Override
  public String produceView() {
    timer.setRepeats(true);
    timer.setInitialDelay(1000);
    timer.setDelay(fps);
    timer.start();
    return null;
  }

  @Override
  public void produceAniView() {
    timer.setRepeats(true);
    timer.setInitialDelay(10);
    timer.setDelay(fps);
    this.looping = false;
  }

  /**
   * This method is called when the view is setup to start up the animation.
   *
   * @param shapes a list of IShape objects which is fed to the animation panel's drawShapes method.
   */
  public void render(List<IShape> shapes) {
    vPanel.drawShapes(shapes);
  }

  @Override
  public JPanel providePanel() {
    return vPanel;
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

