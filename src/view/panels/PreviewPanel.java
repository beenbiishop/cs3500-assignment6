package view.panels;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import view.ImageProcessorGui;

/**
 * Represents the image preview panel that is a part of the {@link ImageProcessorGui} view.
 */
public class PreviewPanel extends JTabbedPane {

  /**
   * Constructs a new image preview panel.
   */
  public PreviewPanel() {
    super();
  }

  /**
   * Displays the given image in the preview panel.
   *
   * @param title the title to display on the tab
   * @param image the image to be displayed
   */
  public void addImageTab(String title, BufferedImage image) {
    JLabel label = new JLabel();
    label.setVerticalAlignment(JLabel.TOP);
    label.setHorizontalAlignment(JLabel.LEFT);
    label.setIcon(new ImageIcon(image));
    JScrollPane scrollPane = new JScrollPane(label);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    this.addTab(title, scrollPane);
  }

  /**
   * Removes the image tab with the given title.
   *
   * @param title the title of the tab to remove
   */
  public void removeImageTab(String title) {
    this.removeTabAt(this.indexOfTab(title));
  }

  /**
   * Selects the image tab with the given title.
   *
   * @param title the title of the tab to select
   */
  public void displayImageTab(String title) {
    this.setSelectedIndex(this.indexOfTab(title));
  }
}
