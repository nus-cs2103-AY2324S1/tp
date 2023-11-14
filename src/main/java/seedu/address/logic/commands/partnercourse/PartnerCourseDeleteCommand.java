package seedu.address.logic.commands.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.UniversityName;
import seedu.address.seplendidui.UiUtil;

/**
 * Deletes a partner course in PartnerCourseCatalogue.
 */
public class PartnerCourseDeleteCommand extends PartnerCourseCommand {
    public static final String ACTION_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Partner course deleted: %1$s";
    public static final String MESSAGE_NONEXISTENT_PARTNER_COURSE = "This partner course does not exist in SEPlendid.";
    public static final String MESSAGE_MAPPING_DEPENDENT_ON_PARTNER_COURSE = "This partner course is mapped to a local "
            + "course. Please delete the mapping first.";
    private final PartnerCode partnerCodeToDelete;
    private final UniversityName universityNameToDelete;

    /**
     * Creates a PartnerCourseDeleteCommand to delete the specified {@code partnerCourse}
     *
     * @param partnerCode The partnerCourse to be deleted in Storage.
     */
    public PartnerCourseDeleteCommand(UniversityName universityName, PartnerCode partnerCode) {
        super();
        requireAllNonNull(universityName, partnerCode);
        universityNameToDelete = universityName;
        partnerCodeToDelete = partnerCode;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        Optional<PartnerCourse> partnerCourseToDelete =
                seplendidModel.getPartnerCourseIfExists(partnerCodeToDelete, universityNameToDelete);
        if (partnerCourseToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_PARTNER_COURSE);
        }
        seplendidModel.deletePartnerCourse(partnerCourseToDelete.get());

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(partnerCourseToDelete.get())),
                UiUtil.ListViewModel.PARTNER_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseDeleteCommand)) {
            return false;
        }

        PartnerCourseDeleteCommand otherPartnerCourseDeleteCommand = (PartnerCourseDeleteCommand) other;
        return partnerCodeToDelete.equals(otherPartnerCourseDeleteCommand.partnerCodeToDelete)
                && universityNameToDelete.equals(otherPartnerCourseDeleteCommand.universityNameToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("partnerCourseCodeToDelete", partnerCodeToDelete)
                .add("partnerCourseUniversityNameToDelete", universityNameToDelete)
                .toString();
    }
}
