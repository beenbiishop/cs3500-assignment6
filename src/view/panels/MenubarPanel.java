package view.panels;

import controller.ImageProcessorGuiController;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenubarPanel extends JMenuBar {

  private final JMenuItem loadItem;
  private final JMenuItem saveItem;
  private final JMenuItem quitItem;
  private ImageProcessorGuiController controller;

  public MenubarPanel() {
    super();

    // File Menu
    JMenu fileMenu = new JMenu("File");
    this.loadItem = new JMenuItem("Load");
    loadItem.setMnemonic(KeyEvent.VK_L);
    loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
    fileMenu.add(loadItem);
    this.saveItem = new JMenuItem("Save");
    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
    fileMenu.add(saveItem);
    this.quitItem = new JMenuItem("Quit");
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_DOWN_MASK));
    fileMenu.add(quitItem);

    // Add to menu bar
    this.add(fileMenu);
  }

  /**
   * Sets the controller for this panel.
   *
   * @param controller the controller for this panel
   */
  public void addFeatures(ImageProcessorGuiController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.controller = controller;
    loadItem.addActionListener(evt -> controller.loadImage());
    saveItem.addActionListener(evt -> controller.saveImage());
    quitItem.addActionListener(evt -> controller.quit());
  }

}
