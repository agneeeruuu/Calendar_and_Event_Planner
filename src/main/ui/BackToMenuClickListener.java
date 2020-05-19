package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CalendarUI.caseArea;

public class BackToMenuClickListener implements ActionListener {

    //EFFECTS: repaint the JPanel with menu
    @Override
    public void actionPerformed(ActionEvent e) {
        caseArea.removeAll();
        caseArea.repaint();
        new CalendarUI().createMenu();
    }
}
