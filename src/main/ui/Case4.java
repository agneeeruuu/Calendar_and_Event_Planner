package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CalendarUI.caseArea;

public class Case4 extends Case {

    private Menu menu;
    private static int textX = 100;
    private static int textY = textX;

    public Case4(CalendarUI calendarUI, JComponent parent) {
        super(calendarUI, parent);
        menu = new Menu();
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Print reminders");
        button = customizeButton(button);
        button.setBounds(X, 2 * Y + HEIGHT * 4 + Y, WIDTH, HEIGHT);
        Font newButtonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), FONT_SIZE);
        button.setFont(newButtonFont);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new PrintReminderClickListener());
    }

    private class PrintReminderClickListener implements ActionListener {

        // EFFECTS: repainted the JPanel and create a JTextArea fot printing reminder
        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            printReminder();
        }

        private void printReminder() {
            JTextArea reminder = new JTextArea(menu.printReminder());
            reminder.setBounds(textX, textY, (CalendarUI.WIDTH - 2 * textX), (CalendarUI.HEIGHT - 2 * textY - 100));
            Font font = new Font(reminder.getFont().getName(), reminder.getFont().getStyle(), 18);
            reminder.setFont(font);
            reminder.setEditable(false);
            reminder.setLineWrap(true);
            reminder.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(reminder);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(textX, textY, (CalendarUI.WIDTH - 2 * textX), (CalendarUI.HEIGHT - 2 * textY - 100));
            caseArea.add(scroll);
            caseArea.add(BackButton.backButton());
        }
    }
}
