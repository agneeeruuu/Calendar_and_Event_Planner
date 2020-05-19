package placeholder.model;

import java.util.*;

public abstract class Event {
    protected int date;
    protected String category;
    protected String eventName;

    public Event(int date, String category, String eventName) {
        this.date = date;
        this.category = category;
        this.eventName = eventName;
    }

    // EFFECTS: return the event with name, category, and date
    public abstract String printOutEvent();

    // EFFECTS: turn event date into the corresponding String representation, for example, 09/01 as Sep 1st
    //          append 0 at front if the event is in January ~ September (101 ~ 930)
    public String eventDateToString(int eventDate) {
        String str = String.valueOf(eventDate);
        if (str.length() <= 3) {
            return str.charAt(0) + "/" + str.substring(1);
        } else {
            return str.substring(0, 2) + "/" + str.substring(2);
        }
    }

    // getter
    // EFFECTS: return event date
    public int getEventDate() {
        return date;
    }

    // getter
    // EFFECTS: return the event category
    public String getEventCategory() {
        return category;
    }

    // getter
    // EFFECTS: return the event name
    public String getEventName() {
        return eventName;
    }

    // EFFECTS: return true is the event is equal to the other in date and category and name
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return date == event.date
                && Objects.equals(category, event.category)
                && Objects.equals(eventName, event.eventName);
    }

    // EFFECTS: return the hashCode of the given event
    @Override
    public int hashCode() {
        return Objects.hash(date, category, eventName);
    }

    // EFFECTS: return event details in string format
    @Override
    public String toString() {
        return printOutEvent();
    }

    // EFFECTS: return true if the event is a regular event, false if the event is an important event
    public abstract boolean regular();
}
