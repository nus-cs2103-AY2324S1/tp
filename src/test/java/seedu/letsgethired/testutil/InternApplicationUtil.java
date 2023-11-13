package seedu.letsgethired.testutil;

import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A utility class for Intern Application.
 */
public class InternApplicationUtil {

    /**
     * Returns an add command string for adding the {@code internApplication}.
     */
    public static String getAddCommand(InternApplication internApplication) {
        return AddCommand.COMMAND_WORD + " " + getInternApplicationDetails(internApplication);
    }

    /**
     * Returns the part of command string for the given {@code internApplication}'s details.
     */
    public static String getInternApplicationDetails(InternApplication internApplication) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + internApplication.getCompany().value + " ");
        sb.append(PREFIX_ROLE + internApplication.getRole().value + " ");
        sb.append(PREFIX_CYCLE + internApplication.getCycle().value + " ");
        sb.append(PREFIX_STATUS + internApplication.getStatus().value + " ");
        sb.append(PREFIX_DEADLINE + internApplication.getDeadline().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditInternApplicationDescriptor}'s details.
     */
    public static String getEditInternApplicationDescriptorDetails(EditInternApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(name -> sb.append(PREFIX_COMPANY).append(name.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.value).append(" "));
        descriptor.getCycle().ifPresent(cycle -> sb.append(PREFIX_CYCLE).append(cycle.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.value).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.value).append(" "));
        return sb.toString();
    }
}
