package placeholder.model;

import java.util.*;

public class DateMap extends Observable {
    private Map<String, List<Event>> eventsMap;

    public DateMap() {
        eventsMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: add event to the date map
    public void addEventToDate(Event event) {

        String date;

        if (event.getEventDate() <= 1000) {
            date = "0" + Integer.toString(event.getEventDate());
        } else {
            date = Integer.toString(event.getEventDate());
        }

        if (!eventsMap.containsKey(date)) {
            eventsMap.put(date, new ArrayList<>());
        }
        eventsMap.get(date).add(event);
    }

    // EFFECTS: return greetings corresponding to how many events are there in a given date
    //          consider busy if there are three or more events in a given date
    public String busyOrNot(String date) {
        if (eventsMap.get(date).size() >= 3) {
            return "A busy day for you buddy. Good Luck!";
        } else {
            return "Not too busy eh?";
        }
    }

    // getter
    // EFFECTS: return the DateMap (eventsMap)
    public Map getDateMap() {
        return eventsMap;
    }
}
