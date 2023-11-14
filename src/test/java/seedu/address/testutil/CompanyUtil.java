package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.model.company.Company;

/**
 * A utility class for Company.
 */
public class CompanyUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getAddCommand(Company company) {
        return AddCompanyCommand.COMMAND_WORD + " c " + getCompanyDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code Company}'s details.
     */
    public static String getCompanyDetails(Company company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + company.getCompanyName().fullName + " ");
        sb.append(PREFIX_PHONE + company.getCompanyPhone().value + " ");
        sb.append(PREFIX_EMAIL + company.getCompanyEmail().value + " ");
        sb.append(PREFIX_DESCRIPTION + company.getCompanyDescription().value + " ");
        company.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    //    /**
    //     * Returns the part of command string for the given {@code EditcompanyDescriptor}'s details.
    //     */
    // public static String getEditcompanyDescriptorDetails(EditCompanyDescriptor descriptor) {
    //     StringBuilder sb = new StringBuilder();
    //     descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
    //     descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
    //     descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
    //     descriptor.getCompanyDescription().ifPresent(
    //          description -> sb.append(PREFIX_DESCRIPTION).append(description.value).append(" "));
    //     if (descriptor.getTags().isPresent()) {
    //         Set<Tag> tags = descriptor.getTags().get();
    //         if (tags.isEmpty()) {
    //             sb.append(PREFIX_TAG);
    //         } else {
    //             tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
    //         }
    //     }
    //     return sb.toString();
    // }
}
