package cs5004.animator.view;

import java.util.LinkedHashMap;
import java.util.List;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IMotion;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.Translation;
import cs5004.animator.model.Scaling;
import cs5004.animator.model.ChangeColor;

/**
 * This class represents an SVGView, which produces a view formatted to SVG standards.
 */
public class SVGView implements IView {

  IModel model;
  int fps;

  @Override
  public Void setUpView(IModel model, int fps) {
    this.model = model;
    this.fps = 1000 / fps;
    return null;
  }

  @Override
  public String produceView() {

    LinkedHashMap<String, IShape> createdShapes = model.getCreatedShapes();
    LinkedHashMap<String, List<IMotion>> createdMotions = model.getCreatedMotions();
    String shapeType = null;
    String x = null;
    String y = null;
    String height = null;
    String width = null;

    StringBuilder outputString = new StringBuilder("<svg width=\"");
    outputString.append(this.model.getCanvasW())
            .append("\" height=\"").append(this.model.getCanvasH())

            .append("\" viewBox=\"").append(this.model.getTopLeftX()).append(" ")
            .append(this.model.getTopLeftY()).append(" ")
            .append(this.model.getCanvasW()).append(" ")
            .append(this.model.getCanvasH()).append("\" version=\"1.1\"\n")
            .append("     xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    for (String key : createdShapes.keySet()) {
      outputString.append("<");
      IShape outputShape = createdShapes.get(key);
      if (outputShape.getType() == ShapeType.RECTANGLE) {
        shapeType = "rect";
        x = "x";
        y = "y";
        height = "height";
        width = "width";

        outputString.append("rect id=");
        outputString.append("\"").append(key).append("\" ").append(x)
                .append("=\"").append(outputShape.getX()).append("\" ")
                .append(y).append("=\"").append(outputShape.getY())
                .append("\" ").append(width).append("=\"").append(outputShape.getW())
                .append("\" ").append(height).append("=\"")
                .append(outputShape.getH()).append("\" ");
      }
      else if (outputShape.getType() == ShapeType.ELLIPSE) {
        shapeType = "ellipse";
        x = "cx";
        y = "cy";
        height = "ry";
        width = "rx";

        outputString.append("ellipse id=");
        outputString.append("\"").append(key).append("\" ").append(x)
                .append("=\"").append(outputShape.getX()).append("\" ")
                .append(y).append("=\"").append(outputShape.getY())
                .append("\" ").append(width).append("=\"").append(outputShape.getW() / 2)
                .append("\" ").append(height).append("=\"")
                .append(outputShape.getH() / 2).append("\" ");
      }
      outputString.append("fill=\"rgb(").append(outputShape.getR()).append(",")
              .append(outputShape.getG()).append(",").append(outputShape.getB()).append(")\" ")
              .append("visibility=\"visible\" >\n");
      List<IMotion> value = createdMotions.get(key);
      for (IMotion iMotion : value) {
        if (iMotion instanceof Translation) {
          if (iMotion.getEndX() != iMotion.getStartX()) {
            outputString.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(iMotion.getStartFrame() * fps).append("ms\" dur=\"")
                    .append((iMotion.getEndFrame() - (iMotion.getStartFrame())) * fps)
                    .append("ms\" ")
                    .append("attributeName=\"").append(x).append("\" from=\"")
                    .append(iMotion.getStartX()).append("\" ")
                    .append("to=\"").append(iMotion.getEndX()).append("\" fill=\"freeze\" />\n");
          }
          if (iMotion.getEndY() != iMotion.getStartY()) {
            outputString.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(iMotion.getStartFrame() * fps).append("ms\" dur=\"")
                    .append((iMotion.getEndFrame() - (iMotion.getStartFrame())) * fps)
                    .append("ms\" ")
                    .append("attributeName=\"").append(y).append("\" from=\"")
                    .append(iMotion.getStartY()).append("\" ")
                    .append("to=\"").append(iMotion.getEndY()).append("\" fill=\"freeze\" />\n");
          }
        }

        else if (iMotion instanceof Scaling) {
          if (iMotion.getEndWidth() != iMotion.getStartWidth()) {
            outputString.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(iMotion.getStartFrame() * fps).append("ms\" dur=\"")
                    .append((iMotion.getEndFrame() - (iMotion.getStartFrame())) * fps)
                    .append("ms\" ")
                    .append("attributeName=\"").append(width).append("\" from=\"")
                    .append(iMotion.getStartWidth())
                    .append("\" ").append("to=\"").append(iMotion.getEndWidth())
                    .append("\" fill=\"freeze\" />\n");
          }
          if (iMotion.getEndHeight() != iMotion.getStartHeight()) {
            outputString.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(iMotion.getStartFrame() * fps).append("ms\" dur=\"")
                    .append((iMotion.getEndFrame() - (iMotion.getStartFrame())) * fps)
                    .append("ms\" ")
                    .append("attributeName=\"").append(height).append("\" from=\"")
                    .append(iMotion.getStartHeight())
                    .append("\" ").append("to=\"").append(iMotion.getEndHeight())
                    .append("\" fill=\"freeze\" />\n");
          }
        }

        else if (iMotion instanceof ChangeColor) {
          outputString
                  .append("    <animate attributeName=\"fill\" attributeType=\"CSS\" from=\"rgb(")
                  .append(iMotion.getStartR()).append(",").append(iMotion.getStartG()).append(",")
                  .append(iMotion.getStartB()).append(")\" to=\"rgb(")
                  .append(iMotion.getEndR()).append(",").append(iMotion.getEndG()).append(",")
                  .append(iMotion.getEndB()).append(")\" begin=\"")
                  .append(iMotion.getStartFrame() * fps).append("ms\" dur=\"")
                  .append((iMotion.getEndFrame() - (iMotion.getStartFrame())) * fps)
                  .append("ms\" ")
                  .append(" fill=\"freeze\" />\n");
        }
      }
      outputString.append("</").append(shapeType).append(">\n\n");
    }
    outputString.append("</svg>");
    return outputString.toString();
  }
}

