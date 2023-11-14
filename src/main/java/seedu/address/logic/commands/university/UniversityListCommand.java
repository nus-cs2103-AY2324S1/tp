package seedu.address.logic.commands.university;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all universities in the UniversitiesCatalogue.
 */
public class UniversityListCommand extends UniversityCommand {
    public static final String ACTION_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all universities";

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateFilteredUniversityList(SeplendidModel.PREDICATE_SHOW_ALL_UNIVERSITIES);
        return new CommandResult(MESSAGE_SUCCESS, UiUtil.ListViewModel.UNIVERSITY_LIST);
    }
}
