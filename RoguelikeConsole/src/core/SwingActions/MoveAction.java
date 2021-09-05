package core.SwingActions;

import base.Character;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveAction extends AbstractAction {
    private Character character;
    private int xOffset, yOffset;

    public MoveAction(Character character, int xOffset, int yOffset) {
        this.character = character;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void actionPerformed(ActionEvent e) {
        character.move(xOffset, yOffset);
    }
}