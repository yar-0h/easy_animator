package cs5004.animator.view;

/**
 * This class represents the operations offered by the ViewSelector.
 * The ViewSelector is used to create a specific View object.
 */
public class ViewSelector {

  /**
   * This method is used to create a specific View object according to the provided string.
   *
   * @param selection A string representing a view object type, supported types are 'text' and 'svg'
   */
  public IView selectView(String selection) {
    IView viewToBeAdded;
    switch (selection) {
      case "text":
        viewToBeAdded = new TextView();
        break;

      case "svg":
        viewToBeAdded = new SVGView();
        break;

      case "visual":
        viewToBeAdded = new VisualView();
        break;

      case "playback":
        viewToBeAdded = new CompositeView();
        break;

      default:
        viewToBeAdded = null;
    }
    return viewToBeAdded;
  }
}
