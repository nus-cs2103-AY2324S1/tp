package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.ParserUtil.FORMAT;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class for Meeting.
 */
public class MeetingUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddMeetingCommand(Meeting meeting) {
        return AddMeetingCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + meeting.getTitle().meetingTitle + " ");
        sb.append(PREFIX_LOCATION + meeting.getLocation().location + " ");
        sb.append(PREFIX_START + meeting.getStart().format(FORMAT) + " ");
        sb.append(PREFIX_END + meeting.getEnd().format(FORMAT) + " ");
        meeting.getAttendees().stream().forEach(
                s -> sb.append(PREFIX_NAME + s.getAttendeeName() + " ")
        );
        meeting.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
