package seedu.address.model.event;

public class EventBuilder {
    public static Event buildEvent(String description, String startTimeString, String endTimeString) {
        EventDescription eventDescription = new EventDescription(description);
        EventPeriod eventPeriod = new EventPeriod(startTimeString, endTimeString);
        return new Event(eventDescription, eventPeriod);
    }
}
