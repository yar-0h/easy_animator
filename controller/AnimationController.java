
package cs5004.animator.controller;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cs5004.animator.view.IAnimationView;
import cs5004.animator.model.IModel;

/**
 * This class represents an instance of an AnimationController Object.
 */
public class AnimationController extends JFrame implements IController, ActionListener {

  IModel model;
  IAnimationView view;
  JFrame f;
  JPanel aniPanel;

  JButton play;
  JButton increaseSpeed;
  JButton decreaseSpeed;
  JButton restart;
  JButton loopToggle;

  /**
   * This is the constructor for the controller.
   *
   * @param view The IAnimationView object to be manipulated.
   * @param model The IModel object to be utilized to create the view.
   */
  public AnimationController(IAnimationView view, IModel model) {

    this.model = model;
    this.view = view;

    JFrame f = new JFrame();

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    f.setSize(model.getCanvasW(), model.getCanvasH());
    f.setLocation(0, 0);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.white);

    restart = new JButton(" << ");
    restart.setActionCommand("restart");
    restart.addActionListener(this);

    decreaseSpeed = new JButton(" < ");
    decreaseSpeed.setActionCommand("speedDown");
    decreaseSpeed.addActionListener(this);

    play = new JButton("play");
    play.setActionCommand("play");
    play.addActionListener(this);

    increaseSpeed = new JButton(" > ");
    increaseSpeed.setActionCommand("speedUp");
    increaseSpeed.addActionListener(this);

    loopToggle = new JButton(" LOOP: OFF ");
    loopToggle.setActionCommand("loopToggle");
    loopToggle.addActionListener(this);

    setSize(model.getCanvasH(), model.getCanvasW());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());

    buttonPanel.add(restart);
    buttonPanel.add(decreaseSpeed);
    buttonPanel.add(play);
    buttonPanel.add(increaseSpeed);
    buttonPanel.add(loopToggle);

    aniPanel = view.providePanel();

    aniPanel.setPreferredSize(new Dimension(350, 350));
    f.add(BorderLayout.CENTER, aniPanel);
    f.add(buttonPanel, BorderLayout.NORTH);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equalsIgnoreCase("play")) {
      play.setText(" || ");
      play.setActionCommand("pause");
      view.startTimer();
    }

    else if (e.getActionCommand().equalsIgnoreCase("pause")) {
      play.setText(" PLAY ");
      play.setActionCommand("play");
      view.stopTimer();
    }

    else if (e.getActionCommand().equalsIgnoreCase("restart")) {
      view.restartFrame();
      view.startTimer();
    }

    else if (e.getActionCommand().equalsIgnoreCase("loopToggle")) {
      if (!view.isLooping()) {
        view.loopToggle();
        loopToggle.setText(" LOOP: ON");
      }
      else {
        view.loopToggle();
        loopToggle.setText(" LOOP: OFF");
      }
    }

    else if (e.getActionCommand().equalsIgnoreCase("speedDown")) {
      view.changeTimerDelay(10);
    }

    else if (e.getActionCommand().equalsIgnoreCase("speedUp")) {
      view.changeTimerDelay(-10);
    }
  }

  @Override
  public void run() {
    view.produceAniView();
  }
}

