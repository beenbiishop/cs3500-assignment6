package view.panels;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenubarPanel extends JMenuBar {

  public MenubarPanel() {
    super();

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

    // Add to menu bar
    this.add(fileMenu);
    this.add(helpMenu);
  }

}
