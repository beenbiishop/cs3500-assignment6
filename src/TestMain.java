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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.CompoundBorder;
import model.Image;
import model.ImageUtils;

public class TestMain extends JFrame {

  public TestMain() {
    super("Layout Test");
    setLayout(new BorderLayout(5, 5));

    initMenu();
    initImagePanel();
    initSidebar();
    initMessage();

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
    JMenuItem removeItem = new JMenuItem("Remove");
    removeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_DOWN_MASK));
    fileMenu.add(removeItem);
    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_DOWN_MASK));
    fileMenu.add(quitItem);
    menuBar.add(fileMenu);

    JMenu helpMenu = new JMenu("Help");
    helpMenu.add(new JMenuItem("Loading/Saving Images"));
    helpMenu.add(new JMenuItem("Applying Transformations/Filters"));
    helpMenu.addSeparator();
    helpMenu.add(new JMenuItem("About Image Processor"));
    menuBar.add(helpMenu);

    add(menuBar, BorderLayout.NORTH);
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

    label1.setIcon(new ImageIcon(ImageUtils.getBufferedImage(image1)));
    label2.setIcon(new ImageIcon(ImageUtils.getBufferedImage(image2)));
    label3.setIcon(new ImageIcon(ImageUtils.getBufferedImage(image3)));

    JScrollPane panel1 = new JScrollPane(label1);
    JScrollPane panel2 = new JScrollPane(label2);
    JScrollPane panel3 = new JScrollPane(label3);

    imagePanel.addTab("color-squares", panel1);
    imagePanel.addTab("koala", panel2);
    imagePanel.addTab("manhattan", panel3);

    add(imagePanel, BorderLayout.CENTER);

  }

  private void initSidebar() {
    JPanel sidebar = new JPanel(new GridLayout(2, 0));
    JPanel transformations = new JPanel(new BorderLayout());
    transformations.setBorder(BorderFactory.createTitledBorder("Transformations"));
    JList<String> list = new JList<>(
        new String[]{"Brighten", "Darken", "Flip Horizontally", "Flip Vertically"});
    list.setPreferredSize(new Dimension(200, 100));
    transformations.add(new JScrollPane(list), BorderLayout.CENTER);
    JButton applyButton = new JButton("Apply Transformation");
    transformations.add(applyButton, BorderLayout.SOUTH);

    JPanel histogram = new JPanel();
    histogram.setBorder(BorderFactory.createTitledBorder("Histogram"));

    sidebar.add(transformations);
    sidebar.add(histogram);
    sidebar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
    add(sidebar, BorderLayout.EAST);
  }

  private void initMessage() {
    JPanel message = new JPanel(new BorderLayout());
    JLabel label = new JLabel("Message would go right here");
    label.setHorizontalAlignment(JLabel.LEFT);
    label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    message.add(label, BorderLayout.CENTER);
    message.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10),
        BorderFactory.createTitledBorder("Message")));
    add(message, BorderLayout.SOUTH);
  }
}
