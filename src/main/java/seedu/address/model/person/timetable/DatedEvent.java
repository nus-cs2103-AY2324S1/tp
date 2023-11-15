package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a DatedEvent in the application.
 * Encapsulates an event that is not recurring on a weekly basis.
 * Contains details such as the event name, time block, date, and reminder settings.
 */
public class DatedEvent extends TimeBlock {
    public static final String MESSAGE_CONSTRAINTS =
            "Input should be in the format 'name YYYY-MM-DD HHMM HHMM yes/no', \n"
                    + "where:\n"
                    + "- 'name' represents the name and should not contain spaces.\n"
                    + "- 'YYYY-MM-DD' represents a date (e.g., '2023-10-24').\n"
                    + "- 'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300). \n"
                    + "- The first 'HHMM' represents the starting time (e.g., '0830' for 08:30 AM).\n"
                    + "- The second 'HHMM' represents the ending time (e.g., '1730' for 05:30 PM).\n"
                    + "- y/n represents whether you want a reminder for this event.";
    public static final String DATE_TIME_VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    protected static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd";
    private final String name;
    private final LocalDate date;
    private final boolean hasReminder;

    /**
     * Initializes a new DatedEvent with the provided details.
     *
     * @param name The name of the event.
     * @param timeBlockString The time block for the event.
     * @param dateString The date of the event in the format "YYYY-MM-DD".
     */
    public DatedEvent(String name, String timeBlockString, String dateString, boolean reminder) {
        super(timeBlockString);
        requireNonNull(name);
        checkArgument(isValidDateTimeString(dateString), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN));
        this.hasReminder = reminder;
    }

    /**
     * Initializes a new DatedEvent with the provided details.
     *
     * @param name The name of the event.
     * @param timeBlockString The time block for the event.
     * @param date The date of the event.
     */
    public DatedEvent(String name, String timeBlockString, LocalDate date, boolean reminder) {
        super(timeBlockString);
        requireNonNull(name);
        this.name = name;
        this.date = date;
        this.hasReminder = reminder;
    }

    /**
     * Factory method to create a new DatedEvent object from a given unparsed input string.
     *
     * The expected format for the input is: "EVENT_NAME DATE YYYY-MM-DD START_TIME END_TIME REMINDER_STATUS"
     * Where:
     * <ul>
     *   <li>EVENT_NAME is the name of the event and can contain spaces.</li>
     *   <li>DATE in 'YYYY-MM-DD' format represents the date of the event.</li>
     *   <li>START_TIME and END_TIME in 'HHMM' format represent the start and end times of the event respectively.</li>
     *   <li>REMINDER_STATUS is 'y' if a reminder is set for the event, 'n' otherwise.</li>
     * </ul>
     *
     * Example input: "meet Andre 2023-10-10 1030 1130 y"
     *
     * @param unparsedInput The input string containing DatedEvent details.
     * @return A new DatedEvent object.
     * @throws IllegalArgumentException If the given input does not adhere to the expected format.
     */
    public static DatedEvent newDatedEvent(String unparsedInput) { // e.g., "meet Andre 2023-10-10 1030 1130 y"
        requireNonNull(unparsedInput);

        // Regex to capture the name, date, start time, and end time
        String regex = "^(.+) (\\d{4}-\\d{2}-\\d{2}) (\\d{4}) (\\d{4}) (y|n)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unparsedInput);

        checkArgument(matcher.matches(), MESSAGE_CONSTRAINTS);

        // Extracting components using the matcher
        String nameString = matcher.group(1); // event name
        String dateString = matcher.group(2); // date
        String startTime = matcher.group(3); // start time
        String endTime = matcher.group(4); // end time
        boolean reminder = matcher.group(5).equals("y"); // reminder status

        checkArgument(isValidDateTimeString(dateString), MESSAGE_CONSTRAINTS);

        // Parse the date
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN));
        String dayOfWeek = date.getDayOfWeek().name();

        // Create the time block
        String timeBlockString = dayOfWeek + " " + startTime + " " + endTime;
        checkArgument(isValidTimeBlock(timeBlockString), MESSAGE_CONSTRAINTS);

        return new DatedEvent(nameString, timeBlockString, dateString, reminder);
    }

    /**
     * Checks if the provided date time string is valid.
     *
     * @param test The date time string to check.
     * @return true if the string is a valid date time format, false otherwise.
     */
    public static boolean isValidDateTimeString(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean isDatedEvent() {
        return true;
    }

    public boolean hasReminder() {
        return hasReminder;
    }

    @Override
    public String toString() {
        return "DatedEvent: [" + name + "] on " + date.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN))
                + " " + super.toString() + " Reminder: " + (hasReminder ? "Yes" : "No");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Event";
    }

    public String getStringForReminder() {
        return name + " " + super.getTimeBlockString();
    }

    /**
     * Checks if this event's date is before the given date.
     * @param date The date to compare with.
     * @return true if this event's date is before the given date.
     */
    public boolean isBefore(LocalDate date) {
        return this.date.isBefore(date);
    }

    /**
     * Checks if this event overlaps with another event.
     * This method assumes that two events overlap if they are on the same date.
     * @param event The event to compare with.
     * @return true if the events overlap.
     */
    public boolean isOverlap(DatedEvent event) {
        if (!this.date.equals(event.date)) {
            return false;
        }
        int thisStartTime = Integer.parseInt(this.getStartTime());
        int thisEndTime = Integer.parseInt(this.getEndTime());
        int eventStartTime = Integer.parseInt(event.getStartTime());
        int eventEndTime = Integer.parseInt(event.getEndTime());

        return thisStartTime < eventEndTime && thisEndTime > eventStartTime;
    }

    @Override
    public boolean equals(Object e) {
        if (e == this) {
            return true;
        } else if (!(e instanceof DatedEvent)) {
            return false;
        } else {
            DatedEvent other = (DatedEvent) e;
            return other.getName().equals(getName())
                    && other.getDate().equals(getDate())
                    && other.getTimeBlockString().equals(getTimeBlockString())
                    && other.hasReminder() == hasReminder();
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
