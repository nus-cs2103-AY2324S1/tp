package seedu.address.testutil;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.model.person.Applicant;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

/**
 * A utility class for Applicant.
 */
public class ApplicantUtil {

    /**
     * Returns an add command string for adding the {@code applicant}.
     */
    public static String getAddApplicantCommand(Applicant applicant) {
        return AddApplicantCommand.COMMAND_WORD + " " + getApplicantDetails(applicant);
    }

    /**
     * Returns the part of command string for the given {@code applicant}'s details.
     */
    public static String getApplicantDetails(Applicant applicant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + applicant.getName().fullName + " ");
        sb.append(PREFIX_PHONE + applicant.getPhone().value + " ");
        return sb.toString();
    }

    // TODO: Implement the following method after EditApplicantCommand is implemented.
    /**
     * Returns the part of command string for the given {@code EditApplicantDescriptor}'s details.
     */
    public static String getEditApplicantDescriptorDetails(EditApplicantCommand.EditApplicantDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(phone.value).append(" "));
        return sb.toString();
    }
}
