package seedu.address.model.event;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.event.exceptions.DateOutOfBoundsException;
import seedu.address.model.event.exceptions.InvalidEventPeriodException;

/**
 * Represents a period in time when an event will occur.
 */
public class EventPeriod implements Comparable<EventPeriod> {
    public static final String MESSAGE_CONSTRAINTS = "The start date and time and end date and time should "
            + "be in the format 'yyyy-MM-dd HH:mm' where:\n"
            + "    -'yyyy' is the year.\n"
            + "    -'MM' is the month.\n"
            + "    -'dd' is the day.\n"
            + "    -'HH:mm' is the time in 24-hour format.";
    public static final String PERIOD_INVALID = "The start date has to be before the end date.";
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");
    public static final LocalTime MAX_TIME_OF_DAY = LocalTime.MIDNIGHT.minusMinutes(1);

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an EventPeriod object with the given start and end date/time strings.
     *
     * @param startString The string representation of the start date and time.
     * @param endString The string representation of the end date and time.
     */
    public EventPeriod(String startString, String endString) {
        this(LocalDateTime.parse(startString, DATE_TIME_STRING_FORMATTER),
                LocalDateTime.parse(endString, DATE_TIME_STRING_FORMATTER));
    }

    /**
     * Constructs an EventPeriod object with the given start and end date/time LocalDateTime.
     *
     * @param start The LocalDateTime representation of the start date and time.
     * @param end The LocalDateTime representation of the end date and time.
     */
    private EventPeriod(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Creates and returns a new EventPeriod object with minimum date/time values.
     *
     * @return A new EventPeriod object with minimum date/time values.
     */
    public static EventPeriod createNonConflictingPeriod() {
        return new EventPeriod(LocalDateTime.MAX, LocalDateTime.MIN);
    }

    /**
     * Checks if the given start and end date/time strings form a valid period.
     *
     * @param startString The string representation of the start date and time.
     * @param endString The string representation of the end date and time.
     * @return True if the period is valid, false otherwise.
     */
    public static boolean isValidPeriod(String startString, String endString) {
        requireAllNonNull(startString, endString);

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        startDateTime = LocalDateTime.parse(startString, DATE_TIME_STRING_FORMATTER);
        endDateTime = LocalDateTime.parse(endString, DATE_TIME_STRING_FORMATTER);

        if (!startDateTime.isBefore(endDateTime)) {
            throw new InvalidEventPeriodException(PERIOD_INVALID);
        }
        return true;
    }

    /**
     * Checks if this EventPeriod overlaps with another EventPeriod.
     *
     * @param other The EventPeriod to check for overlap with.
     * @return True if there is an overlap, false otherwise.
     */
    public boolean isOverlapping(EventPeriod other) {
        requireNonNull(other);

        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

    /**
     * Checks if this EventPeriod overlaps with the given start date and end date.
     *
     * @param start start date.
     * @param end end date.
     * @return True if there is an overlap, false otherwise.
     */
    public boolean isOverlapping(LocalDate start, LocalDate end) {
        requireAllNonNull(start, end);

        boolean isThisEventStartingAfterOtherEventEnds = this.start.toLocalDate().isAfter(end);
        boolean isThisEventEndedBeforeOtherEventStarts = this.end.toLocalDate().isBefore(start);
        return !(isThisEventStartingAfterOtherEventEnds || isThisEventEndedBeforeOtherEventStarts);
    }

    /**
     * Checks if a specified {@code @LocalDateTime} is within the {@code @EventPeriod}.
     *
     * @param dateTime the specified {@code @LocalDateTime}.
     * @return True if it is within the period, false otherwise.
     */
    public boolean isWithin(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        return (start.isEqual(dateTime) || start.isBefore(dateTime)) && end.isAfter(dateTime);
    }

    /**
     * Compares this EventPeriod with another EventPeriod.
     *
     * @param other The EventPeriod to compare with.
     * @return 1 if this EventPeriod is after the other, -1 if it's before, 0 if they are the same.
     */
    @Override
    public int compareTo(EventPeriod other) {
        requireNonNull(other);
        if (this.start.isBefore(other.start)) {
            return -1;
        } else if (this.start.isEqual(other.start)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Get the dates the eventPeriod spans, stored in an arrayList.
     *
     * @return list of the dates the eventPeriod spans.
     */
    public List<LocalDate> getDates() {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        return Stream.iterate(startDate, date -> !date.isAfter(endDate), date -> date.plusDays(1))
                .collect(Collectors.toList());
    }

    /**
     * Get the string representation of the EventPeriod.
     *
     * @return string representation of the period the eventPeriod spans.
     */
    public String getFormattedPeriod() {
        String startString = start.format(DATE_TIME_STRING_FORMATTER);
        String endString = end.format(DATE_TIME_STRING_FORMATTER);
        return startString + " - " + endString;
    }

    /**
     * Get the string representation of the EventPeriod start DateTime.
     *
     * @return string representation of the start DateTime.
     */
    public String getFormattedStart() {
        return start.format(DATE_TIME_STRING_FORMATTER);
    }

    /**
     * Get the string representation of the EventPeriod end DateTime.
     *
     * @return string representation of the end DateTime.
     */
    public String getFormattedEnd() {
        return end.format(DATE_TIME_STRING_FORMATTER);
    }

    /**
     * Checks if another {@code @EventPeriod} occurs within a minute with this {@code @EventPeriod}.
     *
     * @param other the other {@code @EventPeriod}.
     * @return true if they are apart by a single minute.
     */
    public boolean isContinuous(EventPeriod other) {
        requireNonNull(other);

        return this.start.plusMinutes(1) == other.end || other.start.plusMinutes(1) == this.end;
    }

    /**
     * Bounds the {@code EventPeriod} such that the start and end lies within the specified {@code LocalDate}.
     *
     * @param date the specified {@code @LocalDate}.
     * @return new {@code @EventPeriod} with adjusted start and end times.
     */
    public EventPeriod boundPeriodByDate(LocalDate date) {
        requireNonNull(date);

        boolean isDateOnStartDate = date.isEqual(start.toLocalDate());
        boolean isDateOnEndDate = date.isEqual(end.toLocalDate());
        boolean isDateAfterStartDate = date.isAfter(start.toLocalDate());
        boolean isDateBeforeEndDate = date.isBefore(end.toLocalDate());


        if (isDateOnStartDate && isDateOnEndDate) {
            return this;
        } else if (isDateOnStartDate) {
            return new EventPeriod(start, LocalDateTime.of(date, MAX_TIME_OF_DAY));
        } else if (isDateOnEndDate) {
            return new EventPeriod(LocalDateTime.of(date, LocalTime.MIDNIGHT), end);
        } else if (isDateAfterStartDate && isDateBeforeEndDate) {
            return new EventPeriod(LocalDateTime.of(date, LocalTime.MIDNIGHT),
                    LocalDateTime.of(date, MAX_TIME_OF_DAY));
        } else {
            throw new DateOutOfBoundsException();
        }
    }

    /**
     * Compare the start time (independent of date) of this EventPeriod with another.
     *
     * @param other other EventPeriod object.
     * @return a negative integer if this EventPeriod has an earlier start time than the other, 0 if both have the same
     *     start time and a positive integer otherwise.
     */
    public int compareStartTime(EventPeriod other) {
        requireNonNull(other);

        return this.start.toLocalTime().compareTo(other.start.toLocalTime());
    }

    /**
     * Compare the end time (independent of date) of this EventPeriod with another.
     *
     * @param other other EventPeriod object.
     * @return a negative integer if this EventPeriod has an earlier end time than the other, 0 if both have the same
     *     end time and a positive integer otherwise.
     */
    public int compareEndTime(EventPeriod other) {
        requireNonNull(other);

        return this.end.toLocalTime().compareTo(other.end.toLocalTime());
    }

    /**
     * Get the start time as a LocalTime of the EventPeriod, omitting the date.
     *
     * @return the start time as a LocalTime of the EventPeriod.
     */
    public LocalTime getStartTime() {
        return start.toLocalTime();
    }

    /**
     * Get the end time as a LocalTime of the EventPeriod, omitting the date.
     *
     * @return the end time as a LocalTime of the EventPeriod.
     */
    public LocalTime getEndTime() {
        return end.toLocalTime();
    }

    /**
     * Get the duration of the EventPeriod stored in a LocalTime.
     *
     * @return duration of the EventPeriod stored in a LocalTime.
     */
    public Duration getDuration() {
        Duration periodDuration = Duration.between(start, end);
        if (end.toLocalTime().equals(MAX_TIME_OF_DAY)) {
            periodDuration = periodDuration.plusMinutes(1);
        }
        return periodDuration;
    }

    /**
     * Checks if the start and end LocalDateTime objects occur on the same date.
     *
     * @return true if they both occur on the same date, false otherwise.
     */
    public boolean isSingleDay() {
        return start.toLocalDate().isEqual(end.toLocalDate());
    }

    /**
     * Get the DayOfWeek of this EventPeriod. EventPeriod has to only span a single day.
     *
     * @return DayOfWeek of this EventPeriod.
     */
    public DayOfWeek getDayOfWeek() {
        assert(isSingleDay());

        return start.getDayOfWeek();
    }

    /**
     * Get the number of minutes elapsed from the input time to the start time of this EventPeriod.
     *
     * @param time input time.
     * @return minutes elapsed from the input time to the start time of the EventPeriod.
     */
    public long getMinutesFromTimeToStartTime(LocalTime time) {
        return MINUTES.between(time, getStartTime());
    }
    @Override
    public boolean equals(Object other) {
        requireNonNull(other);
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventPeriod)) {
            return false;
        }

        EventPeriod otherEventPeriod = (EventPeriod) other;
        return otherEventPeriod.start.isEqual(this.start) && otherEventPeriod.end.isEqual(this.end);
    }

    @Override
    public String toString() {
        return "start: "
                + this.start.format(DATE_TIME_STRING_FORMATTER)
                + "; end: "
                + this.end.format(DATE_TIME_STRING_FORMATTER);
    }
}
