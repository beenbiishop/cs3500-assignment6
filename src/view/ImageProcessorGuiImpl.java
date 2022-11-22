package view;

import controller.ImageProcessorGuiController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import view.panels.HistogramPanel;
import view.panels.MessagePanel;
import view.panels.PreviewPanel;
import view.panels.TransformationPanel;

public class ImageProcessorGuiImpl implements ImageProcessorGui {

  private JFrame frame;
  private PreviewPanel previewPanel;
  private TransformationPanel transformationPanel;
  private HistogramPanel histogramPanel;
  private MessagePanel messagePanel;

  public ImageProcessorGuiImpl() {
    this.frame = new JFrame("Image Processor");
    this.frame.setLayout(new BorderLayout(5, 5));
    this.frame.setPreferredSize(new Dimension(800, 600));
    this.frame.setMinimumSize(new Dimension(800, 600));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void addFeatures(ImageProcessorGuiController features) {

  }

  @Override
  public void renderMessage(String message) {

  }

  public void displayImage(String name, BufferedImage image) {

  }

  public void removeImage() {

  }

  public void renderDialog() {

  }

  public void renderInput() {

  }

  public String pickFile() {
    return "";
  }

}
