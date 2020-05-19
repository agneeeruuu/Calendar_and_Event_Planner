package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CalendarUI.caseArea;


public class Case1 extends Case {

    public Case1(CalendarUI calendarUI, JComponent parent) {
        super(calendarUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add a new event");
        button = customizeButton(button);
        button.setBounds(X, Y + HEIGHT + 50, WIDTH, HEIGHT);
        Font newButtonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), FONT_SIZE);
        button.setFont(newButtonFont);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddNewEventClickHandler());
    }

    private class AddNewEventClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            createEvent();
        }
    }

    private void createEvent() {
        JButton regularButton = new JButton("Regular Event");
        regularButton = customizeButton(regularButton);
        regularButton.setBounds(CalendarUI.WIDTH / 3, (CalendarUI.HEIGHT - 2 * HEIGHT) / 3,
                CalendarUI.WIDTH / 3, HEIGHT);
        Font newRButtonFont = new Font(regularButton.getFont().getName(),
                regularButton.getFont().getStyle(), FONT_SIZE);
        regularButton.setFont(newRButtonFont);

        JButton importantButton = new JButton("Important Event");
        importantButton = customizeButton(importantButton);
        importantButton.setBounds(CalendarUI.WIDTH / 3, 2 * ((CalendarUI.HEIGHT - 2 * HEIGHT) / 3) - 100 + HEIGHT,
                CalendarUI.WIDTH / 3, HEIGHT);
        Font newIButtonFont = new Font(importantButton.getFont().getName(),
                regularButton.getFont().getStyle(), FONT_SIZE);
        importantButton.setFont(newIButtonFont);
        caseArea.add(regularButton);
        caseArea.add(importantButton);
        regularButton.addActionListener(new AddRegularClickListener());
        importantButton.addActionListener(new AddImportantClickListener());
    }

    private class AddRegularClickListener implements ActionListener {

        // EFFECTS: repaint the JPanel and create a new page for getting regular event details
        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            CreateRegularEvent.getEventDetails();
        }
    }

    private class AddImportantClickListener implements ActionListener {

        // EFFECTS: repaint the JPanel and create a new page for getting important event details
        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            CreateImportantEvent.getEventDetails();
        }
    }
}
