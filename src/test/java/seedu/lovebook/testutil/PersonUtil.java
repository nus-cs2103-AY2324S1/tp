package seedu.lovebook.testutil;

import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.lovebook.logic.commands.AddCommand;
import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.model.person.Date;

/**
 * A utility class for Date.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code date}.
     */
    public static String getAddCommand(Date date) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(date);
    }

    /**
     * Returns the part of command string for the given {@code date}'s details.
     */
    public static String getPersonDetails(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + date.getName().fullName + " ");
        sb.append(PREFIX_AGE + date.getAge().value + " ");
        sb.append(PREFIX_GENDER + date.getGender().value + " ");
        sb.append(PREFIX_HEIGHT + date.getHeight().value + " ");
        sb.append(PREFIX_INCOME + date.getIncome().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.value).append(" "));
        descriptor.getIncome().ifPresent(income -> sb.append(PREFIX_INCOME).append(income.value).append(" "));
        return sb.toString();
    }
}
