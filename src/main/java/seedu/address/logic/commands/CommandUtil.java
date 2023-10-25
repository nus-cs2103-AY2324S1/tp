package seedu.address.logic.commands;

import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

/**
 * Represents utility tools used during command execution.
 */
public class CommandUtil {

    /**
     * Verifies that 1) Start time is not earlier than current time,
     * 2) End Time is not earlier start time.
     * @param event Event to be verified.
     *
     * @throws ParseException if the given {@code time} does not meet any of the above 2 criteria.
     */
    public static void verifyEventTimes(Event event) throws CommandException {

        if (!event.hasStartTime() && !event.hasEndTime()) {
            verifyTimeIsAfterCurrentTime(event.getStartDate().getDate().atTime(LocalTime.MAX));
        } else if (!event.hasStartTime()) {
            verifyTimeIsAfterCurrentTime(event.getEndDate().getDate()
                    .atTime(event.getEndTime().getEventTime()));
        } else if (!event.hasEndTime()) {
            verifyTimeIsAfterCurrentTime(event.getStartDate().getDate()
                    .atTime(event.getStartTime().getEventTime()));
        } else {
            verifyTimeIsAfterCurrentTime(event.getStartDate().getDate()
                    .atTime(event.getStartTime().getEventTime()));

            verifyEndTimeIsAfterStartTime(event);
        }

    }

    private static void verifyEndTimeIsAfterStartTime(Event event) throws CommandException {
        LocalDateTime combinedStartTime = event.getStartDate().getDate()
                .atTime(event.getStartTime().getEventTime());

        LocalDateTime combinedEndTime = event.getEndDate().getDate()
                .atTime(event.getEndTime().getEventTime());

        if (combinedEndTime.isBefore(combinedStartTime)) {
            throw new CommandException(Event.END_TIME_CONSTRAINTS);
        }
    }

    private static void verifyTimeIsAfterCurrentTime(LocalDateTime time) throws CommandException {
        if (time.isBefore(LocalDateTime.now())) {
            throw new CommandException(Event.START_TIME_CONSTRAINTS);
        }
    }
}
