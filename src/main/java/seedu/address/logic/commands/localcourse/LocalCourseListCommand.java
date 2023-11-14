package seedu.address.logic.commands.localcourse;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all local courses in the LocalCourseCatalogue.
 */
public class LocalCourseListCommand extends LocalCourseCommand {
    public static final String ACTION_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all local courses";

    /**
     * Lists all local courses in the LocalCourseCatalogue, by applying a show-all predicate.
     *
     * @param seplendidModel {@code SeplendidModel} which the command should operate on.
     * @return CommandResult indicating success of execution.
     * @throws CommandException On error.
     */
    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateFilteredLocalCourseList(SeplendidModel.PREDICATE_SHOW_ALL_LOCAL_COURSES);
        return new CommandResult(MESSAGE_SUCCESS,
                UiUtil.ListViewModel.LOCAL_COURSE_LIST);
    }

}
