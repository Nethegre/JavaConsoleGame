package gui;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame frame;
    private JTextPane mapTextPane;

    public void init() {
        frame = new JFrame();

        //Text pane to display game map
        mapTextPane = new JTextPane();
        mapTextPane.setEditable(false);
        mapTextPane.setBounds(0,0,300,300);
        mapTextPane.setBackground(Color.BLACK);
        frame.add(mapTextPane);

        //JFrame window settings - has to happen last
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So program actually stops when X is clicked
        frame.setSize(320,350);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
