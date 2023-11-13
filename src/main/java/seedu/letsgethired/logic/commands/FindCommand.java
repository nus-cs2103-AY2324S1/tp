package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.function.Predicate;

import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Finds and lists all applications in intern tracker whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all intern applications for which the specified field contains"
            + " the specified search string (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY + "COMPANY_NAME] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_CYCLE + "CYCLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_NOTE_INSERT + "NOTE] "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_ROLE + "Software Engineering "
            + PREFIX_CYCLE + "Summer "
            + PREFIX_STATUS + "Pending ";;

    public static final String NO_FIND_SPECIFIED = "At least one keyword to find and field to be searched "
            + "must be provided";

    private final Predicate<InternApplication> predicate;

    public FindCommand(Predicate<InternApplication> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternApplicationList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERN_APPLICATIONS_LISTED_OVERVIEW,
                        model.getFilteredInternApplicationList().size()));
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
