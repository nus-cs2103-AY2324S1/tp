package seedu.address.logic;

import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;

//todo improve code coverage and delete dead code
/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lessons listed!";

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Subjects: ");
        person.getSubjectsSet().forEach(builder::append);
        builder.append("; Tags: ");
        person.getTagsSet().forEach(builder::append);
        builder.append("; Remark: ")
                .append(person.getRemark());
        return builder.toString();
    }

    /**
     * Formats the {@code lesson} for display to the user.
     */
    public static String formatLesson(Lesson lesson) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Start: ")
                .append(lesson.getStart())
                .append("; End: ")
                .append(lesson.getEnd());
        return builder.toString();
    }

    /**
     * Formats the {@code task} for display to the user.
     */
    public static String formatTask(Task task) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Description: ")
                .append(task.getDescription());
        return builder.toString();
    }


}
