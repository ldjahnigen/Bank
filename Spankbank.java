import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;


public class Spankbank {
  public static void main(String[] args) {
    //initialize frame
    JFrame frame = new JFrame();
    frame.setSize(1100, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    LoginPage loginpage = new LoginPage(frame);

  }
}

class LoginPage {
  public LoginPage(JFrame frame) {
    Factory f = new Factory();
    Font normal_font = new Font("Arial", Font.PLAIN, 18);
    Font title_font = new Font("Arial", Font.BOLD, 24);
    JPanel panel = new JPanel(new GridLayout(0, 2));
    panel.setBorder(BorderFactory.createEmptyBorder(250, 350, 350, 350));

    panel.add(f.genLabel("Spank Bank", title_font));
    panel.add(f.genLabel("", normal_font));

    panel.add(f.genLabel("Username: ", normal_font));
    JTextField username_field = f.genTfield(10);
    panel.add(username_field);

    panel.add(f.genLabel("Password: ", normal_font));
    JPasswordField password_field = f.genPfield(10);
    panel.add(password_field);

    panel.add(f.genButton("Create Account", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = username_field.getText(); 
        char[] passwordc = password_field.getPassword();
        String password = new String(passwordc);

        username_field.setText("");
        password_field.setText("");

        System.out.println(username + password);
      }
    }));

    panel.add(f.genButton("Log in", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = username_field.getText(); 
        char[] passwordc = password_field.getPassword();
        String password = new String(passwordc);

        username_field.setText("");
        password_field.setText("");

        System.out.println(username + password);
      }
    }));
    frame.add(panel, BorderLayout.CENTER);
  }
}

class Factory {
  public JLabel genLabel(String text, Font font) {
    JLabel label = new JLabel(text);
    label.setFont(font);
    return label;
  }

  public JButton genButton(String text, ActionListener actionlistener) {
    JButton button = new JButton(text);
    button.addActionListener(actionlistener);
    return button;
  }

  public JTextField genTfield(int columns) {
    JTextField field = new JTextField(null, columns);
    return field;
  }

  public JPasswordField genPfield(int columns) {
    JPasswordField field = new JPasswordField(null, columns);
    return field;
  }
}

class Account {
  String username;
  String password;
  float balance;
  String history_address;

}
