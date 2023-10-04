package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditJobDescriptor;
import seedu.address.model.job.Job;

/**
 * A utility class for Job.
 */
public class JobUtil {

    /**
     * Returns an add command string for adding the {@code job}.
     */
    public static String getAddCommand(Job job) {
        return AddCommand.COMMAND_WORD + " " + getJobDetails(job);
    }

    /**
     * Returns the part of command string for the given {@code job}'s details.
     */
    public static String getJobDetails(Job job) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROLE + job.getRole().description + " ");
        sb.append(PREFIX_COMPANY + job.getCompany().name + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditJobDescriptor}'s details.
     */
    public static String getEditJobDescriptorDetails(EditJobDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.description).append(" "));
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.name).append(" "));
        return sb.toString();
    }
}
