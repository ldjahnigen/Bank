import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Spankbank {
  public static void main(String[] args) {
    //initialize frame
    JFrame frame = new JFrame();
    frame.setSize(1100, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    // start at LoginPage
    LoginPage loginpage = new LoginPage(frame);
    loginpage.construct();

  }
}

class Page {
  JPanel panel;
  JFrame frame;
}

class LoginPage extends Page {
  public static HashMap<String, String> populateHash() {
    HashMap<String, String> accounts_hash = new HashMap<String, String>();
    FileHound filehound = new FileHound();
    String[] accounts_list = filehound.fileRead("accounts.txt").split(",");

    for (String s : accounts_list) {
      String account_file = filehound.fileRead(s + ".txt");
      String[] account_files = account_file.split(",");
      accounts_hash.put(account_files[0], account_files[1]);
    }

    return accounts_hash;
  }

  public LoginPage(JFrame mainframe) {
    // create panel, factory, and define fonts; assign frame to page object
    frame = mainframe;
    panel = new JPanel(new GridLayout(0, 2));
    panel.setBorder(BorderFactory.createEmptyBorder(250, 350, 300, 350));
    Factory f = new Factory();
    FileHound filehound = new FileHound();
    Font normal_font = new Font("Arial", Font.PLAIN, 18);
    Font title_font = new Font("Arial", Font.BOLD, 24);
    
    // start with panel invisible
    panel.setVisible(false);

    // add components
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
        // create account
        String username = username_field.getText(); 
        char[] passwordc = password_field.getPassword();
        String password = new String(passwordc);
        Account account = new Account(username, password);
  
        // clear text fields, remove components, then create mainpage
        username_field.setText("");
        password_field.setText("");
        panel.removeAll();
        MainPage mainpage = new MainPage(frame);
        mainpage.construct(mainpage);
      }
    }));

    JButton loginbutton = f.genButton("Log in", null);
    JLabel ack = f.genLabel("", normal_font);
    panel.add(loginbutton); 
    panel.add(ack);

    ActionListener listener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      String username = username_field.getText(); 
      char[] passwordc = password_field.getPassword();
      String password = new String(passwordc);

      username_field.setText("");
      password_field.setText("");
      
      HashMap<String, String> accounts_hash = populateHash();

      if (password.equals(accounts_hash.get(username))) {
        panel.removeAll();
        MainPage mainpage = new MainPage(frame);
        mainpage.construct(mainpage);
      } else {
        ack.setText("Bad Credentials");
        panel.repaint();
        panel.revalidate();
      }
    }
  };
  loginbutton.addActionListener(listener);

  frame.add(panel, BorderLayout.CENTER); 
  frame.repaint();
  frame.revalidate();
  }

  public void construct() {
    panel.setVisible(true);
  }
}

class MainPage extends Page {
  public MainPage(JFrame mainframe) {
    frame = mainframe;
    panel = new JPanel(new GridLayout(0, 2));
    frame.add(panel, BorderLayout.CENTER); 
    Factory f = new Factory();
    Font normal_font = new Font("Arial", Font.PLAIN, 18);
    Font title_font = new Font("Arial", Font.BOLD, 24);
    panel.setBorder(BorderFactory.createEmptyBorder(250, 350, 300, 350));
    panel.add(f.genLabel("Welcome", title_font));
    panel.setVisible(false);
  }

  public void construct(MainPage mainpage) {
    panel.setVisible(true);
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
  float balance = (float) 0.0;
  String location;

  @Override
  public String toString() {
    return username + ", " + password + ", " + balance;
  }

  public Account(String usernamei, String passwordi) {
    username = usernamei;
    password = passwordi;
    location = username + ".txt";
    FileHound f = new FileHound();
    f.fileWrite(username + "," + password + "," + balance, location);
    f.fileAppend(username + ",", "accounts.txt");

  }
}
