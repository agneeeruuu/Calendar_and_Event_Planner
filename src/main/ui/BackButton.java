package ui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static ui.Case.customizeButton;

public class BackButton {

    // EFFECTS: construct a JButton so that user is able to go back to the menu by clicking it
    public static JButton backButton() {
        JButton back = new JButton("Back");
        back = customizeButton(back);
        back.setBounds(CalendarUI.WIDTH / 2 - 40, CalendarUI.HEIGHT - 150,
                100, 50);
        Font backFont = new Font(back.getFont().getName(),
                back.getFont().getStyle(), 24);
        back.setFont(backFont);
        back.addActionListener(new BackToMenuClickListener());
        try {
            CalendarUI.calendar.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return back;
    }


}
