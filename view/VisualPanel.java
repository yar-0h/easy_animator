package cs5004.animator.view;

import java.awt.Graphics;
import java.awt.Color;

import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.IShape;

/**
 * This class represents an extension of the JPanel class.
 * This is modified to help produce our desired animation.
 */
public class VisualPanel extends JPanel {

  List<IShape> drawQueue = null;
  int canvasX;
  int canvasY;

  public VisualPanel(int canvasX, int canvasY) {
    this.canvasX = canvasX;
    this.canvasY = canvasY;
  }


  /**
   * This method updates the list of shapes to draw and then refreshes the panel.
   *
   * @param currentFrameShapes a list of IShape objects, fed to it by the frame's render method.
   */
  public void drawShapes(List<IShape> currentFrameShapes) {
    this.drawQueue = currentFrameShapes;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //List<IShape> frameShapes;
    Color shapeColor;

    if (drawQueue != null) {
      for (IShape shape : this.drawQueue) {
        shapeColor = new Color(shape.getR(), shape.getG(), shape.getB());
        g.setColor(shapeColor);
        switch (shape.getType()) {
          case RECTANGLE:
            g.fillRect((shape.getX() - canvasX), (shape.getY() - canvasY),
                    shape.getW(), shape.getH());
            break;
          case ELLIPSE:
            g.fillOval((shape.getX() - canvasX), (shape.getY() - canvasY),
                    shape.getW(), shape.getH());
            break;
          default:
            break;
        }
      }
    }
  }
}