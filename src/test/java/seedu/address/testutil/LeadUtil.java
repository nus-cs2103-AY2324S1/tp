package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY_MILESTONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddLeadCommand;
import seedu.address.model.person.Lead;

/**
 * A utility class for Lead.
 */
public class LeadUtil {

    /**
     * Returns an AddClient command string for adding the {@code lead}.
     */
    public static String getAddLeadCommand(Lead lead) {
        return AddLeadCommand.COMMAND_WORD + " " + getLeadDetails(lead);
    }

    /**
     * Returns the part of command string for the given {@code lead}'s details.
     */
    public static String getLeadDetails(Lead lead) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + lead.getName().fullName + " ");
        sb.append(PREFIX_PHONE + lead.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + lead.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + lead.getAddress().value + " ");
        sb.append(PREFIX_KEY_MILESTONE + lead.getKeyMilestone().value + " ");

        lead.getMeetingTime().ifPresent(meetingTime -> sb.append(PREFIX_MEETING_TIME
                + meetingTime.toString() + " "));
        lead.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
