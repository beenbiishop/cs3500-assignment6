package view.panels;

import controller.ImageProcessorGuiController;
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

  private final JList<String> list = new JList<String>();

  /**
   * Constructs a new transformation panel.
   */
  public TransformationPanel(ImageProcessorGuiController controller) {
    super(new BorderLayout());
    this.add(new JScrollPane(this.list), BorderLayout.CENTER);
    JButton transformButton = new JButton("Apply Transformation");
    this.add(transformButton, BorderLayout.SOUTH);
    transformButton.addActionListener(
        evt -> controller.transformImage(this.getSelectedTransformation()));
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

  /**
   * Returns the selected transformation.
   *
   * @return the selected transformation
   */
  private String getSelectedTransformation() {
    return this.list.getSelectedValue();
  }

}
