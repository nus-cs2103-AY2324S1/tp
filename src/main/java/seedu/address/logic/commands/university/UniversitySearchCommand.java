package seedu.address.logic.commands.university;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.university.UniversityNameContainsKeywordsPredicate;
import seedu.address.seplendidui.UiUtil;

/**
 * Finds and lists all universities in SEPlendid whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class UniversitySearchCommand extends UniversityCommand {

    public static final String ACTION_WORD = "search";
    public static final String MESSAGE_SUCCESS = "Universities searched: %1$s";
    public static final String MESSAGE_NONEXISTENT_UNIVERSITIES = "This university does not exist in SEPlendid";
    private final UniversityNameContainsKeywordsPredicate predicate;

    public UniversitySearchCommand(UniversityNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(SeplendidModel model) throws CommandException {
        requireNonNull(model);

        model.getSearchUniversityIfExists(predicate);

        if (model.getFilteredUniversityList().isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_UNIVERSITIES);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(model.getFilteredUniversityList())),
                UiUtil.ListViewModel.UNIVERSITY_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniversitySearchCommand)) {
            return false;
        }

        UniversitySearchCommand otherUniversitySearchCommand = (UniversitySearchCommand) other;
        return predicate.equals(otherUniversitySearchCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
