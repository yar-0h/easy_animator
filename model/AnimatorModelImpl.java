package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs5004.animator.util.AnimationBuilder;


/**
 * This is the implementation of a EasyAnimator model.IModel interface.
 */
public class AnimatorModelImpl implements IModel {
  private LinkedHashMap<String, IShape> createdShapes = new LinkedHashMap<>();
  private LinkedHashMap<String, List<IMotion>> createdMotions  = new LinkedHashMap<>();

  private LinkedHashMap<String, Scaling> scalingHistory  = new LinkedHashMap<>();
  private LinkedHashMap<String, Translation> translationHistory  = new LinkedHashMap<>();
  private LinkedHashMap<String, ChangeColor> changeColorHistory  = new LinkedHashMap<>();

  private int canvasW;
  private int canvasH;
  private int canvasX;
  private int canvasY;
  private int lastFrame;

  @Override
  public void addShape(String id, ShapeType type, int x, int y, int w, int h, int r, int g, int b,
                       int frameIn) {
    IShape shapeToBeAdded;
    if (createdShapes.get(id) == null) {
      switch (type) {
        case RECTANGLE:
          shapeToBeAdded = new Rectangle(x, y, w, h, r, g, b, frameIn);
          break;
        case ELLIPSE:
          shapeToBeAdded = new Ellipse(x, y, w, h, r, g, b, frameIn);
          break;
        default:
          shapeToBeAdded = null;
      }
      createdShapes.put(id, shapeToBeAdded);
    }
  }

  @Override
  public void addComplicatedShape(String id, List<CartesianPoint> vertexList,
                                  int x, int y, int w, int h, int frameIn) {
    IShape shapeToBeAdded;
    if (createdShapes.get(id) == null) {
      shapeToBeAdded = new Polygon(x, y, w, h, frameIn, vertexList);
      createdShapes.put(id, shapeToBeAdded);
    }
  }

  @Override
  public void updateShapeFrameOut(String id, int frameOut) {
    IShape shapeToBeRemoved = createdShapes.get(id);
    IShape removedShape;

    if (shapeToBeRemoved != null) {
      switch (shapeToBeRemoved.getType()) {
        case RECTANGLE:
          removedShape = new Rectangle(shapeToBeRemoved.getX(), shapeToBeRemoved.getY(),
                  shapeToBeRemoved.getW(), shapeToBeRemoved.getH(),
                  shapeToBeRemoved.getR(), shapeToBeRemoved.getG(), shapeToBeRemoved.getB(),
                  shapeToBeRemoved.getFirstAppearance(), frameOut);
          break;
        case ELLIPSE:
          removedShape = new Ellipse(shapeToBeRemoved.getX(), shapeToBeRemoved.getY(),
                  shapeToBeRemoved.getW(), shapeToBeRemoved.getH(),
                  shapeToBeRemoved.getR(), shapeToBeRemoved.getG(), shapeToBeRemoved.getB(),
                  shapeToBeRemoved.getFirstAppearance(), frameOut);
          break;
        case POLYGON:
          removedShape = new Polygon(shapeToBeRemoved.getX(), shapeToBeRemoved.getY(),
                  shapeToBeRemoved.getW(), shapeToBeRemoved.getH(),
                  shapeToBeRemoved.getR(), shapeToBeRemoved.getG(), shapeToBeRemoved.getB(),
                  shapeToBeRemoved.getFirstAppearance(), frameOut,
                  shapeToBeRemoved.getListOfVertices());
          break;
        default:
          removedShape = null;
      }
      createdShapes.replace(id, removedShape);
      setLastFrame(frameOut);
    }
  }

  @Override
  public void removeShape(String id) {
    createdShapes.remove(id);
    createdMotions.remove(id);
    scalingHistory.remove(id);
    translationHistory.remove(id);
    changeColorHistory.remove(id);
  }

  @Override
  public void addTranslation(String id, int endingX, int endingY, int sFrame, int eFrame) {
    IShape shapeToBeMorphed = createdShapes.get(id);
    if (!translationHistory.containsKey(id)) {
      Translation translationAddition = new Translation(shapeToBeMorphed, endingX, endingY,
              sFrame, eFrame);
      addMotion(id, translationAddition);
      translationHistory.put(id, translationAddition);
    }
    else {
      Translation previousTranslation = translationHistory.get(id);
      Translation translationAddition = new Translation(previousTranslation, endingX, endingY,
              sFrame, eFrame);
      addMotion(id, translationAddition);
      translationHistory.put(id, translationAddition);
    }
    setLastFrame(eFrame);
  }

  @Override
  public void addScaling(String id, int endingWidth, int endingHeight,
                              int sFrame, int eFrame) {
    IShape shapeToBeMorphed = createdShapes.get(id);
    if (!scalingHistory.containsKey(id)) {
      Scaling scalingAddition = new Scaling(shapeToBeMorphed, endingWidth, endingHeight,
              sFrame, eFrame);
      addMotion(id, scalingAddition);
      scalingHistory.put(id, scalingAddition);
    }
    else {
      Scaling previousScaling = scalingHistory.get(id);
      Scaling scalingAddition = new Scaling(previousScaling, endingWidth, endingHeight,
              sFrame, eFrame);
      addMotion(id, scalingAddition);
      scalingHistory.put(id, scalingAddition);
    }
    setLastFrame(eFrame);
  }

  @Override
  public void addChangeColor(String id, int endingR, int endingG, int endingB,
                             int sFrame, int eFrame) {
    IShape shapeToBeMorphed = createdShapes.get(id);
    if (!changeColorHistory.containsKey(id)) {
      ChangeColor changeColorAddition = new ChangeColor(shapeToBeMorphed, endingR, endingG, endingB,
              sFrame, eFrame);
      addMotion(id, changeColorAddition);
      changeColorHistory.put(id, changeColorAddition);
    }
    else {
      ChangeColor previousChangeColor = changeColorHistory.get(id);
      ChangeColor changeColorAddition = new ChangeColor(previousChangeColor,
              endingR, endingG, endingB, sFrame, eFrame);
      addMotion(id, changeColorAddition);
      changeColorHistory.put(id, changeColorAddition);
    }

    setLastFrame(eFrame);
  }

  private void addMotion(String id, IMotion motion) {
    IShape shapeToBeMorphed = createdShapes.get(id);
    List<IMotion> shapesActions = createdMotions.get(id);
    if (shapeToBeMorphed != null) {
      if (shapesActions == null) {
        shapesActions = new ArrayList<>();
        shapesActions.add(motion);
        createdMotions.put(id, shapesActions);
      }
      else {
        if (!shapesActions.contains(motion)) {
          int i = 0;
          while (i < shapesActions.size()) {
            IMotion motionComparison = shapesActions.get(i);
            if (motionComparison.getClass() == motion.getClass()) {
              if (motion.getStartFrame() > motionComparison.getStartFrame()
                      && motion.getStartFrame() < motionComparison.getEndFrame()) {
                throw new IllegalArgumentException("error: overlapping transformations\n");
              }
              else if (motion.getStartFrame() < motionComparison.getStartFrame()) {
                shapesActions.add(i, motion);
                return;
              }
              else {
                i++;
              }
            }
            else {
              i++;
            }
          }
          shapesActions.add(motion);
        }
      }
    }
  }

  @Override
  public String getScene() {
    StringBuilder outputString = new StringBuilder("Shapes:\n");
    createdShapes.forEach((id,shape) ->
            outputString.append("Name: ").append(id).append("\n").append(shape.toString()));

    for (String key : createdMotions.keySet()) {
      List<IMotion> value = createdMotions.get(key);
      for (IMotion iMotion : value) {
        outputString.append("Shape ").append(key).append(iMotion.toString());
      }
    }

    return outputString.toString();
  }

  @Override
  public List<List<IShape>> getFrameByFrame() {
    List<List<IShape>> frameByFrame = new LinkedList<>();
    LinkedHashMap<String, IShape> previousFrame = new LinkedHashMap<>();

    //for every frame of animation
    for (int i = 1; i < this.lastFrame + 1; i++) {
      LinkedHashMap<String, IShape> currentFrameMap = new LinkedHashMap<>();
      List<IShape> currentFrame = new LinkedList<>();

      Set<String> keys = createdMotions.keySet();
      //for every shape with motions
      for (String k : keys) {
        if ((createdShapes.get(k).getFirstAppearance() <= i)
                && (createdShapes.get(k).getLastAppearance() >= i)) {

          ShapeType typeOfShape;
          double shapeX;
          double shapeY;

          double shapeW;
          double shapeH;

          double shapeR;
          double shapeG;
          double shapeB;

          //check if shape has previously appeared
          //if not, get info from shapelist
          if (!previousFrame.containsKey(k)) {
            typeOfShape = this.createdShapes.get(k).getType();
            shapeX = this.createdShapes.get(k).getX();
            shapeY = this.createdShapes.get(k).getY();

            shapeW = this.createdShapes.get(k).getW();
            shapeH = this.createdShapes.get(k).getH();

            shapeR = this.createdShapes.get(k).getR();
            shapeG = this.createdShapes.get(k).getG();
            shapeB = this.createdShapes.get(k).getB();
          }
          //if so, get it from the previous frame
          else {
            typeOfShape = previousFrame.get(k).getType();
            shapeX = previousFrame.get(k).getX();
            shapeY = previousFrame.get(k).getY();

            shapeW = previousFrame.get(k).getW();
            shapeH = previousFrame.get(k).getH();

            shapeR = previousFrame.get(k).getR();
            shapeG = previousFrame.get(k).getG();
            shapeB = previousFrame.get(k).getB();
          }

          //iterate through every established motion
          for (int j = 0; j < createdMotions.get(k).size(); j++) {
            //check if motion exists during frame
            if ((createdMotions.get(k).get(j).getStartFrame() <= i)
                    && (createdMotions.get(k).get(j).getEndFrame() >= i)) {

              double stNum = (createdMotions.get(k).get(j).getEndFrame() - i);
              double den = (createdMotions.get(k).get(j).getEndFrame()
                      - createdMotions.get(k).get(j).getStartFrame());

              int endNum = (i - createdMotions.get(k).get(j).getStartFrame());

              if ((createdMotions.get(k).get(j) instanceof Translation)) {
                shapeX = Math.round((createdMotions.get(k).get(j).getStartX() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndX() * (endNum / den)));

                shapeY = Math.round((createdMotions.get(k).get(j).getStartY() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndY() * (endNum / den)));
              }

              else if ((createdMotions.get(k).get(j) instanceof Scaling)) {
                shapeW = Math.round((createdMotions.get(k).get(j).getStartWidth() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndWidth() * (endNum / den)));

                shapeH = Math.round((createdMotions.get(k).get(j).getStartHeight() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndHeight() * (endNum / den)));
              }

              else if ((createdMotions.get(k).get(j) instanceof ChangeColor)) {

                shapeR = Math.round((createdMotions.get(k).get(j).getStartR() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndR() * (endNum / den)));

                shapeG = Math.round((createdMotions.get(k).get(j).getStartG() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndG() * (endNum / den)));

                shapeB = Math.round((createdMotions.get(k).get(j).getStartB() * (stNum / den))
                        + (createdMotions.get(k).get(j).getEndB() * (endNum / den)));
              }
            }

            if (shapeX < 0) {
              shapeX = 0;
            }
            if (shapeY < 0) {
              shapeY = 0;
            }

            if (shapeW < 0) {
              shapeW = 0;
            }
            if (shapeH < 0) {
              shapeH = 0;
            }

            if (shapeR < 0) {
              shapeR = 0;
            }
            if (shapeG < 0) {
              shapeG = 0;
            }
            if (shapeB < 0) {
              shapeB = 0;
            }

            if (shapeR > 255) {
              shapeR = 255;
            }
            if (shapeG > 255) {
              shapeG = 255;
            }
            if (shapeB > 255) {
              shapeB = 255;
            }
          }

          IShape newShape = null;
          if (typeOfShape == ShapeType.RECTANGLE) {
            newShape = new Rectangle((int)shapeX,
                    (int)shapeY,
                    (int)shapeW,
                    (int)shapeH,
                    (int)shapeR,
                    (int)shapeG,
                    (int)shapeB, i);
          }

          else if (typeOfShape == ShapeType.ELLIPSE) {
            newShape = new Ellipse((int)shapeX,
                    (int)shapeY,
                    (int)shapeW,
                    (int)shapeH,
                    (int)shapeR,
                    (int)shapeG,
                    (int)shapeB, i);
          }
          currentFrameMap.put(k, newShape);
          currentFrame.add(newShape);
        }
      }
      frameByFrame.add(currentFrame);
      previousFrame = currentFrameMap;
    }
    return frameByFrame;
  }


  //A FAILED VERSION OF GETFRAMEBYFRAME METHOD
  //  @Override
  //  public List<IShape> getAllShapesAtFrame(int frame) {
  //    List<IShape> frameOutput = new ArrayList<>();
  //
  //    for (Map.Entry<String, List<IMotion>> entry : createdMotions.entrySet()) {
  //      for (int i = 0; i < entry.getValue().size(); i++) {
  //        //make sure shape exists at frame
  //        if ((entry.getValue().get(i).getStartFrame() <= frame)
  //                && (entry.getValue().get(i).getEndFrame() >= frame)) {
  //          if (entry.getValue().get(i) instanceof Translation);
  //        }
  //      }
  //    }
  //
  //    int frameShapeX;
  //    int frameShapeY;
  //    int totalXChange;
  //    int totalYChange;
  //
  //    int xChangePerFrame;
  //    int yChangePerFrame;
  //
  //    int frameShapeWidth;
  //    int frameShapeHeight;
  //    int totalWidthChange;
  //    int totalHeightChange;
  //
  //    int heightChangePerFrame;
  //    int widthChangePerFrame;
  //
  //    int frameShapeRed;
  //    int frameShapeGreen;
  //    int frameShapeBlue;
  //    int totalRedChange;
  //    int totalGreenChange;
  //    int totalBlueChange;
  //
  //    int redChangePerFrame;
  //    int greenChangePerFrame;
  //    int blueChangePerFrame;
  //
  //    int lengthOfChange;
  //
  //    IShape shapeCheck;
  //    IShape shapeToBeAdded;
  //    // creating iterator object to iterate through list of created shapes
  //
  //    // while there are shapes in the hashmap:
  //    for (Map.Entry<String, IShape> entry : createdShapes.entrySet()) {
  //
  //      shapeCheck = entry.getValue();
  //
  //      // copy the shape's original properties
  //      frameShapeX = shapeCheck.getX();
  //      frameShapeY = shapeCheck.getY();
  //      frameShapeWidth = shapeCheck.getW();
  //      frameShapeHeight = shapeCheck.getH();
  //      frameShapeRed = shapeCheck.getR();
  //      frameShapeGreen = shapeCheck.getG();
  //      frameShapeBlue = shapeCheck.getB();
  //
  //      // if the shape exists during that frame
  //      if (shapeCheck.getFirstAppearance() <= frame
  //              && ((shapeCheck.getLastAppearance() >= frame)
  //              || (shapeCheck.getLastAppearance() == -1))) {
  //        // and has been morphed in any way
  //        if (createdMotions.containsKey(entry.getKey())) {
  //          List<IMotion> shapeVersions = createdMotions.get(entry.getKey());
  //          for (IMotion iMotion : shapeVersions) {
  //            // check if any of these morphs happen during this fram
  //            if (iMotion.getStartFrame() <= frame
  //                    && iMotion.getEndFrame() >= frame) {
  //              lengthOfChange = (iMotion.getEndFrame() - iMotion.getStartFrame());
  //              if (iMotion instanceof Translation) {
  //                totalXChange = iMotion.getEndX() - iMotion.getStartX();
  //                totalYChange = iMotion.getEndY() - iMotion.getStartY();
  //                xChangePerFrame = totalXChange / lengthOfChange;
  //                yChangePerFrame = totalYChange / lengthOfChange;
  //                frameShapeX = ((frame - iMotion.getStartFrame()) * (xChangePerFrame))
  //                        + iMotion.getStartX();
  //                frameShapeY = ((frame - iMotion.getStartFrame()) * (yChangePerFrame))
  //                        + iMotion.getStartY();
  //              } else if (iMotion instanceof Scaling) {
  //                totalWidthChange = iMotion.getEndWidth() - iMotion.getStartWidth();
  //                totalHeightChange = iMotion.getEndHeight() - iMotion.getStartHeight();
  //                widthChangePerFrame = totalWidthChange / lengthOfChange;
  //                heightChangePerFrame = totalHeightChange / lengthOfChange;
  //                frameShapeWidth = ((frame - iMotion.getStartFrame()) * (widthChangePerFrame))
  //                        + iMotion.getStartWidth();
  //                frameShapeHeight = ((frame - iMotion.getStartFrame()) * (heightChangePerFrame))
  //                        + iMotion.getStartHeight();
  //              } else if (iMotion instanceof ChangeColor) {
  //                totalRedChange = iMotion.getEndR() - iMotion.getStartR();
  //                totalGreenChange = iMotion.getEndG() - iMotion.getStartG();
  //                totalBlueChange = iMotion.getEndB() - iMotion.getStartB();
  //                redChangePerFrame = totalRedChange / lengthOfChange;
  //                greenChangePerFrame = totalGreenChange / lengthOfChange;
  //                blueChangePerFrame = totalBlueChange / lengthOfChange;
  //                frameShapeRed = ((frame - iMotion.getStartFrame()) * (redChangePerFrame))
  //                        + iMotion.getStartR();
  //                frameShapeGreen = ((frame - iMotion.getStartFrame()) * (greenChangePerFrame))
  //                        + iMotion.getStartG();
  //                frameShapeBlue = ((frame - iMotion.getStartFrame()) * (blueChangePerFrame))
  //                        + iMotion.getStartB();
  //              }
  //            }
  //          }
  //        }
  //      }
  //      // build shape and add to list
  //      switch (shapeCheck.getType()) {
  //        case RECTANGLE:
  //          shapeToBeAdded = new Rectangle(frameShapeX, frameShapeY,
  //                  frameShapeWidth, frameShapeHeight,
  //                  frameShapeRed, frameShapeGreen, frameShapeBlue,
  //                  frame);
  //          break;
  //
  //        case ELLIPSE:
  //          shapeToBeAdded = new Ellipse(frameShapeX, frameShapeY,
  //                  frameShapeWidth, frameShapeHeight,
  //                  frameShapeRed, frameShapeGreen, frameShapeBlue,
  //                  frame);
  //          break;
  //
  //        case POLYGON:
  //          shapeToBeAdded = new Polygon(frameShapeX, frameShapeY,
  //                  frameShapeWidth, frameShapeHeight,
  //                  frameShapeRed, frameShapeGreen, frameShapeBlue,
  //                  frame, shapeCheck.getListOfVertices());
  //          break;
  //
  //        default:
  //          shapeToBeAdded = null;
  //      }
  //      frameOutput.add(shapeToBeAdded);
  //    }
  //    return frameOutput;
  //  }

  @Override
  public LinkedHashMap<String, IShape> getCreatedShapes() {
    return this.createdShapes;
  }

  @Override
  public LinkedHashMap<String, List<IMotion>> getCreatedMotions() {
    return this.createdMotions;
  }

  /**
   * This is the implementation of an AnimationBuilder, used to build a model from parsed input.
   */
  public static final class Builder implements AnimationBuilder<IModel> {
    IModel model = new AnimatorModelImpl();
    Map<String, ShapeType> shapes = new LinkedHashMap<>();
    Map<String, List<ProtoMotion>> transformations = new HashMap<>();

    @Override
    public IModel build() {
      ShapeType newShapeType;
      int newShapeX;
      int newShapeY;
      int newShapeW;
      int newShapeH;
      int newShapeR;
      int newShapeG;
      int newShapeB;
      int newShapeIn;
      int newShapeOut;

      for (String key : shapes.keySet()) {
        newShapeType = shapes.get(key);
        newShapeX = transformations.get(key).get(0).getStartX();
        newShapeY = transformations.get(key).get(0).getStartY();

        newShapeW = transformations.get(key).get(0).getStartW();
        newShapeH = transformations.get(key).get(0).getStartH();

        newShapeR = transformations.get(key).get(0).getStartR();
        newShapeG = transformations.get(key).get(0).getStartG();
        newShapeB = transformations.get(key).get(0).getStartB();

        newShapeIn = transformations.get(key).get(0).getStart();
        newShapeOut = transformations.get(key).get(0).getEnd();


        model.addShape(key, newShapeType, newShapeX, newShapeY, newShapeW, newShapeH,
                newShapeR, newShapeG, newShapeB, newShapeIn);
        model.updateShapeFrameOut(key, newShapeOut);

        for (int i = 0; i < transformations.get(key).size(); i++) {
          //motion deemed to be a 'start' type of motion gets added similarly to a translation
          //to keep things from breaking.
          List<ProtoMotionType> protoList = transformations.get(key).get(i).getTypes();
          for (int j = 0; j < protoList.size(); j++) {
            if ((protoList.get(j) == ProtoMotionType.START) && (protoList.size() == 1)) {
              model.addTranslation(key,
                      transformations.get(key).get(i).getEndX(),
                      transformations.get(key).get(i).getEndY(),
                      transformations.get(key).get(i).getStart(),
                      transformations.get(key).get(i).getEnd());
            }

            else if (protoList.get(j) == ProtoMotionType.TRANSLATION) {
              model.addTranslation(key,
                      transformations.get(key).get(i).getEndX(),
                      transformations.get(key).get(i).getEndY(),
                      transformations.get(key).get(i).getStart(),
                      transformations.get(key).get(i).getEnd());
            }

            else if (protoList.get(j) == ProtoMotionType.SCALING) {
              model.addScaling(key,
                      transformations.get(key).get(i).getEndW(),
                      transformations.get(key).get(i).getEndH(),
                      transformations.get(key).get(i).getStart(),
                      transformations.get(key).get(i).getEnd());
            }

            else if (protoList.get(j) == ProtoMotionType.COLORCHANGE) {
              model.addChangeColor(key,
                      transformations.get(key).get(i).getEndR(),
                      transformations.get(key).get(i).getEndG(),
                      transformations.get(key).get(i).getEndB(),
                      transformations.get(key).get(i).getStart(),
                      transformations.get(key).get(i).getEnd());
            }
          }
          model.updateShapeFrameOut(key, transformations.get(key).get(i).getEnd());
        }
      }
      return model;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      this.model.setTopLeftX(x);
      this.model.setTopLeftY(y);
      this.model.setCanvasH(height);
      this.model.setCanvasW(width);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      ShapeType newShapeType = null;

      if (type.equalsIgnoreCase("rectangle")) {
        newShapeType = ShapeType.RECTANGLE;
      }
      else if (type.equalsIgnoreCase("ellipse")) {
        newShapeType = ShapeType.ELLIPSE;
      }
      shapes.put(name, newShapeType);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
      ///CHECK FOR SHAPE IN LIST OF DECLARED SHAPES TOO???
      if (!transformations.containsKey(name)) {
        List<ProtoMotion> protoList = new ArrayList<>();
        protoList.add(new ProtoMotion(t1, x1, y1, w1, h1, r1, g1, b1,
                t2, x2, y2, w2, h2, r2, g2, b2));
        transformations.put(name, protoList);
      }

      else {
        transformations.get(name).add(new ProtoMotion(t1, x1, y1, w1, h1, r1, g1, b1,
                t2, x2, y2, w2, h2, r2, g2, b2));
      }

      return this;
    }
  }

  public void setCanvasW(int width) {
    this.canvasW = width;
  }

  public int getCanvasW() {
    return this.canvasW;
  }

  public void setCanvasH(int height) {
    this.canvasH = height;
  }

  public int getCanvasH() {
    return this.canvasH;
  }

  @Override
  public void setTopLeftX(int x) {
    this.canvasX = x;
  }

  @Override
  public void setTopLeftY(int y) {
    this.canvasY = y;
  }

  @Override
  public int getTopLeftX() {
    return this.canvasX;
  }

  @Override
  public int getTopLeftY() {
    return this.canvasY;
  }

  @Override
  public void setLastFrame(int latestFrame) {
    if (latestFrame > this.lastFrame) {
      this.lastFrame = latestFrame;
    }

  }

  @Override
  public int getLastFrame() {
    return this.lastFrame;
  }
}

