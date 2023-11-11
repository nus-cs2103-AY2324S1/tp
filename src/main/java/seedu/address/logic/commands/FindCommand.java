package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.LessonContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons or lessons in app whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons or lesson whose names contains "
            + "the specified search string (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameter: SEARCH_STRING\n"
            + "Example: " + COMMAND_WORD + " alex yeoh";

    private Predicate predicate;
    private final String trimmedArgs;

    public FindCommand(String trimmedArgs) {
        this.trimmedArgs = trimmedArgs;
    }

    @Override @SuppressWarnings("unchecked")
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (model.getState()) {
        case STUDENT:
            predicate = new NameContainsKeywordsPredicate(trimmedArgs);
            model.updateFilteredPersonList(predicate);
            model.showPerson(null);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        case SCHEDULE:
            predicate = new LessonContainsKeywordsPredicate(trimmedArgs);
            model.updateFilteredScheduleList(predicate);
            model.showLesson(null);
            return new CommandResult(
                    String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredScheduleList().size()));
        default:
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }


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
        return trimmedArgs.equals(otherFindCommand.trimmedArgs);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("trimmed args", trimmedArgs)
                .toString();
    }
}
