package view.panels;

import javax.swing.JButton;
import javax.swing.JList;
import view.ImageProcessorGui;

/**
 * Represents the transformation panel that is a part of the {@link ImageProcessorGui} view.
 */
public class TransformationPanel extends JList<String> {

  JButton transformButton;
  JList<String> list;

  /**
   * Constructs a new transformation panel.
   */
  public TransformationPanel() {
    super();
  }

  public void addTransformation(String key, String label) {
    if (key == null || key.length() == 0) {
      throw new IllegalArgumentException("Key cannot be null or empty");
    }
    if (label == null || label.length() == 0) {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }

  }
}
