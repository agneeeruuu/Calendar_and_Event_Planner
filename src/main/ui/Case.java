package ui;

import javax.swing.*;

public abstract class Case extends JFrame {

    protected JButton button;
    private boolean active;
    protected CalendarUI calendarUI;
    public static int WIDTH = CalendarUI.WIDTH / 2;
    public static int HEIGHT = 80;
    public static int X = 250;
    public static int Y = (CalendarUI.HEIGHT - HEIGHT * 4) / 5;
    public static int FONT_SIZE = 24;

    // EFFECTS: construct the abstract case button
    public Case(CalendarUI calendarUI, JComponent parent) {
        this.calendarUI = calendarUI;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected static JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

}