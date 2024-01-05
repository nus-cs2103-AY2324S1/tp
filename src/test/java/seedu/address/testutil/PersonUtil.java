package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM_HANDLE + person.getTelegramHandle().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getGradedTest().stream().forEach(
                s -> sb.append(PREFIX_GRADED_TEST + s.gradedTestsIndv + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(
                telegramHandle -> sb.append(PREFIX_TELEGRAM_HANDLE).append(telegramHandle.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            sb.append(parseTagsToEdit(descriptor)).append(" ");
        }
        if (descriptor.getGradedTests().isPresent()) {
            sb.append(parseGradedTestToEdit(descriptor)).append(" ");
        }
        return sb.toString();
    }

    private static String parseTagsToEdit(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        Set<Tag> tags = descriptor.getTags().get();
        if (tags.isEmpty()) {
            sb.append(PREFIX_TAG);
            return sb.toString();
        } else {
            tags.forEach(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
            return sb.toString();
        }
    }

    private static String parseGradedTestToEdit(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        Set<GradedTest> gradedTests = descriptor.getGradedTests().get();
        if (gradedTests.isEmpty()) {
            sb.append(PREFIX_GRADED_TEST);
            return sb.toString();
        } else {
            gradedTests.forEach(gt -> sb.append(PREFIX_GRADED_TEST).append(gt.gradedTestsIndv).append(" "));
            return sb.toString();
        }
    }
}
