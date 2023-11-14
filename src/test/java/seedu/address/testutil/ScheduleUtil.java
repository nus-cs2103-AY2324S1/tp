package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * A utility class for Schedule.
 */
public class ScheduleUtil {
    private static final DateTimeFormatter startTimeFormatter =
            DateTimeFormatter.ofPattern(StartTime.DATETIME_INPUT_FORMAT);
    private static final DateTimeFormatter endTimeFormatter =
            DateTimeFormatter.ofPattern(EndTime.DATETIME_INPUT_FORMAT);

    /**
     * Returns an add schedule command string for adding the {@code schedule}.
     */
    public static String getAddScheduleCommand(Schedule schedule) {
        return AddScheduleCommand.COMMAND_WORD + " " + getScheduleDetails(schedule);
    }

    /**
     * Returns the part of command string for the given {@code schedule}'s details.
     */
    public static String getScheduleDetails(Schedule schedule) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDEX_FIRST_PERSON.getOneBased() + " ");
        sb.append(PREFIX_START_TIME + schedule.getStartTime().value.format(startTimeFormatter) + " ");
        sb.append(PREFIX_END_TIME + schedule.getEndTime().value.format(endTimeFormatter) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditScheduleDescriptor}'s details.
     */
    public static String getEditScheduleDescriptorDetails(EditScheduleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getStartTime().ifPresent(startTime -> sb.append(PREFIX_START_TIME + startTime.value
            .format(startTimeFormatter)).append(" "));
        descriptor.getEndTime().ifPresent(endTime -> sb.append(PREFIX_END_TIME + endTime.value
            .format(endTimeFormatter)).append(" "));
        return sb.toString();
    }
}
