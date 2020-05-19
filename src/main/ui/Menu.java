package ui;

import placeholder.model.*;
import placeholder.model.exception.InvalidCategoryException;
import placeholder.model.exception.InvalidDateException;
import placeholder.model.exception.InvalidEventNameException;
import ui.web.HolidayInfo;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Calendar calendarExample;
    private Event eventExample;


    // this class is originally used for interacting with user input in the console
    // now that I have a GUI, all the methods here are kept private and are not being called
    public Menu() {
        calendarExample = new Calendar();
        try {
            calendarExample.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printMenu() {
        System.out.println("\n\n\nselect 1: add a new event\n" + "select 2: list all events for a date\n"
                + "select 3: check out national holidays this year\n" + "select 4: Quit\n");
    }

    //EFFECTS: parses user input until user quits
    private void handleUserInput() {
        printMenu();
        Scanner input = new Scanner(System.in);
        parseInput(input.nextInt());
    }

    private void parseInput(int i) {
        switch (i) {
            case 1:
                case1();
                break;
            case 2:
                case2();
                break;
            case 3:
                case3();
                break;
            case 4:
                endProgram();
                break;
            default:
                System.out.println("Sorry! I don't understand. Please re-enter your choice from 1 to 4");
                handleUserInput();
                break;
        }
    }

    private void case1() {

        System.out.println("Would you like to add an important event (Enter I) or regular event (Enter R)?");
        Scanner c = new Scanner(System.in);
        String importantOrRegular = c.nextLine();

        if (importantOrRegular.equals("I")) {
            System.out.println("Let's create an important event");
            eventSetUp("I");
        } else if (importantOrRegular.equals("R")) {
            System.out.println("Let's create a regular event");
            eventSetUp("R");
        } else {
            System.out.println("ERROR: Please enter only I or R!!!");
            case1();
        }
        quitOrContinue();
    }

    private void eventSetUp(String str) {
        int eventDate = checkDate();
        String eventCategory = checkCategory();
        String eventName = checkName();
        constructEventInCalendar(str, eventDate, eventCategory, eventName);
    }

    private void constructEventInCalendar(String str, int eventDate, String eventCategory, String eventName) {
        if (str.equals("R")) {
            eventExample = new EventRegular(eventDate, eventCategory, eventName);
        } else {
            eventExample = new EventImportant(eventDate, eventCategory, eventName);
        }
        calendarExample.addEvent(eventExample);
        System.out.println(eventExample.printOutEvent());
    }

    private String checkCategory() {
        System.out.println("please enter event category: ");
        Scanner category = new Scanner(System.in);
        String eventCategory = category.nextLine();
        try {
            calendarExample.processCategory(eventCategory);
        } catch (InvalidCategoryException e) {
            System.out.println("Ooops! Can you start over and enter a meaningful event category next time?");
            case1();
        }
        return eventCategory;
    }

    private String checkName() {
        System.out.println("please enter event name: ");
        Scanner name = new Scanner(System.in);
        String eventName = name.nextLine();
        try {
            calendarExample.processName(eventName);
        } catch (InvalidEventNameException e) {
            System.out.println("Ooops! Can you start over and enter a meaningful event name next time?");
            case1();
        }
        return eventName;
    }

    private int checkDate() {
        System.out.println("please enter event date as 4-digit number MMDD: ");
        Scanner date = new Scanner(System.in);
        int eventDate = date.nextInt();
        try {
            calendarExample.processDate(eventDate);
        } catch (InvalidDateException e) {
            System.out.println("Ooops! can you enter a 4 digit number MMDD as your event date (e.g., 1113 as Nov 13)?");
            case1();
        }
        return eventDate;
    }

    private void case2() {
        System.out.println("enter the date you want to look into in integer format XXXX");
        Scanner scanner = new Scanner(System.in);
        int eventDate = scanner.nextInt();

        List<Event> events = (List<Event>) calendarExample.getEventsMap().getDateMap().get(Integer.toString(eventDate));

        System.out.println("You have....\n");
        if (events != null) {
            for (Event event : events) {
                System.out.println(event.getEventCategory() + " : " + event.getEventName());
            }
            System.out.println("\n" + calendarExample.getEventsMap().busyOrNot(Integer.toString(eventDate)));
        } else {
            System.out.println("There's no events added yet!");
        }
        quitOrContinue();
    }

    private void case3() {
        try {
            System.out.println("preparing...\n\n");
            HolidayInfo.printHolidays();
        } catch (IOException e) {
            System.out.println("We are currently unable to provide you with the information...");
        } finally {
            quitOrContinue();
        }
    }

    private void quitOrContinue() {
        System.out.println("\nDo you wish to quit (enter Q) or go back to the menu (enter M)?");

        Scanner input = new Scanner(System.in);
        String quitOrContinue = input.nextLine();
        if (quitOrContinue.equals("M")) {
            handleUserInput();
        } else if (quitOrContinue.equals("Q")) {
            endProgram();
        } else {
            System.out.println("\nSorry I don't understand!");
            handleUserInput();
        }
    }

    private void endProgram() {
        System.out.println("Quitting...");
        try {
            calendarExample.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            printReminder();
        } finally {
            printReminder();
        }
    }

    // EFFECTS: print out the reminder and determine whether busy or not
    public String printReminder() {
        if (calendarExample.numberOfEvents() >= 1) {
            return ("\nJust a reminder that you still have the following events in your calendar!\n")
                    + calendarExample.printAllEventsInTheCalendar()
                    + ("\n\nHave a great day!");
        } else {
            return "There's nothing added yet!" + "\nHave a great day!";
        }
    }

}
