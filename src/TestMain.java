import controller.ImageFileHandler;
import controller.ImageIOHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import model.Image;

public class TestMain extends JFrame {

  public TestMain() {
    super("Layout Test");
    setLayout(new BorderLayout(15, 20));

    initMenu();
    initImagePanel();
    initSidebar();

    setPreferredSize(new Dimension(800, 600));
    setMinimumSize(new Dimension(800, 600));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();

    setVisible(true);

//    setExtendedState(JFrame.MAXIMIZED_BOTH);
  }

  public static void main(String[] args) {
    new TestMain();
  }

  private void initMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    JMenuItem loadItem = new JMenuItem("Load");
    loadItem.setMnemonic(KeyEvent.VK_L);
    loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
    fileMenu.add(loadItem);
    JMenuItem saveItem = new JMenuItem("Save");
    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
    fileMenu.add(saveItem);
    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_DOWN_MASK));
    fileMenu.add(quitItem);
    menuBar.add(fileMenu);

    JMenu filterMenu = new JMenu("Filter");
    filterMenu.add(new JMenuItem("Blur"));
    filterMenu.add(new JMenuItem("Sharpen"));
    filterMenu.add(new JMenuItem("Sepia"));
    filterMenu.add(new JMenuItem("Greyscale"));
    JMenu visualizeSubmenu = new JMenu("Visualize Component");
    visualizeSubmenu.add(new JMenuItem("Red Channel"));
    visualizeSubmenu.add(new JMenuItem("Green Channel"));
    visualizeSubmenu.add(new JMenuItem("Blue Channel"));
    visualizeSubmenu.add(new JMenuItem("Value Channel"));
    visualizeSubmenu.add(new JMenuItem("Intensity Channel"));
    visualizeSubmenu.add(new JMenuItem("Luma Channel"));
    filterMenu.add(visualizeSubmenu);
    menuBar.add(filterMenu);

    JMenu transformMenu = new JMenu("Transform");
    transformMenu.add(new JMenuItem("Brighten"));
    transformMenu.add(new JMenuItem("Darken"));
    transformMenu.add(new JMenuItem("Flip Horizontally"));
    transformMenu.add(new JMenuItem("Flip Vertically"));
    menuBar.add(transformMenu);

    JMenu helpMenu = new JMenu("Help");
    helpMenu.add(new JMenuItem("Loading/Saving Images"));
    helpMenu.add(new JMenuItem("Applying Transformations/Filters"));
    helpMenu.addSeparator();
    helpMenu.add(new JMenuItem("About Image Processor"));
    menuBar.add(helpMenu);


  }

  private void initImagePanel() {
    JTabbedPane imagePanel = new JTabbedPane();
    ImageFileHandler handler = new ImageIOHandler();

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();

    label1.setBackground(new Color(45, 45, 45));
    label2.setBackground(new Color(45, 45, 45));
    label3.setBackground(new Color(45, 45, 45));

    label1.setVerticalAlignment(JLabel.TOP);
    label2.setVerticalAlignment(JLabel.TOP);
    label3.setVerticalAlignment(JLabel.TOP);

    Image image1 = handler.process("res/exImage2.png");
    Image image2 = handler.process("res/Koala.png");
    Image image3 = handler.process("res/Manhattan.png");

    label1.setIcon(new ImageIcon(image1.getBufferedImage()));
    label2.setIcon(new ImageIcon(image2.getBufferedImage()));
    label3.setIcon(new ImageIcon(image3.getBufferedImage()));

    JScrollPane panel1 = new JScrollPane(label1);
    JScrollPane panel2 = new JScrollPane(label2);
    JScrollPane panel3 = new JScrollPane(label3);

    imagePanel.addTab("color-squares", panel1);
    imagePanel.addTab("koala", panel2);
    imagePanel.addTab("manhattan", panel3);

    imagePanel.setTabComponentAt(imagePanel.indexOfTab("color-squares"), new ButtonTab(imagePanel));
    imagePanel.setTabComponentAt(imagePanel.indexOfTab("koala"), new ButtonTab(imagePanel));
    imagePanel.setTabComponentAt(imagePanel.indexOfTab("manhattan"), new ButtonTab(imagePanel));

    add(imagePanel, BorderLayout.CENTER);

  }

  private void initSidebar() {
    JPanel sidebar = new JPanel(new GridLayout(2, 0));
    JPanel transformations = new JPanel();

    transformations.setPreferredSize(new Dimension(200, 0));

    JPanel histogram = new JPanel();
    histogram.setBackground(Color.CYAN);
    histogram.setPreferredSize(new Dimension(200, 0));

    sidebar.add(transformations);
    sidebar.add(histogram);
    sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10));
    add(sidebar, BorderLayout.EAST);
  }
}
