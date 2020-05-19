package ui;

import placeholder.model.Event;
import placeholder.model.EventRegular;
import placeholder.model.exception.InvalidCategoryException;
import placeholder.model.exception.InvalidDateException;
import placeholder.model.exception.InvalidEventNameException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CalendarUI.calendar;
import static ui.CalendarUI.caseArea;

public class CreateRegularEvent {

    private static JTextField dateText;
    private static JTextField categoryText;
    private static JTextField nameText;
    private static Event event;

    // EFFECTS: construct JTextFields for getting user input for event date, category, and name
    public static void getEventDetails() {
        caseArea.add(dateHelper("please enter event date in MMdd format\n"
                + "for example: Jul 17th as 0717"));
        caseArea.add(labelHelper("Date: ", 100));
        caseArea.add(categoryHelper("please enter event category\n"
                + "for example, SCHOOL, WORK, FAMILY, ..."));
        caseArea.add(labelHelper("Category: ", 300));
        caseArea.add(nameHelper("please enter event name\n"
                + "for example, Accounting Lecture"));
        caseArea.add(labelHelper("Name: ", 500));
        caseArea.add(BackButton.backButton());
        caseArea.add(enter());
    }

    private static JButton enter() {
        JButton enter = new JButton("Enter");
        enter.setBounds(CalendarUI.WIDTH / 2 - 40, CalendarUI.HEIGHT - 225,
                100, 50);
        Font enterFont = new Font(enter.getFont().getName(),
                enter.getFont().getStyle(), 24);
        enter.setFont(enterFont);
        enter.addActionListener(new EnterClickListener());
        return enter;
    }

    private static JTextField nameHelper(String description) {
        nameText = new JTextField(description);
        nameText.setBounds(200, 500, 725, 50);
        Font eventFont = new Font(nameText.getFont().getName(), nameText.getFont().getStyle(), 20);
        nameText.setFont(eventFont);
        return nameText;
    }

    private static JTextField categoryHelper(String description) {
        categoryText = new JTextField(description);
        categoryText.setBounds(200, 300, 725, 50);
        Font eventFont = new Font(categoryText.getFont().getName(), categoryText.getFont().getStyle(), 20);
        categoryText.setFont(eventFont);
        return categoryText;
    }

    private static JTextField dateHelper(String description) {
        dateText = new JTextField(description);
        dateText.setBounds(200, 100, 725, 50);
        Font eventFont = new Font(dateText.getFont().getName(), dateText.getFont().getStyle(), 20);
        dateText.setFont(eventFont);
        return dateText;
    }

    // EFFECTS: construct JLabel for event name/ date/ category beside the JTextFields
    public static JLabel labelHelper(String description, int y) {
        JLabel label = new JLabel(description, SwingConstants.RIGHT);
        label.setBounds(75, y, 125, 50);
        Font dateLFont = new Font(label.getFont().getName(), label.getFont().getStyle(), 24);
        label.setFont(dateLFont);
        return label;
    }


    private static class EnterClickListener implements ActionListener {

        // EFFECTS: check if the name/ category/ date entered by the user is valid
        @Override
        public void actionPerformed(ActionEvent e) {

            catchDateException();

            try {
                calendar.processCategory(categoryText.getText());
            } catch (InvalidCategoryException ex) {
                categoryText.setText("Ooops! Can you "
                        + "start over and enter a meaningful event category next time?");
            }

            try {
                calendar.processName(nameText.getText());
            } catch (InvalidEventNameException ex) {
                nameText.setText("Ooops! Can you start "
                        + "over and enter a meaningful event name next time?");
            }
            event = new EventRegular(Integer.valueOf(dateText.getText()),
                    categoryText.getText(), nameText.getText());
            calendar.addEvent(event);
            successfulMessage();
        }

        private void successfulMessage() {
            caseArea.removeAll();
            caseArea.repaint();
            JTextArea successful = new JTextArea(event.printOutEvent());
            successful.setBounds(CalendarUI.WIDTH / 2 - 300, CalendarUI.HEIGHT / 2 - 200, 600, 100);
            Font font = new Font(successful.getFont().getName(), successful.getFont().getStyle(), 20);
            successful.setFont(font);
            successful.setLineWrap(true);
            successful.setWrapStyleWord(true);
            successful.setEditable(false);
            successful.setBorder(null);
            successful.setOpaque(false);
            successful.setFocusable(false);
            caseArea.add(successful);
            caseArea.add(BackButton.backButton());
        }

        // EFFECTS: return exception message if the date entered is not valid
        public void catchDateException() {
            try {
                Integer.parseInt(dateText.getText());
                calendar.processDate(Integer.valueOf(dateText.getText()));
            } catch (NumberFormatException nfe) {
                dateText.setText("can you enter a "
                        + "4 digit number MMDD as event date (e.g., 1113 as Nov 13)?");
            } catch (InvalidDateException ex) {
                dateText.setText("can you enter a "
                        + "4 digit number MMDD as event date (e.g., 1113 as Nov 13)?");
            }
        }

    }
}
