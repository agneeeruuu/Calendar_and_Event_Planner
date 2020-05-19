package placeholder.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import placeholder.model.Calendar;
import placeholder.model.Event;
import placeholder.model.EventImportant;
import placeholder.model.EventRegular;
import placeholder.model.exception.InvalidCategoryException;
import placeholder.model.exception.InvalidEventNameException;

import static org.junit.jupiter.api.Assertions.fail;


public class ExceptionTest {

    Event regularEvent;
    Event importantEvent;
    Calendar calendar;

    @BeforeEach
    public void setUp() {
        regularEvent = new EventRegular(1025, "BIRTHDAY", "Claire's Birthday");
        importantEvent = new EventImportant(1225, "HOLIDAY", "Christmas");
        calendar = new Calendar();
        calendar.addEvent(regularEvent);
        calendar.addEvent(importantEvent);
    }

    @Test
    public void invalidNameExceptionExpected() {
        try {
            calendar.processName("M");
            fail();
        } catch (InvalidEventNameException e) {
        }
    }

    @Test
    public void invalidNameExceptionNotExpected() {
        try {
            calendar.processName(regularEvent.getEventName());
        } catch (InvalidEventNameException e) {
            fail();
        }
    }

    @Test
    public void invalidCategoryExceptionExpected() {
        try {
            calendar.processCategory("UU");
            fail();
        } catch (InvalidCategoryException e) {
        }
    }

    @Test
    public void invalidCategoryExceptionNotExpected() {
        try {
            calendar.processCategory(regularEvent.getEventCategory());
        } catch (InvalidCategoryException e) {
            fail();
        }
    }



}
