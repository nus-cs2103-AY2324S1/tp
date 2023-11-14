package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.time.DayOfWeek;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.course.Course;
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
        sb.append(PREFIX_NAME).append(person.getName().fullName).append(" ");
        sb.append(PREFIX_PHONE).append(person.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL).append(person.getEmail().value).append(" ");
        sb.append(PREFIX_TELEGRAM).append(person.getTelegram().value).append(" ");

        if (!person.getFreeTime().equals(FreeTime.EMPTY_FREE_TIME)) {
            FreeTime freeTime = person.getFreeTime();
            sb.append(PREFIX_FREE_TIME);
            for (int i = 0; i < FreeTime.NUM_DAYS; i++) {
                sb.append(DayOfWeek.of(i) + ":" + freeTime.getDay(i).toString());
            }
        }
        person.getTags().forEach(
                s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        person.getCourses().forEach(
                s -> sb.append(PREFIX_COURSE).append(s.getCourseCode()).append(" ")
        );
        sb.append(PREFIX_HOUR + String.valueOf(person.getHour().value) + " ");
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
        descriptor.getTelegram().ifPresent(address -> sb.append(PREFIX_TELEGRAM).append(address.value).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        }
        if (descriptor.getCourses().isPresent()) {
            Set<Course> mods = descriptor.getCourses().get();
            mods.forEach(s -> sb.append(PREFIX_COURSE).append(s.getCourseCode()).append(" "));
        }
        descriptor.getHour().ifPresent(hour -> sb.append(PREFIX_HOUR).append(hour.value).append(" "));
        return sb.toString();
    }
}
