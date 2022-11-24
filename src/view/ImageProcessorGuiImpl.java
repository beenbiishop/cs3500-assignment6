package view;

import controller.ImageProcessorGuiController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.panels.HistogramPanel;
import view.panels.MenubarPanel;
import view.panels.MessagePanel;
import view.panels.PreviewPanel;
import view.panels.TransformationPanel;

/**
 * Represents an implementation of the {@link ImageProcessorGui} interface.
 */
public class ImageProcessorGuiImpl implements ImageProcessorGui {

  private final Map<String, int[][]> histograms = new HashMap<>();
  private final Map<String, BufferedImage> images = new HashMap<>();
  private final JFrame frame;
  private final ImageProcessorGuiController controller;
  private final PreviewPanel previewPanel;
  private final TransformationPanel transformationPanel;
  private final HistogramPanel histogramPanel = new HistogramPanel();
  private final MessagePanel messagePanel = new MessagePanel();

  public ImageProcessorGuiImpl(ImageProcessorGuiController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.controller = controller;

    // Initialize the frame
    this.frame = new JFrame("Image Processor");
    this.frame.setLayout(new BorderLayout(5, 5));
    this.frame.setPreferredSize(new Dimension(800, 600));
    this.frame.setMinimumSize(new Dimension(800, 600));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add panels to the frame
    this.frame.setJMenuBar(new MenubarPanel(this.controller));
    this.previewPanel = new PreviewPanel();
    this.previewPanel.addChangeListener(evt -> this.changeHistogram());
    this.frame.add(this.previewPanel, BorderLayout.CENTER);
    this.frame.add(this.messagePanel, BorderLayout.SOUTH);
    this.transformationPanel = new TransformationPanel(this.controller);
    initSidebar();

    // Display the frame
    this.frame.pack();
    this.frame.setVisible(true);
  }


  @Override
  public void displayImage(String name, BufferedImage image, int[][] histogram) {
    if (name == null || image == null || histogram == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.images.put(name, image);
    this.histograms.put(name, histogram);
    this.previewPanel.addImageTab(name, image);
    this.previewPanel.displayImageTab(name);
    this.histogramPanel.updateHistogram(histogram);
  }

  @Override
  public boolean removeImage(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (this.images.containsKey(name)) {
      this.images.remove(name);
      this.histograms.remove(name);
      this.previewPanel.removeImageTab(name);
      return true;
    } else {
      return false;
    }
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
    this.messagePanel.updateMessage(message);
  }

  /**
   * Initializes the sidebar with the transformation panel and the histogram panel.
   */
  private void initSidebar() {
    JPanel sidebarPanel = new JPanel(new GridLayout(2, 0));
    this.transformationPanel.setBorder(BorderFactory.createTitledBorder("Transformations"));
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    sidebarPanel.add(this.transformationPanel);
    sidebarPanel.add(this.histogramPanel);
    sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
    this.frame.add(sidebarPanel, BorderLayout.EAST);
  }

  /**
   * Changes the histogram panel to be the current one.
   */
  private void changeHistogram() {
    this.histogramPanel.updateHistogram(
        this.histograms.get(this.previewPanel.getSelectedImageTab()));
  }

  @Override
  public void setTransformations(String[] transformations) {
    this.transformationPanel.setTransformations(transformations);
    this.frame.repaint();
  }

  @Override
  public String getCurrentImageName() {
    return this.previewPanel.getSelectedImageTab();
  }
}
