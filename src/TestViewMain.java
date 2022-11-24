import controller.ImageFileHandler;
import controller.ImageIOHandler;
import controller.ImageProcessorGuiController;
import controller.ImageProcessorGuiControllerImpl;
import java.awt.image.BufferedImage;
import model.Image;
import model.ImageUtils;
import view.ImageProcessorGui;
import view.ImageProcessorGuiImpl;

public class TestViewMain {

  public static void main(String[] args) {
    ImageProcessorGuiController controller = new ImageProcessorGuiControllerImpl();
    ImageFileHandler fileHandler = new ImageIOHandler();
    Image testImg1 = fileHandler.process("res/Manhattan.png");
    Image testImg2 = fileHandler.process("res/Koala.png");
    int[][] testImg1Data = ImageUtils.getChannelFrequencies(testImg1);
    int[][] testImg2Data = ImageUtils.getChannelFrequencies(testImg2);
    BufferedImage testImg1Buff = ImageUtils.getBufferedImage(testImg1);
    BufferedImage testImg2Buff = ImageUtils.getBufferedImage(testImg2);
    ImageProcessorGui view = new ImageProcessorGuiImpl(controller);
    String[] testImg1DataStr = new String[]{"visualize-red", "visualize-blue", "visualize-green",
        "visualize-luminance", "visualize-saturation"};
    view.setTransformations(testImg1DataStr);
    view.displayImage("manhattan", testImg1Buff, testImg1Data);
    view.displayImage("koala", testImg2Buff, testImg2Data);
  }

}
