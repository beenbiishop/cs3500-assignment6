package view.panels;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import view.ImageProcessorGui;

/**
 * Represents the transformation panel that is a part of the {@link ImageProcessorGui} view.
 */
public class TransformationPanel extends JPanel {

  private JButton transformButton = new JButton("Apply Transformation");
  private JList<String> list = new JList<String>();

  /**
   * Constructs a new transformation panel.
   */
  public TransformationPanel() {
    super(new BorderLayout());
    this.add(new JScrollPane(this.list), BorderLayout.CENTER);
    this.add(this.transformButton, BorderLayout.SOUTH);
  }

  /**
   * Sets the list of transformations to be available.
   *
   * @param transformations the transformations to be available
   */
  public void setTransformations(String[] transformations) {
    if (transformations == null) {
      throw new IllegalArgumentException("Transformations cannot be null");
    }
    for (String transformation : transformations) {
      if (transformation == null || transformation.length() == 0) {
        throw new IllegalArgumentException("Transformation cannot be null or empty");
      }
    }
    this.list.setListData(transformations);
    this.list.repaint();
    this.repaint();
  }

}
