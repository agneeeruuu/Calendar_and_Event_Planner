package placeholder.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import placeholder.model.EventImportant;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class EventImportantTest extends EventTest {

    @BeforeEach
    public void setup() {
        testEvent = new EventImportant(1018, "SCHOOL", "COOP Interview");
        testEvent2 = new EventImportant(503, "FAMILY", "Ian's Birthday");
    }

    @Test
    public void printOutEventTest() throws ParseException {
        assertEquals("!!! SCHOOL : COOP Interview is successfully added to 10/18",
                testEvent.printOutEvent());
        assertEquals("!!! FAMILY : Ian's Birthday is successfully added to 05/03", testEvent2.printOutEvent());
    }

    @Test
    public void regularTest() {
        assertFalse(testEvent.regular());
    }
}


