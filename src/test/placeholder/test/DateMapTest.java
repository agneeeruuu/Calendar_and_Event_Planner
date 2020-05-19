package placeholder.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import placeholder.model.DateMap;
import placeholder.model.Event;
import placeholder.model.EventImportant;
import placeholder.model.EventRegular;
import static org.junit.jupiter.api.Assertions.*;


public class DateMapTest {

    private DateMap map;
    private Event testEvent;

    @BeforeEach
    public void setUp() {
        map = new DateMap();
        testEvent = new EventRegular(717, "BIRTHDAY", "My Birthday");
    }

    @Test
    public void addEventToDateTest() {
        map.addEventToDate(testEvent);
        assertTrue(map.getDateMap().containsKey("0" + Integer.toString(testEvent.getEventDate())));
        assertEquals(1, map.getDateMap().keySet().size());

        Event testEvent1 = new EventRegular(717,"WORK", "YiFang");
        map.addEventToDate(testEvent1);

        assertTrue(map.getDateMap().containsKey("0" + Integer.toString(testEvent1.getEventDate())));
        assertEquals(1, map.getDateMap().keySet().size());
    }

    @Test
    public void busyOrNotTest() {
        map.addEventToDate(testEvent);
        assertEquals("Not too busy eh?",
                map.busyOrNot("0717"));

        Event event2 = new EventRegular(717, "WORK", "Shift 17:00 - 21:00");
        map.addEventToDate(event2);
        assertEquals("Not too busy eh?",
                map.busyOrNot("0717"));

        Event event3 = new EventImportant(717, "FAMILY", "Mom's Visiting");
        map.addEventToDate(event3);
        assertEquals("A busy day for you buddy. Good Luck!",
                map.busyOrNot("0717"));
    }
}
