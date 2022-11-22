package view;

import controller.ImageProcessorGuiController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.panels.HistogramPanel;
import view.panels.MessagePanel;
import view.panels.PreviewPanel;
import view.panels.TransformationPanel;

/**
 * Represents an implementation of the {@link ImageProcessorGui} interface.
 */
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

  @Override
  public void addFeatures(ImageProcessorGuiController features) {

  }

  @Override
  public void displayImage(String name, BufferedImage image) {

  }

  @Override
  public boolean removeImage(String name) {
    return false;
  }

  @Override
  public void renderDialog(DialogType type, String message) {

  }

  @Override
  public String[] renderInput(List<String> questions, String error) {
    return new String[0];
  }

  @Override
  public String loadFile(FileNameExtensionFilter filter) {
    return null;
  }

  @Override
  public void saveFile(FileNameExtensionFilter filter) {

  }

  @Override
  public void renderMessage(String message) throws IllegalStateException {

  }
}
