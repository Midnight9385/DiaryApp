package App;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CRUD.DataInterface;
import EmailSystem.PasswordResetPopup;
import EmailSystem.SetEmailPopup;
import UserSystem.User;
import UserSystem.UserInterface;
import util.ErrorHandler;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DiaryApp {
    private static UserInterface userInterface;
    private static DataInterface dataInterface;
    private static User user;
    private static JTextField usernameBox;
    private static JTextField passwordBox;
    private static JTextField entryNameBox;
    private static JFrame frame;
    private static JPanel signInPanel = new JPanel();
    private static JPanel entriesPanel = new JPanel();
    private static JPanel writePanel = new JPanel();
    private static JButton signInB;
    private static JButton createEntryB;
    private static JButton forgotPasswordB;
    private static JButton createAccountB;
    private static JButton saveEntryB;
    private static JLabel signInText;
    private static JTextArea journalEntry = new JTextArea();
    private static JTextArea journalTitle = new JTextArea();
    private static String nameOfChosenEntry;
    private static JScrollPane itemScrollPane;
    private static JPanel itemPanel = new JPanel();
    private static ArrayList<JButton> buttonList = new ArrayList<>();
    private static JButton signOutB = new JButton("sign out");
    private static JButton exitB = new JButton("exit");
    private static JButton setEmailB = new JButton("set recovery email");
    public static void main(String[] args) {

        checkFilesExist();

        dataInterface = new DataInterface();
        userInterface = new UserInterface();

        // Create a JFrame (main window)
        frame = new JFrame("Basic Swing GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Width, Height
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        

        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
        writePanel.setLayout(new BoxLayout(writePanel, BoxLayout.Y_AXIS));
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        itemPanel.setPreferredSize(new Dimension(780, 400));

        //#region elements

        //#region buttons
        signInB = new JButton("Sign in");
        forgotPasswordB = new JButton("forgot password");
        createEntryB = new JButton("create new entry");
        createAccountB = new JButton("create new account");
        saveEntryB = new JButton("save and exit journal");
        setEmailB = new JButton("Set Recovery Email");

        //#endregion

        //#region labels
            signInText = new JLabel("Sign In!");
            signInText.setFont(new Font("Helvetica", Font.PLAIN, 75));
        //#endregion

        //#region text boxes
            usernameBox = new JTextField("username");
            usernameBox.setForeground(Color.GRAY);
            usernameBox.setMaximumSize(new Dimension(400, 100));
            usernameBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (usernameBox.getText().equals("username") || usernameBox.getText().equals("enter a username to use")){
                        usernameBox.setText("");
                        usernameBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (usernameBox.getText().isEmpty()) {
                        usernameBox.setText("username");
                        usernameBox.setForeground(Color.GRAY);
                    }
                }
            });
            passwordBox = new JTextField("password");
            passwordBox.setForeground(Color.GRAY);
            passwordBox.setMaximumSize(new Dimension(400, 100));
            passwordBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (passwordBox.getText().equals("password") || passwordBox.getText().equals("enter a password to use")){
                        passwordBox.setText("");
                        passwordBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (passwordBox.getText().isEmpty()) {
                        passwordBox.setText("password");
                        passwordBox.setForeground(Color.GRAY);
                    }
                }
            });
            entryNameBox = new JTextField("enter name for entry");
            entryNameBox.setForeground(Color.GRAY);
            entryNameBox.setMaximumSize(new Dimension(400, 100));
            entryNameBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (entryNameBox.getText().equals("enter name for entry")){
                        entryNameBox.setText("");
                        entryNameBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (entryNameBox.getText().isEmpty()) {
                        entryNameBox.setText("enter name for entry");
                        entryNameBox.setForeground(Color.GRAY);
                    }
                }
            });

            journalEntry.setMaximumSize(new Dimension(750, 560));
        //#endregion

        
        itemScrollPane = new JScrollPane(itemPanel);

        itemScrollPane.setBackground(Color.BLUE);
        itemScrollPane.setEnabled(true);

        journalTitle.setMaximumSize(new Dimension(780, 25));

        signInText.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInB.setAlignmentX(Component.CENTER_ALIGNMENT);
        setEmailB.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordB.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountB.setAlignmentX(Component.CENTER_ALIGNMENT);
        journalEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveEntryB.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveEntryB.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitB.setAlignmentX(Component.CENTER_ALIGNMENT);
        signOutB.setAlignmentX(Component.RIGHT_ALIGNMENT);
        entryNameBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        createEntryB.setAlignmentX(Component.LEFT_ALIGNMENT);

        itemScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemScrollPane.setFocusTraversalPolicyProvider(true);

        //#endregion

        //#region action listeners
            signInB.addActionListener(a -> signIn());            
            createEntryB.addActionListener(b -> createEntry());
            createAccountB.addActionListener(c -> createAccount());
            saveEntryB.addActionListener(d -> saveEntry());
            signOutB.addActionListener(e -> signOut());
            exitB.addActionListener(f -> exit());    
            setEmailB.addActionListener(g -> setEmail());   
            forgotPasswordB.addActionListener(h -> forgotPassword());
        //#endregion

        //#region add to panel and frame
        signInPanel.add(Box.createVerticalGlue());
        signInPanel.add(signInText);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(usernameBox);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(passwordBox);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(signInB);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(forgotPasswordB);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(createAccountB);
        signInPanel.add(Box.createVerticalStrut(50));
        signInPanel.add(exitB);
        signInPanel.add(Box.createVerticalGlue());

        entriesPanel.add(itemScrollPane);
        entriesPanel.add(signOutB);
        entriesPanel.add(createEntryB);
        entriesPanel.add(entryNameBox);
        entriesPanel.add(setEmailB);

        writePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Top padding
        writePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Right padding
        writePanel.add(journalTitle);
        writePanel.add(Box.createVerticalStrut(10));
        writePanel.add(journalEntry);
        writePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Bottom padding
        writePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Left padding
        writePanel.add(saveEntryB);



        frame.add(signInPanel);
        //#endregion

        // Display the frame
        frame.setVisible(true);

        signInB.requestFocusInWindow();
    }

    private static void setEmail() {
        new SetEmailPopup(user);
    }

    public DiaryApp(){
        checkFilesExist();

        dataInterface = new DataInterface();
        userInterface = new UserInterface();

        // Create a JFrame (main window)
        frame = new JFrame("Basic Swing GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Width, Height
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        

        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
        writePanel.setLayout(new BoxLayout(writePanel, BoxLayout.Y_AXIS));
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        itemPanel.setPreferredSize(new Dimension(780, 400));

        //#region elements

        //#region buttons
        signInB = new JButton("Sign in");
        forgotPasswordB = new JButton("Forgot Login");
        createEntryB = new JButton("create new entry");
        createAccountB = new JButton("create new account");
        saveEntryB = new JButton("save and exit journal");
        setEmailB = new JButton("Set Recovery Email");

        //#endregion

        //#region labels
            signInText = new JLabel("Sign In!");
            signInText.setFont(new Font("Helvetica", Font.PLAIN, 75));
        //#endregion

        //#region text boxes
            usernameBox = new JTextField("username");
            usernameBox.setForeground(Color.GRAY);
            usernameBox.setMaximumSize(new Dimension(400, 100));
            usernameBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (usernameBox.getText().equals("username") || usernameBox.getText().equals("enter a username to use")){
                        usernameBox.setText("");
                        usernameBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (usernameBox.getText().isEmpty()) {
                        usernameBox.setText("username");
                        usernameBox.setForeground(Color.GRAY);
                    }
                }
            });
            passwordBox = new JTextField("password");
            passwordBox.setForeground(Color.GRAY);
            passwordBox.setMaximumSize(new Dimension(400, 100));
            passwordBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (passwordBox.getText().equals("password") || passwordBox.getText().equals("enter a password to use")){
                        passwordBox.setText("");
                        passwordBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (passwordBox.getText().isEmpty()) {
                        passwordBox.setText("password");
                        passwordBox.setForeground(Color.GRAY);
                    }
                }
            });
            entryNameBox = new JTextField("enter name for entry");
            entryNameBox.setForeground(Color.GRAY);
            entryNameBox.setMaximumSize(new Dimension(400, 100));
            entryNameBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (entryNameBox.getText().equals("enter name for entry")){
                        entryNameBox.setText("");
                        entryNameBox.setForeground(Color.BLACK);
                    }
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    if (entryNameBox.getText().isEmpty()) {
                        entryNameBox.setText("enter name for entry");
                        entryNameBox.setForeground(Color.GRAY);
                    }
                }
            });

            journalEntry.setMaximumSize(new Dimension(750, 560));
        //#endregion

        
        itemScrollPane = new JScrollPane(itemPanel);

        itemScrollPane.setBackground(Color.BLUE);
        itemScrollPane.setEnabled(true);

        journalTitle.setMaximumSize(new Dimension(780, 25));

        signInText.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInB.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountB.setAlignmentX(Component.CENTER_ALIGNMENT);
        journalEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveEntryB.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveEntryB.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordB.setAlignmentX(Component.CENTER_ALIGNMENT);
        setEmailB.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitB.setAlignmentX(Component.CENTER_ALIGNMENT);
        signOutB.setAlignmentX(Component.RIGHT_ALIGNMENT);
        entryNameBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        createEntryB.setAlignmentX(Component.LEFT_ALIGNMENT);

        itemScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemScrollPane.setFocusTraversalPolicyProvider(true);

        //#endregion

        //#region action listeners
            signInB.addActionListener(a -> signIn());
            createEntryB.addActionListener(b -> createEntry());
            createAccountB.addActionListener(c -> createAccount());
            saveEntryB.addActionListener(d -> saveEntry());
            signOutB.addActionListener(e -> signOut());
            exitB.addActionListener(f -> exit());       
            forgotPasswordB.addActionListener(g -> forgotPassword());
            setEmailB.addActionListener(h -> setEmail());
        //#endregion

        //#region add to panel and frame
        signInPanel.add(Box.createVerticalGlue());
        signInPanel.add(signInText);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(usernameBox);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(passwordBox);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(signInB);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(forgotPasswordB);
        signInPanel.add(Box.createVerticalStrut(25));
        signInPanel.add(createAccountB);
        signInPanel.add(Box.createVerticalStrut(50));
        signInPanel.add(exitB);
        signInPanel.add(Box.createVerticalGlue());

        entriesPanel.add(itemScrollPane);
        entriesPanel.add(signOutB);
        entriesPanel.add(createEntryB);
        entriesPanel.add(entryNameBox);
        entriesPanel.add(setEmailB);

        writePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Top padding
        writePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Right padding
        writePanel.add(journalTitle);
        writePanel.add(Box.createVerticalStrut(10));
        writePanel.add(journalEntry);
        writePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Bottom padding
        writePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Left padding
        writePanel.add(saveEntryB);



        frame.add(signInPanel);
        //#endregion

        // Display the frame
        frame.setVisible(true);

        signInB.requestFocusInWindow();
    }

    public static void checkFilesExist(){
        String folderPath = new File("").getAbsolutePath()+"\\Data";
        String filePath = folderPath + File.separator + "UserData.txt";

        File file = new File(filePath);

        if (file.exists()) {
            //if the file already exist no need to do anything further
        } else {
            // Create the folder if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    //folder created successfully
                } else {
                    //if the folder wasn't successfully created then you cannot use this app
                    //so exit and display error message
                    error();
                }
            }

            try {
                // Create the file
                if (file.createNewFile()) {
                    //file made successfully
                } else {
                    //while technically the app can still be used without the file it is used for critical functions
                    //and thus it not being created will result in the app closing and displaying and error message
                    error();
                }
            } catch (IOException e) {
                //if there is some exception it is safe to assume the file cannot be used and thus the app cannot be used
                error();
            }
        }
    }

    public static void forgotPassword(){
        // System.out.println("started email thing");
        try {
            new PasswordResetPopup(userInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("ended email thing");
        return;
    }

    public static void error(){
        //if there is an error this method will display an error dialog and then exit the program once the user clicks
        //ok in the error dialog
        ErrorHandler.sendFatalErrorMessage("Error Creating File", "there was an error creating the file directory or file for storing data, rerun application and make sure you are"+
        "using admin. \n\n If error persist try making the file directory and files yourself in the directory of the jar file, "+
        new File("").getAbsolutePath()+ ", in there make a folder name Data and inside that folder create UserData.txt");                     
    }

    public static void signOut(){
        userInterface.exit(dataInterface.exit(), user, false);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(signInPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void exit(){
        System.exit(0);
    }

    public static void signIn(){
        user = userInterface.signIn(usernameBox.getText(), passwordBox.getText());
        if(user != null){
            frame.getContentPane().removeAll();
            frame.getContentPane().add(entriesPanel);
            frame.revalidate();
            frame.repaint();

            dataInterface.updateDataStorage(user.getSerial());

            loadItems();
        }else{
            usernameBox.setText("invalid login!");
            usernameBox.setForeground(Color.RED);
        }
    }

    public static void saveEntry(){
        for (JButton b : buttonList) {
            if(b.getText().indexOf(nameOfChosenEntry)!=-1){
                buttonList.remove(b);
                break;
            }
        }

        dataInterface.getDataStorage().deleteData(nameOfChosenEntry);
        dataInterface.getDataStorage().createData(journalEntry.getText(), journalTitle.getText());

        frame.getContentPane().removeAll();
        frame.getContentPane().add(entriesPanel);
        frame.revalidate();
        frame.repaint();

        loadItems();
    }

    public static void readData(String s){
        nameOfChosenEntry = s;
        journalEntry.setText(dataInterface.getDataStorage().readData(s).read().toString());
        journalTitle.setText(dataInterface.getDataStorage().readData(s).getName());
    }

    public static void loadEntry(String s){
        readData(s);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(writePanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void createEntry(){
        if(!entryNameBox.getText().isEmpty() && !entryNameBox.getText().equals("enter name for entry")){
            dataInterface.create(entryNameBox.getText());
            nameOfChosenEntry = entryNameBox.getText();

            journalEntry.setText("");
            journalTitle.setText(nameOfChosenEntry);

            entryNameBox.setText("");

            frame.getContentPane().removeAll();
            frame.getContentPane().add(writePanel);
            frame.revalidate();
            frame.repaint();
        }
    }

    public static void createAccount(){
        if(usernameBox.getText().equals("username")||passwordBox.getText().equals("password")){
            usernameBox.setText("enter a username to use");
            passwordBox.setText("enter a password to use");
        }else{
            user = userInterface.createUser(usernameBox.getText(), passwordBox.getText());

            dataInterface.updateDataStorage(user.getSerial());

            frame.getContentPane().removeAll();
            frame.getContentPane().add(entriesPanel);
            frame.revalidate();
            frame.repaint();

            loadItems();
        }
    }

    public static void loadItems() {
        buttonList.removeAll(buttonList);
        itemPanel.removeAll();

        String[] strings = dataInterface.getList();

        for (String s : strings) {
            JButton button = new JButton(s);
            buttonList.add(button);
            itemPanel.add(button);
        }

        itemScrollPane.revalidate();
        itemScrollPane.repaint();

        for (JButton b : buttonList) {
            b.addActionListener(e -> loadEntry(b.getText().substring(0, b.getText().indexOf("-") - 1)));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }
}