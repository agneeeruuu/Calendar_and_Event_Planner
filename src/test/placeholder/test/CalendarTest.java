package placeholder.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import placeholder.model.Calendar;
import placeholder.model.EventImportant;
import placeholder.model.EventRegular;
import placeholder.model.exception.InvalidDateException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;


import static java.nio.file.Files.readAllLines;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {

    private EventRegular testEvent;
    private Calendar testCalendar;
    private EventImportant testEvent2;
    private EventRegular testEvent3;
    private EventImportant testEvent4;



    @BeforeEach
    public void setUp() {
        testEvent = new EventRegular(717, "BIRTHDAY", "My Birthday");
        testCalendar = new Calendar();
        testEvent2 = new EventImportant(1127, "SCHOOL", "Project Demo");
        testEvent3 = new EventRegular(1127, "OTHER", "Transit Strike");
        testEvent4 = new EventImportant(210, "FAMILY", "Dad's Birthday");
    }

    @Test
    public void addEventTest() {
        testCalendar.addEvent(testEvent);
        assertEquals(1, testCalendar.numberOfEvents());
        assertTrue(testCalendar.containEvent(testEvent));
    }

    @Test
    public void numberOfEventsTest() {
        assertEquals(0, testCalendar.numberOfEvents());
        testCalendar.addEvent(testEvent);
        assertEquals(1, testCalendar.numberOfEvents());
        testCalendar.addEvent(testEvent);
        testCalendar.addEvent(testEvent);
        assertEquals(3, testCalendar.numberOfEvents());
        assertEquals(3, testCalendar.getAllEvents().size());

        assertTrue(testCalendar.getEventsMap().getDateMap().
                containsKey("0" + testEvent.getEventDate()));
    }

    @Test
    public void containsEventTest() {
        assertFalse(testCalendar.containEvent(testEvent));
        testCalendar.addEvent(testEvent);
        assertTrue(testCalendar.containEvent(testEvent));
    }

    @Test
    public void loadTest() throws IOException {
        testCalendar.load();
        List<String> lines = readAllLines(Paths.get("saveFile.sav"));
        lines.add("I 1016 SCHOOL Accounting Midterm");
        lines.add("R 1113 SCHOOL Comm 205 ICE due");
        lines.add("R 1231 HOLIDAY New Year's Eve");
        assertTrue(lines.contains("I 1016 SCHOOL Accounting Midterm"));
        assertEquals(3, lines.size());
    }

    @Test
    public void saveTest() throws IOException {
        testCalendar.save();
        List<String> lines = Files.readAllLines(Paths.get("saveFile.sav"));
        lines.add("I 1016 SCHOOL Accounting Midterm");
        lines.add("R 1113 SCHOOL Comm 205 ICE due");
        lines.add("R 1231 HOLIDAY New Year's Eve");
        assertEquals(3, lines.size());
        assertTrue(lines.contains("R 1231 HOLIDAY New Year's Eve"));
    }

    @Test
    public void saveHelperTest() {
        assertEquals("R 0717 BIRTHDAY My Birthday", testCalendar.saveHelper(testEvent));
        assertEquals("R 1127 OTHER Transit Strike", testCalendar.saveHelper(testEvent3));
        assertEquals("I 1127 SCHOOL Project Demo", testCalendar.saveHelper(testEvent2));
        assertEquals("I 0210 FAMILY Dad's Birthday", testCalendar.saveHelper(testEvent4));
    }

    @Test
    public void combineNameTest() {
        String[] testString = {"I", "1130", "WORK", "Shift" , "10am", "-", "9pm"};
        assertEquals("Shift 10am - 9pm", testCalendar.combineName(testString));
        String[] testString2 = {"R", "1116", "ENTERTAINMENT", "Gym"};
        assertEquals("Gym", testCalendar.combineName(testString2));
    }


    @Test
    public void printAllEventsInTheCalendarTestNoEvents() {
        testCalendar.addEvent(testEvent);
        testCalendar.addEvent(testEvent2);
        assertEquals("\n07/17 BIRTHDAY : My Birthday"
                +"\n11/27 SCHOOL : Project Demo", testCalendar.printAllEventsInTheCalendar());
    }

    @Test
    public void processDateTest() {
        ;
        try {
            testCalendar.processDate(100);
            testCalendar.processDate(132);
            testCalendar.processDate(200);
            testCalendar.processDate(230);
            testCalendar.processDate(300);
            testCalendar.processDate(332);
            testCalendar.processDate(400);
            testCalendar.processDate(431);
            testCalendar.processDate(500);
            testCalendar.processDate(532);
            testCalendar.processDate(600);
            testCalendar.processDate(631);
            testCalendar.processDate(700);
            testCalendar.processDate(732);
            testCalendar.processDate(800);
            testCalendar.processDate(832);
            testCalendar.processDate(900);
            testCalendar.processDate(931);
            testCalendar.processDate(1000);
            testCalendar.processDate(1032);
            testCalendar.processDate(1100);
            testCalendar.processDate(1131);
            testCalendar.processDate(1200);
            testCalendar.processDate(1232);
            fail();
        } catch (InvalidDateException e) {
        }

        try {
            testCalendar.processDate(101);
            testCalendar.processDate(131);
            testCalendar.processDate(201);
            testCalendar.processDate(229);
            testCalendar.processDate(301);
            testCalendar.processDate(331);
            testCalendar.processDate(401);
            testCalendar.processDate(430);
            testCalendar.processDate(501);
            testCalendar.processDate(531);
            testCalendar.processDate(601);
            testCalendar.processDate(630);
            testCalendar.processDate(701);
            testCalendar.processDate(731);
            testCalendar.processDate(801);
            testCalendar.processDate(831);
            testCalendar.processDate(901);
            testCalendar.processDate(930);
            testCalendar.processDate(1001);
            testCalendar.processDate(1031);
            testCalendar.processDate(1101);
            testCalendar.processDate(1130);
            testCalendar.processDate(1201);
            testCalendar.processDate(1231);
        } catch (InvalidDateException e) {
            fail();
        }
    }
}
