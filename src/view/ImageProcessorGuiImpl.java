package view;

import controller.ImageProcessorGuiController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
  private JMenuBar menuBar;
  private PreviewPanel previewPanel;
  private JPanel sidebarPanel;
  private TransformationPanel transformationPanel;
  private JButton transformationButton;
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

  /**
   * Initializes the menu bar.
   */
  private void initMenu() {
    this.menuBar = new JMenuBar();

    // File Menu
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadItem = new JMenuItem("Load");
    loadItem.setMnemonic(KeyEvent.VK_L);
    loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
    fileMenu.add(loadItem);
    JMenuItem saveItem = new JMenuItem("Save");
    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
    fileMenu.add(saveItem);
    JMenuItem removeItem = new JMenuItem("Remove");
    removeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_DOWN_MASK));
    fileMenu.add(removeItem);
    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_DOWN_MASK));
    fileMenu.add(quitItem);

    // Help Menu
    JMenu helpMenu = new JMenu("Help");
    JMenuItem helpItem = new JMenuItem("How to Use");
    helpMenu.add(helpItem);
    JMenuItem aboutItem = new JMenuItem("About Image Processor");
    helpMenu.add(aboutItem);

    this.menuBar.add(fileMenu);
    this.menuBar.add(helpMenu);
    this.frame.setJMenuBar(this.menuBar);
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
    this.sidebarPanel = new JPanel(new GridLayout(2, 0));
    this.transformationPanel = new TransformationPanel();
    this.histogramPanel = new HistogramPanel();
    this.sidebarPanel.add(this.transformationPanel);
    this.sidebarPanel.add(this.histogramPanel);
  }

  /**
   * Initializes the message panel.
   */
  private void initMessagePanel() {
    this.messagePanel = new MessagePanel();
    this.frame.add(this.messagePanel, BorderLayout.SOUTH);
  }
}
