package seedu.address.model.event;

/**
 * The class for holding an Event
 */
public class Event {
    private final EventTime start;
    private final EventTime end;
    private final EventName name;

    private final EventLocation location;

    private final EventInformation information;

    /**
     * Constructor for the `Event` class
     * @param name The event name
     * @param start The start time of the event
     * @param end The end time of the event
     * @param location The location of the event
     * @param information The information of the event
     */
    public Event(EventName name, EventTime start, EventTime end, EventLocation location, EventInformation information) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;
        this.information = information;
    }

    /**
     * Constructor for the `Event` class, with Strings as parameters
     * @param name The event name
     * @param start The start time of the event
     * @param end The end time of the event
     * @param location The location of the event
     * @param information The information of the event
     */
    public Event(String name, String start, String end, String location, String information) {
        this.name = EventName.fromString(name);
        this.start = EventTime.fromString(start);
        this.end = EventTime.fromString(end);
        this.location = EventLocation.fromString(location);
        this.information = EventInformation.fromString(information);
    }

    /**
     * Get the name of the event
     * @return The name of the event
     */
    public String getName() {
        return name.toString();
    }

    /**
     * Get the start time of the event, represented in string
     * @return The start time in string
     */
    public String getStartString() {
        // Temporary, can use Util class instead
        return this.start.toString();
    }

    /**
     * Get the end time of the event, represented in string
     * @return The end time in string
     */
    public String getEndString() {
        return this.end.toString();
    }

    /**
     * Get the location of the event, represented in string
     * @return The location in string
     */
    public String getLocationStr() {
        return this.location.toString();
    }

    /**
     * Get the information of the event, represented in string
     * @return The information in string
     */
    public String getInformationStr() {
        return this.information.toString();
    }

    /**
     * Get a string that can be used to represent this event on GUI
     * @return The information in string
     */
    public String getUiText() {
        String result = this.getName() + " ( Starts at: " + this.start;
        if (!this.end.toString().isEmpty()) {
            result += ", Ends at: " + this.end;
        }
        if (!this.location.toString().isEmpty()) {
            result += ", Location: " + this.location;
        }
        if (!this.information.toString().isEmpty()) {
            result += ", Information: " + this.information;
        }
        result += " )";
        return result;
    }
}
