import controller.ImageProcessorGuiController;
import controller.ImageProcessorGuiControllerImpl;
import model.StoredImages;
import model.StoredImagesImpl;
import view.ImageProcessorGui;
import view.ImageProcessorGuiImpl;

/**
 * Represents the main class for the Image Processor program. It creates the model, view, and
 * controller for the program.
 */
public class GuiMain {

  /**
   * Main method for the Image Processor program. It creates the model, view, and controller for the
   * program.
   *
   * @param args
   */
  public static void main(String[] args) {
    StoredImages store = new StoredImagesImpl();
    ImageProcessorGuiController controller = new ImageProcessorGuiControllerImpl(store);
    ImageProcessorGui view = new ImageProcessorGuiImpl();
    controller.setView(view);
    String[] transformations = new String[14];
    transformations[0] = "Blur";
    transformations[1] = "Brighten";
    transformations[2] = "Darken";
    transformations[3] = "Greyscale";
    transformations[4] = "Horizontal Flip";
    transformations[5] = "Vertical Flip";
    transformations[6] = "Sepia";
    transformations[7] = "Sharpen";
    transformations[8] = "Visualize Red";
    transformations[9] = "Visualize Green";
    transformations[10] = "Visualize Blue";
    transformations[11] = "Visualize Value";
    transformations[12] = "Visualize Intensity";
    transformations[13] = "Visualize Luma";
    view.setTransformations(transformations);
  }
}