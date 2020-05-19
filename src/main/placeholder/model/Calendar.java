package placeholder.model;

import placeholder.model.exception.InvalidCategoryException;
import placeholder.model.exception.InvalidDateException;
import placeholder.model.exception.InvalidEventNameException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.*;


import static java.nio.file.Files.readAllLines;

public class Calendar implements Saveable, Loadable {

    private ArrayList<Event> allEvents;
    private DateMap map;

    public Calendar() {
        allEvents = new ArrayList<>();
        map = new DateMap();
    }

    // getters
    // EFFECTS: return event list stored in calendar
    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }

    // REQUIRES: events must be valid (are already constructed)
    // MODIFIES: this
    // EFFECTS: adding event into the Calendar (event list) and the date map
    public void addEvent(Event e) {
        allEvents.add(e);
        map.addEventToDate(e);
    }

    // EFFECTS: return the number of events in the Calendar
    public int numberOfEvents() {
        return allEvents.size();
    }

    // EFFECTS: return true if the given event is in the calendar (list of events)
    public boolean containEvent(Event e) {
        return allEvents.contains(e);
    }

    // REQUIRES: file destination exists and have information needed
    // MODIFIES: this
    // EFFECTS: load the events from the file destination to the program
    @Override
    public void load() throws IOException {
        List<String> lines = readAllLines(Paths.get("saveFile.sav"));
        for (String line : lines) {
            String[] part = line.split(" ");
            String regularOrImportant = part[0];
            int eventDate = Integer.parseInt(part[1]);
            String eventCategory = part[2];
            String eventName = combineName(part);

            if (regularOrImportant.equals("R")) {
                Event event = new EventRegular(eventDate, eventCategory, eventName);
                addEvent(event);
            } else if (regularOrImportant.equals("I")) {
                Event event = new EventImportant(eventDate, eventCategory, eventName);
                addEvent(event);
            }
        }
    }

    // EFFECTS: combine String[] from the third part till the end
    public String combineName(String[] part) {

        String combined = "";

        for (int i = 3; i < part.length; i++) {
            if (combined.length() >= 1) {
                combined = combined + " " + part[i];
            } else {
                combined = part[i];
            }
        }

        return combined;
    }

    // REQUIRES: file destination exists
    // EFFECTS: save the events to the file destination
    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("saveFile.sav", "UTF-8");

        for (Event event : allEvents) {
            writer.println(saveHelper(event));
        }

        writer.close();
    }

    // EFFECTS: append R/ I to the event details to be stored if the event is Regular/ Important
    //          append 0 if the event date is in January ~ September (101 to 930)
    public String saveHelper(Event event) {
        if (event.regular()) {
            if (event.getEventDate() <= 1000) {
                return ("R" + " 0" + event.getEventDate() + " " + event.getEventCategory()
                        + " " + event.getEventName());
            } else {
                return ("R" + " " + event.getEventDate() + " " + event.getEventCategory() + " " + event.getEventName());
            }
        } else {
            if (event.getEventDate() <= 1000) {
                return ("I" + " 0" + event.getEventDate() + " " + event.getEventCategory()
                        + " " + event.getEventName());
            } else {
                return ("I" + " " + event.getEventDate() + " " + event.getEventCategory() + " " + event.getEventName());
            }
        }
    }

    // EFFECTS: return all the events (toString) currently stored in the calendar
    public String printAllEventsInTheCalendar() {
        String reminder = "";

        for (Event event : allEvents) {
            if (event.getEventDate() <= 1000) {
                reminder = reminder + ("\n0" + event.eventDateToString(event.date)
                        + " " + event.category + " : " + event.eventName);
            } else {
                reminder = reminder + ("\n" + event.eventDateToString(event.date) + " "
                        + event.category + " : " + event.eventName);
            }
        }

        return reminder;
    }

    // EFFECTS: throw InvalidEventNameException if the length of eventName <= 3
    public String processName(String eventName) throws InvalidEventNameException {
        if (eventName.length() <= 3) {
            throw new InvalidEventNameException();
        }
        return eventName;
    }

    // EFFECTS: throw InvalidCategoryException if the length of eventCategory <= 3
    public String processCategory(String eventCategory) throws InvalidCategoryException {
        if (eventCategory.length() <= 3) {
            throw new InvalidCategoryException();
        }
        return eventCategory;
    }

    // EFFECTS: throw InvalidEventDateException if the eventDate is not a proper date
    public int processDate(int eventDate) throws InvalidDateException {
        if ((Integer.valueOf(eventDate) >= 101 && Integer.valueOf(eventDate) <= 131)
                || (Integer.valueOf(eventDate) >= 201 && Integer.valueOf(eventDate) <= 229)
                || (Integer.valueOf(eventDate) >= 301 && Integer.valueOf(eventDate) <= 331)
                || (Integer.valueOf(eventDate) >= 401 && Integer.valueOf(eventDate) <= 430)
                || (Integer.valueOf(eventDate) >= 501 && Integer.valueOf(eventDate) <= 531)
                || (Integer.valueOf(eventDate) >= 601 && Integer.valueOf(eventDate) <= 630)
                || (Integer.valueOf(eventDate) >= 701 && Integer.valueOf(eventDate) <= 731)
                || (Integer.valueOf(eventDate) >= 801 && Integer.valueOf(eventDate) <= 831)
                || (Integer.valueOf(eventDate) >= 901 && Integer.valueOf(eventDate) <= 930)
                || (Integer.valueOf(eventDate) >= 1001 && Integer.valueOf(eventDate) <= 1031)
                || (Integer.valueOf(eventDate) >= 1101 && Integer.valueOf(eventDate) <= 1130)
                || (Integer.valueOf(eventDate) >= 1201 && Integer.valueOf(eventDate) <= 1231)) {
            return eventDate;
        } else {
            throw new InvalidDateException();
        }
    }

    // getter
    // EFFECTS: return the event map (stored in dates)
    public DateMap getEventsMap() {
        return map;
    }
}
