package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.model.company.Company;

/**
 * A utility class for Company.
 */
public class CompanyUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getAddCommand(Company company) {
        return AddCommand.COMMAND_WORD + " " + getCompanyDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code company}'s details.
     */
    public static String getCompanyDetails(Company company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + company.getName().fullName + " ");
        sb.append(PREFIX_PHONE + company.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + company.getEmail().value + " ");
        sb.append(PREFIX_ROLE + company.getRole().jobRole + " ");
        sb.append(PREFIX_DEADLINE + company.getDeadline().toString() + " ");
        sb.append(PREFIX_STATUS + company.getStatus().toString() + " ");
        sb.append(PREFIX_RECRUITER_NAME + company.getRecruiterName().fullName + " ");
        sb.append(PREFIX_PRIORITY + company.getPriority().priority + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCompanyDescriptor}'s details.
     */
    public static String getEditCompanyDescriptorDetails(EditCompanyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_COMPANY_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.jobRole).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.toString())
                .append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.toString()).append(" "));
        descriptor.getRecruiterName().ifPresent(recruiterName -> sb.append(PREFIX_RECRUITER_NAME)
                .append(recruiterName.fullName).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY).append(priority.priority)
                .append(" "));

        return sb.toString();
    }
}
