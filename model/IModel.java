package cs5004.animator.model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * This interface represents the operations offered by the Easy Animator Model
 * object. One object of this model potentially represents an animated canvas.
 */
public interface IModel {

  /**
   * This method adds a shape to the current model.
   *
   * @param id the name of the shape to be added.
   * @param type the type of shape to be added, the model currently supports rectangles,
   *             simple ellipses and polygons.
   *             Polygons must be added with the addComplicatedShape method.
   * @param x the horizontal position of the shape upon an x/y plane
   * @param y the vertical position of the shape upon an x/y plane
   * @param width the width of the shape desired
   * @param height the height of the shape desired
   * @param frameIn the frame number at which the shape appears
   * @throws IllegalArgumentException if the shape is not possible
   *              (ie. already exists, invalid parameters)
   */
  void addShape(String id, ShapeType type, int x, int y, int width, int height,
                int r, int g, int b, int frameIn)
          throws IllegalArgumentException;

  /**
   * This method adds a complicated shape to the current model.
   * Currently only supports polygons.
   *
   * @param id the name of the shape to be added.
   * @param vertexList a list of the shapes vertices, in the form of model.CartesianPoint objects.
   *                   The first should be the shape's origin point, the following points should be
   *                   in clockwise order.
   * @param x the horizontal position of the shape upon an x/y plane
   * @param y the vertical position of the shape upon an x/y plane
   * @param width the width of the shape desired
   * @param height the height of the shape desired
   * @param frameIn the frame number at which the shape appears
   * @throws IllegalArgumentException if the shape is not possible
   *                    (ie. already exists, invalid parameters)
   */
  void addComplicatedShape(String id, List<CartesianPoint> vertexList,
                           int x, int y, int width, int height, int frameIn)
          throws IllegalArgumentException;

  /**
   * This method removes a shape from the scene at the given frame.
   *
   * @param id the name of the shape to be removed.
   * @param frameOut the frame number at which the shape disappears
   * @throws IllegalArgumentException if the shape can't be removed (ie. doesn't exists)
   */
  void updateShapeFrameOut(String id, int frameOut) throws IllegalArgumentException;

  /**
   * This method completely removes a shape and all associated motions from the model.
   *
   * @param id the name of the shape to be removed.
   * @throws IllegalArgumentException if the shape can't be removed (ie. doesn't exists)
   */
  void removeShape(String id) throws IllegalArgumentException;

  /**
   * This method adds a translation motion to the current model.
   * A translation is a form of transformation moving a shape from one location to another.
   *
   * @param id the name of the shape to be translated.
   * @param endingX the ending x-coordinate of the translation.
   * @param endingY the ending y-coordinate of the translation.
   * @param sFrame the starting frame of the translation.
   * @param eFrame the ending frame of the translation.
   * @throws IllegalArgumentException if the translation is not possible
   *                    (ie. already exists, overlapping frames, shape doesnt exist)
   */
  void addTranslation(String id, int endingX, int endingY, int sFrame, int eFrame)
          throws IllegalArgumentException;

  /**
   * This method adds a scaling motion to the current model.
   * model.Scaling is a form of transformation changing the dimensions of a shape.
   *
   * @param id the name of the shape to be translated.
   * @param endingWidth the resultant width of the scaling.
   * @param endingHeight the resultant height of the scaling.
   * @param sFrame the starting frame of the scaling.
   * @param eFrame the ending frame of the scaling.
   * @throws IllegalArgumentException if the scaling is not possible
   *                    (ie. already exists, overlapping frames, shape doesn't exist)
   */
  void addScaling(String id, int endingWidth, int endingHeight, int sFrame, int eFrame)
          throws IllegalArgumentException;

  /**
   * This method adds a model.ChangeColor motion to the current model.
   * model.ChangeColor is a form of transformation changing the color of a shape.
   * The shapes color will be a set of 3 integer values (each from 0-255) representing an RGB value.
   * Shapes will be (0, 0, 0) by default.
   *
   * @param id the name of the shape to be translated.
   * @param endingR the resultant red value of the shape.
   * @param endingG the resultant green value of the shape.
   * @param endingB the resultant blue value of the shape.
   * @param sFrame the starting frame of the scaling.
   * @param eFrame the ending frame of the scaling.
   * @throws IllegalArgumentException if the scaling is not possible
   *                    (ie. already exists, overlapping frames, shape doesn't exist)
   */
  void addChangeColor(String id, int endingR, int endingG, int endingB, int sFrame, int eFrame)
          throws IllegalArgumentException;


  String getScene();

  void setCanvasW(int width);

  void setCanvasH(int height);

  int getCanvasW();

  int getCanvasH();

  void setTopLeftX(int x);

  void setTopLeftY(int y);

  int getTopLeftX();

  int getTopLeftY();

  void setLastFrame(int latestFrame);

  int getLastFrame();

  //Ended up not working out
  //List<IShape> getAllShapesAtFrame(int frame);

  LinkedHashMap<String, IShape> getCreatedShapes();

  LinkedHashMap<String, List<IMotion>> getCreatedMotions();

  List<List<IShape>> getFrameByFrame();
}

