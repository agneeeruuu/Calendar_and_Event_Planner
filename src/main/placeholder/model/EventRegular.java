package placeholder.model;

public class EventRegular extends Event {

    public EventRegular(int date, String category, String eventName) {
        super(date, category, eventName);
    }

    // EFFECTS: return the event details with 0 appended to the date if the event happens in Jan ~ Sep
    @Override
    public String printOutEvent() {
        if (eventDateToString(date).length() <= 4) {
            return category + " : " + eventName
                    + " is successfully added to 0" + eventDateToString(date);
        } else {
            return category + " : " + eventName
                    + " is successfully added to " + eventDateToString(date);
        }
    }

    // EFFECTS: return true since the event is a regular event
    @Override
    public boolean regular() {
        return true;
    }


}



