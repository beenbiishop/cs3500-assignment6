package view.panels;

import javax.swing.*;

public class MessagePanel extends JPanel {
  static JFrame frame;
  static JButton button1;
  static JLabel label;

  public static void main(String[] args) {
    frame = new JFrame("Message Panel");
    label = new JLabel("Message Label");

    frame.setSize(100, 300);
    frame.show();

  }

}
