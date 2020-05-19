package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CalendarUI.caseArea;

public class Case2 extends Case {


    public Case2(CalendarUI calendarUI, JComponent parent) {
        super(calendarUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Search for events on a particular day");
        button = customizeButton(button);
        button.setBounds(X, 2 * Y + 2 * HEIGHT, WIDTH, HEIGHT);
        Font newButtonFont = new Font(button.getFont().getName(),button.getFont().getStyle(),FONT_SIZE);
        button.setFont(newButtonFont);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SearchEventClickHandler());
    }

    private class SearchEventClickHandler implements ActionListener {

        // EFFECTS: repaint the JPanel and create a JTextArea for listing out events for a given day
        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            new ListEvents();
        }
    }
}
