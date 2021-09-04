package gui.SwingActions;

import mocks.MockedPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class moveAction extends AbstractAction {
    private MockedPlayer mockedPlayer;
    private int xOffset, yOffset;

    public moveAction(MockedPlayer mockedPlayer, int xOffset, int yOffset) {
        this.mockedPlayer = mockedPlayer;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void actionPerformed(ActionEvent e) {
        mockedPlayer.move(xOffset, yOffset);
    }
}