package util.UI.Dialogs;

import util.UI.UiBooster;
import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.LoginCredentials;
import util.UI.UISpacer;

import javax.swing.*;

import App.DiaryApp;
import UserSystem.User;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class LoginDialog extends JDialog {

    private final JTextField tfUsername;
    private final JPasswordField pfPassword;
    private final JButton btnLogin;
    private final DialogClosingState closingState;

    private static User[] u = new User[1];

    public LoginDialog(boolean login, String title, String message, String usernameLabel, String passwordLabel, String loginButtonLabel, String cancelButtonLabel, String iconPath) {
        super((JFrame) null, title, true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.ipadx = 5;
        cs.ipady = 5;

        JLabel lbAddress = new JLabel(message);
        lbAddress.setFont(lbAddress.getFont().deriveFont((float)20));
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(lbAddress, cs);

        JLabel lbUsername = new JLabel(usernameLabel);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        JLabel lbPassword = new JLabel(passwordLabel);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

        pfPassword.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

        closingState = new DialogClosingState();

        btnLogin = new JButton(loginButtonLabel);
        btnLogin.addActionListener(e -> dispose());

        JButton btnCancel = new JButton(cancelButtonLabel);
        btnCancel.addActionListener(e -> {
            DiaryApp.signOut(getUser(), true);
            // closingState.setClosedByUser(true);
        });

        

        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
        if(login){
         JButton btnForgot = new JButton("Forgot Password");
         btnForgot.addActionListener(e -> DiaryApp.forgotPassword());

         JButton btnCreate = new JButton("Create New Account");
         btnCreate.addActionListener(e -> AccountCreatorDialog.createAccount());
         
         bp.add(btnForgot);
         bp.add(btnCreate);
        }


        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        applyWindowIcon(iconPath, this);

        tfUsername.requestFocus();
    }

    public LoginCredentials showDialog() {
        setVisible(true);
        dispose();
        return closingState.isClosedByUser() ?
                null :
                new LoginCredentials(tfUsername.getText().trim(), new String(pfPassword.getPassword()));
    }

    public static void setUser(User user){
        u[0] = user;
    }

    public static User getUser(){
        return u[0];
    }

    public static void start(){
        new UiBooster().showWaitingDialog("", "").close();
        EntryListDialog.showEntires(login(false, new LoginDialog(true, "Login to App", "Enter Username And Password", 
                                                     "Username: ", "Password: ", 
                                                     "Login", "Exit", 
                                                     "C:\\Users\\Zachary\\Documents\\GitHub\\diaryapp\\src\\main\\resources\\DiaryAppIcon.png")
                                                     ), true);                                                                                          
    }

    public static User login(boolean failed, LoginDialog login){
        login.setBounds(UISpacer.getMiddleX(800), UISpacer.getMiddleY(400), 800, 400);
        if(failed){
            new UiBooster().showInfoDialog("invalid username or password please retry", false);
        }
        DiaryApp.getUserInterface().signIn(login.showDialog(), u); //uses psuedo pass by reference since it is easier in this case
        return (u[0]!=null)?u[0]:login(true, login);
    }
}
