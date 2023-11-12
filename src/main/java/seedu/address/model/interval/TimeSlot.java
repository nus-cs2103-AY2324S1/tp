package seedu.address.model.interval;

import seedu.address.commons.core.LogsCenter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TimeSlot Class
 */
public class TimeSlot {
    private Date start;
    private Date end;
    private static final Logger logger = LogsCenter.getLogger(TimeSlot.class);

    /**
     * Constructor for TimeSlot
     * @param start a Date object for start
     * @param end a Date object for end
     */
    public TimeSlot(Date start, Date end) {
        requireAllNonNull(start, end);
        this.start = start;
        this.end = end;
    }

    /**
     * @return the duration of the timeslot in minutes
     */
    public long getDurationMinutes() {
        return (end.getTime() - start.getTime()) / (60 * 1000);
    }

    /**
     * Parses a list of String into a list of TimeSlots.
     * @param timeSlots the list of strings to be parsed
     * @return parsed timeslots
     * @throws ParseException
     */
    public static List<TimeSlot> parseIntervals(List<String> timeSlots) throws ParseException {
        List<TimeSlot> timeSlotObjects = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Pattern timePattern = Pattern.compile("\\d{2}:\\d{2}");
        for (String timeSlot : timeSlots) {
            assert timeSlot.charAt(6) == '-';
            String[] times = timeSlot.split(" - ");
            assert timePattern.matcher(times[0]).matches();
            assert timePattern.matcher(times[1]).matches();
            Date start = dateFormat.parse(times[0]);
            Date end = dateFormat.parse(times[1]);
            timeSlotObjects.add(new TimeSlot(start, end));
        }
        return timeSlotObjects;
    }

    /**
     * Finds the available time within the given list of timeslots and interval
     * @param timeslots
     * @param interval
     * @return a list of available timeslots
     * @throws ParseException
     */
    public static List<TimeSlot> findAvailableTime(List<TimeSlot> timeslots, Interval interval) throws ParseException {
        timeslots.sort(Comparator.comparing(timeslot -> timeslot.getStart()));

        List<TimeSlot> availableTime = new ArrayList<>();
        Date lastEnd = interval.getIntervalBegin().getTimes();
        for (TimeSlot timeslot : timeslots) {
            if (timeslot.getStart().after(interval.getIntervalEnd().getTimes())) {
                availableTime.add(new TimeSlot(lastEnd, interval.getIntervalEnd().getTimes()));
            } else if (timeslot.getStart().after(lastEnd)) {
                availableTime.add(new TimeSlot(lastEnd, timeslot.getStart()));
            }
            if (lastEnd.before(timeslot.getEnd())) {
                lastEnd = timeslot.getEnd();
            }
        }
        if (lastEnd.before(interval.getIntervalEnd().getTimes())) {
            availableTime.add(new TimeSlot(lastEnd, interval.getIntervalEnd().getTimes()));
        }
        List<TimeSlot> validTimeSlots = availableTime.stream()
                .filter(timeslot -> timeslot.getDurationMinutes() >= interval.getDuration().toInt())
                .collect(Collectors.toList());

        return validTimeSlots;
    }

    /**
     * Prints out the results
     * @param timeslots
     * @return String of results
     */
    public static String printResults(List<TimeSlot> timeslots) {
        if (timeslots.size() == 0) {
            logger.info("[TimeSlot.printResults]: No timeslots are found");
            return "There are no available timeslots.";
        }

        String result = "";

        for (TimeSlot timeslot : timeslots) {
            logger.info("[TimeSlot.printResults]: Timeslots found");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String startTime = timeFormat.format(timeslot.getStart());
            String endTime = timeFormat.format(timeslot.getEnd());
            result = result + "Free from " + startTime + " - " + endTime + "\n";
        }
        return result;
    }

    /**
     * @return start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @return end
     */
    public Date getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeSlot)) {
            return false;
        }

        TimeSlot otherTimeslot = (TimeSlot) other;
        return start.equals(otherTimeslot.getStart())
                && end.equals(otherTimeslot.getEnd());
    }
}
