//@@author devanshubisht
package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Student;

/**
 * Finds and lists all students based on specified criteria.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students based on specified criteria.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_PENDING_QUESTION + "PENDING_QUESTION] "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Note: You can only use one prefix at a time.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Alice";

    public static final String MESSAGE_NO_DESCRIPTION = "Description should not be blank."
            + "\nExample: find n/Alice";

    private final Predicate<Student> predicate;

    /**
     * Constructs a FindCommand with the specified predicate for filtering.
     *
     * @param predicate A predicate that filters entities based on the specified criteria.
     */
    public FindCommand(Predicate <Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getSelectedCourse();

        course.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, course.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;

        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
