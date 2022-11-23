package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
    initMenu();
    initPreviewPanel();
    initSidebar();
    initMessagePanel();
    this.frame.pack();
    this.frame.setVisible(true);
  }


  @Override
  public void displayImage(String name, BufferedImage image, int[][] histogram) {
    this.previewPanel.addImageTab(name, image);
    this.histogramPanel.updateHistogram(histogram);
  }

  @Override
  public boolean removeImage(String name) {
    return this.previewPanel.removeImageTab(name);
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
   * Initializes the menu bar.
   */
  private void initMenu() {
    JMenuBar menuBar = new MenubarPanel();
    this.frame.setJMenuBar(menuBar);
  }

  /**
   * Initializes the preview panel.
   */
  private void initPreviewPanel() {
    this.previewPanel = new PreviewPanel();
    this.frame.add(this.previewPanel, BorderLayout.CENTER);
  }

  /**
   * Initializes the sidebar with the transformation panel and the histogram panel.
   */
  private void initSidebar() {
    JPanel sidebarPanel = new JPanel(new GridLayout(2, 0));
    this.transformationPanel = new TransformationPanel();
    this.transformationPanel.setBorder(BorderFactory.createTitledBorder("Transformations"));
    this.histogramPanel = new HistogramPanel();
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    sidebarPanel.add(this.transformationPanel);
    sidebarPanel.add(this.histogramPanel);
    sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
    this.frame.add(sidebarPanel, BorderLayout.EAST);
  }

  /**
   * Initializes the message panel.
   */
  private void initMessagePanel() {
    this.messagePanel = new MessagePanel();
    this.frame.add(this.messagePanel, BorderLayout.SOUTH);
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
