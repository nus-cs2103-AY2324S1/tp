package seedu.address.model.interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class TimeSlot {
    public Date start;
    public Date end;

    public TimeSlot(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public long getDurationMinutes() {
        return (end.getTime() - start.getTime()) / (60 * 1000);
    }

    public static List<TimeSlot> parseIntervals(List<String> timeSlots) throws ParseException {
        List<TimeSlot> timeSlotObjects = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        for (String timeSlot : timeSlots) {
            String[] times = timeSlot.split(" - ");
            Date start = dateFormat.parse(times[0]);
            Date end = dateFormat.parse(times[1]);
            timeSlotObjects.add(new TimeSlot(start, end));
        }
        return timeSlotObjects;
    }

    public static List<TimeSlot> findAvailableTime(List<TimeSlot> timeslots, Interval interval) throws ParseException {
        timeslots.sort(Comparator.comparing(timeslot -> timeslot.start));

        List<TimeSlot> availableTime = new ArrayList<>();
        Date lastEnd = interval.getIntervalBegin().getTime();
        for (TimeSlot timeslot : timeslots) {
            if (timeslot.start.after(lastEnd)) {
                availableTime.add(new TimeSlot(lastEnd, timeslot.start));
            }
            if (lastEnd.before(timeslot.end)) {
                lastEnd = timeslot.end;
            }
        }
        if (lastEnd.before(interval.getIntervalEnd().getTime())) {
            availableTime.add(new TimeSlot(lastEnd, interval.getIntervalEnd().getTime()));
        }
        List<TimeSlot> validTimeSlots = availableTime.stream()
                .filter(timeslot -> timeslot.getDurationMinutes() >= interval.getDuration().toInt())
                .collect(Collectors.toList());

        return validTimeSlots;
    }

    public static String printResults(List<TimeSlot> timeslots) {
        String result = "";

        for (TimeSlot timeslot : timeslots) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String startTime = timeFormat.format(timeslot.start);
            String endTime = timeFormat.format(timeslot.end);
            result = result + "Free from " + startTime + " - " + endTime + "\n";
        }
        return result;
    }
}
