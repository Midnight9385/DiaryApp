import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

//this class was an example of how to check if a file existed and if not how to create it and any missing folders it is in
public class CheckFileExistExample{
    public static void main(String[] args) {

        checkFilesExist();
        // Create a JFrame (window) instance
        JFrame frame = new JFrame("Simple GUI Example");
        
        // Create a JLabel (text label) instance
        JLabel label = new JLabel(new File("").getAbsolutePath());

        // Add the label to the frame
        frame.add(label);

        // Set the size of the frame
        frame.setSize(300, 200);

        // Set the default close operation (exit on close)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to be visible
        frame.setVisible(true);
    }

    public static void checkFilesExist(){
        String folderPath = new File("").getAbsolutePath()+"\\Data";
        // System.out.println(File.separator);
        String filePath = folderPath + File.separator + "UserData.txt";

        File file = new File(filePath);

        if (file.exists()) {
            // System.out.println("File already exists.");
        } else {
            // Create the folder if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    // System.out.println("Folder created successfully.");
                } else {
                    // System.out.println("Failed to create the folder.");
                }
            }

            try {
                // Create the file
                if (file.createNewFile()) {
                    // System.out.println("File created successfully.");
                } else {
                    // System.out.println("Failed to create the file.");
                }
            } catch (IOException e) {
                // System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}