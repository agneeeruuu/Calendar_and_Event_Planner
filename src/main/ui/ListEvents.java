package ui;

import placeholder.model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import static ui.CalendarUI.calendar;
import static ui.CalendarUI.caseArea;

public class ListEvents {

    private JLabel case2Label;
    private JTextField searchDate;
    private JTextArea eventsInTheDate;

    // EFFECTS: added a JLabel and JTextField for getting user input for the date they want to search for
    public ListEvents() {
        caseArea.add(BackButton.backButton());
        case2Label = new JLabel("Please enter the date you want to search in MMdd format: ");
        case2Label.setBounds(150, 80, 700, 100);
        case2Label.setHorizontalAlignment(SwingConstants.CENTER);
        Font labelFont = new Font(case2Label.getFont().getName(), case2Label.getFont().getStyle(), 24);
        case2Label.setFont(labelFont);
        caseArea.add(case2Label);

        searchDate = new JTextField();
        searchDate.setBounds(300, 200, 400, 50);
        Font textFont = new Font(searchDate.getFont().getName(), searchDate.getFont().getStyle(), 24);
        searchDate.setFont(textFont);
        searchDate.addActionListener(new FindDateClickListener());
        caseArea.add(searchDate);
    }

    private class FindDateClickListener implements ActionListener {

        // EFFECTS: construct a JTextArea for printing out events listed in the given day
        // and determine if the day is busy or not
        @Override
        public void actionPerformed(ActionEvent e) {
            String dateString = searchDate.getText();
            ArrayList<Event> events = (ArrayList<Event>) calendar.getEventsMap().getDateMap().get(dateString);

            eventsInTheDate = new JTextArea();

            if (events != null) {
                eventsInTheDate.append("You have...\n\n");

                for (Event event : events) {
                    eventsInTheDate.append("\n\n" + event.getEventCategory() + " : " + event.getEventName());
                }
                eventsInTheDate.append("\n\n\n\n" + calendar.getEventsMap().busyOrNot(dateString));
            } else {
                eventsInTheDate.append("There's no events added yet!");
            }

            setLayout();
        }

        private void setLayout() {
            eventsInTheDate.setBounds(100, 300, 800, 300);
            Font font = new Font(eventsInTheDate.getFont().getName(), eventsInTheDate.getFont().getStyle(), 18);
            eventsInTheDate.setFont(font);
            eventsInTheDate.setEditable(false);
            eventsInTheDate.setLineWrap(true);
            eventsInTheDate.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(eventsInTheDate);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBounds(100, 300, 800, 300);
            caseArea.add(scroll);
        }
    }
}
