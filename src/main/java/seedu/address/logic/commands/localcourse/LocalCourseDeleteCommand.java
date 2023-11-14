package seedu.address.logic.commands.localcourse;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.seplendidui.UiUtil;

/**
 * Deletes a local course identified using its local code from the address book.
 */
public class LocalCourseDeleteCommand extends LocalCourseCommand {
    public static final String ACTION_WORD = "delete";

    public static final String MESSAGE_SUCCESS = "Deleted Local Course: %1$s";
    public static final String MESSAGE_NONEXISTENT_LOCAL_COURSE = "This local course does not exist in SEPlendid.";
    public static final String MESSAGE_MAPPING_DEPENDENT_ON_LOCAL_COURSE = "This local course is mapped to a partner "
            + "course. Please delete the mapping first.";

    private final LocalCode localCodeToDelete;

    /**
     * Creates a LocalCourseDeleteCommand to delete based on the specified {@code LocalCode}
     *
     * @param localCode The localCode that identifies the localCourse to be deleted.
     */
    public LocalCourseDeleteCommand(LocalCode localCode) {
        requireNonNull(localCode);
        this.localCodeToDelete = localCode;
    }

    /**
     * Perfoms the deletion of the local course.
     *
     * @param seplendidModel {@code SeplendidModel} which the command should operate on.
     * @return {@code CommandResult} that describes the result of the deletion.
     * @throws CommandException If an error occurs during the deletion.
     */
    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        // There should be no duplicates
        Optional<LocalCourse> localCourseToDelete = seplendidModel.getLocalCourseIfExists(localCodeToDelete);
        if (localCourseToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_LOCAL_COURSE);
        }
        seplendidModel.deleteLocalCourse(localCourseToDelete.get());

        // At this point we can already assume localCourseToDelete is not empty.
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(localCourseToDelete.get())),
                UiUtil.ListViewModel.LOCAL_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocalCourseDeleteCommand)) {
            return false;
        }

        LocalCourseDeleteCommand otherLocalCourseDeleteCommand = (LocalCourseDeleteCommand) other;
        return localCodeToDelete.equals(otherLocalCourseDeleteCommand.localCodeToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("localCourseToDelete", localCodeToDelete)
                .toString();
    }
}
