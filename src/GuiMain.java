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
  }
}