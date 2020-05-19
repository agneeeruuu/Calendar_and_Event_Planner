package placeholder.test;

import org.junit.jupiter.api.Test;
import placeholder.model.Event;
import placeholder.model.EventImportant;
import placeholder.model.EventRegular;

import static org.junit.jupiter.api.Assertions.*;

public abstract class EventTest {

    Event testEvent;
    Event testEvent2;

    @Test
    public void getEventDateTest() {
        assertEquals(1018, testEvent.getEventDate());
    }

    @Test
    public void getEventCategoryTest() {
        assertEquals("SCHOOL", testEvent.getEventCategory());
    }

    @Test
    public void comparingEvents() {
        Event event1 = new EventRegular(1005, "SCHOOL", "Interview");
        Event event2 = new EventRegular(1015, "SCHOOL", "Interview");
        Event event3 = new EventRegular(1005, "SCHOOL", "Interview");
        Event event4 = new EventImportant(1005, "SCHOOL", "Interview");
        Event event5 = new EventRegular(1005, "FAMILY", "Interview");
        Event event6 = new EventRegular(1005, "SCHOOL", "Movie Night");
        assertTrue(event1.equals(event3));
        assertFalse(event1.equals(event2));
        assertEquals(event1.hashCode(),event3.hashCode());
        assertFalse(event1.equals(null));
        assertFalse(event1.equals(event4));
        assertFalse(event1.equals(event5));
        assertFalse(event1.equals(event6));
    }


}
