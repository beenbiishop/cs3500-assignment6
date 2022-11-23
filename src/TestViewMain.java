import controller.ImageFileHandler;
import controller.ImageIOHandler;
import java.awt.image.BufferedImage;
import model.Image;
import model.ImageUtils;
import view.ImageProcessorGui;
import view.ImageProcessorGuiImpl;

public class TestViewMain {

  public static void main(String[] args) {
    ImageFileHandler fileHandler = new ImageIOHandler();
    Image testImg1 = fileHandler.process("res/Manhattan.png");
    int[][] testImg1Data = ImageUtils.getChannelFrequencies(testImg1);
    BufferedImage testImg1Buff = ImageUtils.getBufferedImage(testImg1);
    ImageProcessorGui view = new ImageProcessorGuiImpl();
    view.displayImage("testimg1", testImg1Buff, testImg1Data);
  }

}
