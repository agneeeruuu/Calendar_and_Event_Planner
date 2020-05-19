package ui;

import ui.web.HolidayInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static ui.CalendarUI.caseArea;


public class Case3 extends Case {

    private static int textX = 100;
    private static int textY = textX;

    public Case3(CalendarUI calendarUI, JComponent parent) {
        super(calendarUI, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("List out national holidays");
        button = customizeButton(button);
        button.setBounds(X, 2 * Y + HEIGHT * 3 + 50, WIDTH, HEIGHT);
        Font newButtonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), FONT_SIZE);
        button.setFont(newButtonFont);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new NationalHolidayClickListener());
    }

    private class NationalHolidayClickListener implements ActionListener {

        // EFFECTS: repaint the JPanel and create a JTextArea for printing out web data (national holidays)
        @Override
        public void actionPerformed(ActionEvent e) {
            caseArea.removeAll();
            caseArea.repaint();
            printOutHolidays();
        }
    }

    private void printOutHolidays() {
        try {
            JTextArea holidays = new JTextArea(HolidayInfo.printHolidays());
            holidays.setBounds(textX, textY, (CalendarUI.WIDTH - 2 * textX), (CalendarUI.HEIGHT - 2 * textY - 100));
            Font font = new Font(holidays.getFont().getName(), holidays.getFont().getStyle(), 18);
            holidays.setFont(font);
            holidays.setEditable(false);
            holidays.setLineWrap(true);
            holidays.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(holidays);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(textX, textY, (CalendarUI.WIDTH - 2 * textX), (CalendarUI.HEIGHT - 2 * textY - 100));
            caseArea.add(scroll);
            caseArea.add(BackButton.backButton());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
