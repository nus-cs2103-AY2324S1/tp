package seedu.address.logic.commands.university;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.model.university.University;
import seedu.address.seplendidui.UiUtil;

/**
 * Sorts university list.
 */
public class UniversitySortCommand extends UniversityCommand {
    public static final String ACTION_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all universities";

    private final Comparator<University> comparator;

    public UniversitySortCommand(Comparator<University> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateSortedUniversityList(comparator);
        return new CommandResult(MESSAGE_SUCCESS,
                UiUtil.ListViewModel.UNIVERSITY_SORT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniversitySortCommand)) {
            return false;
        }

        UniversitySortCommand otherUniversitySortCommand = (UniversitySortCommand) other;
        return comparator.equals(otherUniversitySortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("UniversityAttributeToSort", comparator)
                .toString();
    }
}
