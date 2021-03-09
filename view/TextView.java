package cs5004.animator.view;

import java.util.LinkedHashMap;
import java.util.List;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.IMotion;
import cs5004.animator.model.ShapeType;


/**
 * This class represents a TextView, which produces a String view formatted very similarly to what
 * can be found on the assignment page.
 */
public class TextView implements IView {

  IModel model;
  int fps;
  int canvasW;
  int canvasH;
  int canvasX;
  int canvasY;

  @Override
  public Void setUpView(IModel model, int fps) {
    this.model = model;
    this.fps = fps;
    this.canvasW = model.getCanvasW();
    this.canvasH = model.getCanvasH();
    this.canvasX = model.getTopLeftX();
    this.canvasY = model.getTopLeftY();
    return null;
  }

  @Override
  public String produceView() {

    LinkedHashMap<String, IShape> createdShapes = model.getCreatedShapes();
    LinkedHashMap<String, List<IMotion>> createdMotions = model.getCreatedMotions();
    StringBuilder outputString = new StringBuilder();

    outputString.append("canvas ").append(canvasX).append(" ").append(canvasY).append(" ")
            .append(canvasW).append(" ").append(canvasH).append("\n\n");

    for (String id : createdShapes.keySet()) {
      IShape shape = createdShapes.get(id);

      if (shape.getType() == ShapeType.RECTANGLE) {
        outputString.append("Create rectangle ").append(id)
                .append(" with color (")
                .append(shape.getR()).append(",")
                .append(shape.getG()).append(",")
                .append(shape.getB()).append(")")
                .append(" and corner at (")
                .append(shape.getX()).append(",")
                .append(shape.getY()).append("), width ")
                .append(shape.getW()).append(" and height ")
                .append(shape.getH()).append("\n");
      }

      else if (shape.getType() == ShapeType.ELLIPSE) {
        outputString.append("Create ellipse ")
                .append(id).append(" with color (")
                .append(shape.getR()).append(",")
                .append(shape.getG()).append(",")
                .append(shape.getB()).append(")")
                .append(" and center at (")
                .append(shape.getX()).append(",")
                .append(shape.getY()).append("), radius ")
                .append(shape.getW()).append(" and ")
                .append(shape.getH()).append("\n");
      }
    }

    outputString.append("\n");

    for (String id : createdShapes.keySet()) {
      IShape shape = createdShapes.get(id);
      outputString.append(id).append(" ").append(shape.toFPSString(fps));
    }
    outputString.append("\n");


    for (String key : createdMotions.keySet()) {
      List<IMotion> value = createdMotions.get(key);
      for (IMotion iMotion : value) {
        outputString.append(key).append(iMotion.toFPSString(fps));
      }
    }
    return outputString.toString();
  }
}

