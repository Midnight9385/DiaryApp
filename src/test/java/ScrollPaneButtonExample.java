import javax.swing.*;
import java.util.ArrayList;


//This class was written by ChatGPT as a sample of making clickable buttons in a scroll pane
//I add this class solely because it took many attempts to find a solution that worked and might be a useful example to others
public class ScrollPaneButtonExample {
    private static JPanel itemPanel = new JPanel();
    private static ArrayList<JButton> buttonList = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scroll Pane Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a scroll pane and set its viewport to contain the itemPanel
        JScrollPane scrollPane = new JScrollPane(itemPanel);

        // Set layout for the itemPanel
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        // Add buttons to the itemPanel
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("Button " + i);
            itemPanel.add(button);
            buttonList.add(button);
        }

        // Add an ActionListener to each button
        for (JButton b : buttonList) {
            b.addActionListener(e -> {
                // Perform action when a button is clicked
                System.out.println("Button clicked: " + b.getText());
            });
        }

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Display the frame
        frame.setVisible(true);
    }
}
