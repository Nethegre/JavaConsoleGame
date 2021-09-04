import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        //JFrame window settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So program actually stops when X is clicked
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);

        //Map out text pane
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBounds(0,0,300,300);
        textPane.setBackground(Color.BLACK);
        frame.add(textPane);
    }
}
