package ui;

import placeholder.model.Calendar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalendarUI extends JFrame {

    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    static JPanel caseArea = new JPanel();
    public List<Case> cases;
    public static Calendar calendar;
    public Menu menu;

    // EFFECTS: launch the CalendarUI program with menu and fields initialised
    public CalendarUI() {
        super("Calendar");
        initialiseFields();
        createMenu();
    }

    private void initialiseFields() {
        calendar = new Calendar();
        cases = new ArrayList<>();
        menu = new Menu();
        try {
            calendar.load();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    // EFFECTS: create the JFrame layout
    public void createMenu() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createCase();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: create the JPanel layout with JButtons presenting all the options
    public void createCase() {
        GroupLayout layout = new GroupLayout(caseArea);
        caseArea.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        add(caseArea, BorderLayout.CENTER);
        chooseCase();
        JLabel welcome = new JLabel("<HTML><em><strong>Welcome to My Calendar!</strong></em></HTML>");
        welcome.setBounds(150, 80, 700, 100);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        Font welcomeFont = new Font(welcome.getFont().getName(), welcome.getFont().getStyle(), 50);
        welcome.setFont(welcomeFont);
        caseArea.add(welcome);
    }

    // EFFECTS: construct options that user can choose from in the menu
    public void chooseCase() {
        Case1 case1 = new Case1(this, caseArea);
        cases.add(case1);

        Case2 case2 = new Case2(this, caseArea);
        cases.add(case2);

        Case3 case3 = new Case3(this, caseArea);
        cases.add(case3);

        Case4 case4 = new Case4(this, caseArea);
        cases.add(case4);
    }


    public static void main(String[] args) {
        new CalendarUI();
    }


}
