package seedu.address.logic.commands.partnercourse;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.seplendidui.UiUtil;

/**
 * Lists all partner courses in the PartnerCourseCatalogue
 */
public class PartnerCourseListCommand extends PartnerCourseCommand {
    public static final String ACTION_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all partner courses";

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateFilteredPartnerCourseList(SeplendidModel.PREDICATE_SHOW_ALL_PARTNER_COURSES);
        return new CommandResult(MESSAGE_SUCCESS, UiUtil.ListViewModel.PARTNER_COURSE_LIST);
    }
}

