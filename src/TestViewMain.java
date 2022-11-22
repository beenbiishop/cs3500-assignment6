import controller.ImageProcessorGuiController;
import controller.ImageProcessorGuiControllerImpl;
import model.StoredImages;
import model.StoredImagesImpl;
import view.ImageProcessorGui;
import view.ImageProcessorGuiImpl;

public class TestViewMain {

  public static void main(String[] args) {

    StoredImages model = new StoredImagesImpl();
    ImageProcessorGuiController controller = new ImageProcessorGuiControllerImpl(model);
    ImageProcessorGui view = new ImageProcessorGuiImpl();
    controller.setView(view);
  }

}
