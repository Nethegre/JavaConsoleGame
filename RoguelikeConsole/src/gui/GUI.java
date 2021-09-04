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
        mapTextPane.setFont(new Font("monospaced", Font.PLAIN, 14));
        mapTextPane.setBounds(0,0,100*mapTextPane.getFont().getSize(),100*mapTextPane.getFont().getSize());
        mapTextPane.setForeground(Color.WHITE);
        mapTextPane.setBackground(Color.BLACK);
        frame.add(mapTextPane);

        //JFrame window settings - has to happen last
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So program actually stops when X is clicked
        frame.setSize(mapTextPane.getWidth()+20,mapTextPane.getHeight()+50);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void updateMap(char[][] gameMap) {
        String newMap = "";

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                newMap += gameMap[i][j];
            }
            newMap += '\n';
        }

        mapTextPane.setText(newMap);
    }
}
