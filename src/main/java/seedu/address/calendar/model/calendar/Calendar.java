package seedu.address.calendar.model.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.List;

import seedu.address.calendar.model.calendar.calendarexceptions.EventConflictException;
import seedu.address.calendar.model.event.Event;


public class Calendar {
    private NavigableMap<LocalDateTime, Event> eventTree;

    public Calendar() {
        this.eventTree = new TreeMap<LocalDateTime, Event>();
    }

    public String addEvent(Event event) throws EventConflictException {
        LocalDateTime eventStartDateTime = event.getStartDateTime();
        LocalDateTime eventEndDateTime = event.getEndDateTime();

        Event precedingEvent = this.eventTree.floorEntry(eventStartDateTime).getValue();
        Event proceedingEvent = this.eventTree.higherEntry(eventEndDateTime).getValue();

        if (precedingEvent.isConflicting(event) || proceedingEvent.isConflicting(event)) { // Handled by Parser
            throw new EventConflictException();
        }
        this.eventTree.put(eventStartDateTime, event);
        return "Event added successfully!";
    }

    public String changeEventStartDate
}
