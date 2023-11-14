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
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;
import seedu.address.seplendidui.UiUtil;

/**
 * Updates partner course.
 */
public class PartnerCourseUpdateCommand extends PartnerCourseCommand {
    public static final String ACTION_WORD = "update";
    public static final String MESSAGE_SUCCESS = "Updated this partner course: %1$s to this: %2$s.";
    public static final String MESSAGE_NONEXISTENT_PARTNER_COURSE = "This partner course does not exist in SEPlendid.";
    public static final String MESSAGE_DUPLICATE_PARTNER_COURSE =
            "This updated partner course already exists in SEPlendid.";
    public static final String MESSAGE_MAPPING_DEPENDENT_ON_PARTNER_COURSE = "This partner course is mapped to a "
            + "local course. Please delete the mapping first.";
    private PartnerCourse partnerCourseToUpdate;
    private PartnerCourse updatedPartnerCourse;

    private UniversityName universityName;
    private PartnerCode partnerCode;
    private PartnerName partnerName;

    private PartnerUnit partnerUnit;
    private PartnerDescription partnerDescription;

    private PartnerCourseAttribute partnerCourseAttribute;
    private String updatedValue;

    /**
     * Creates a PartnerCourseEditCommand to edit the given partner course.
     *
     * @param universityName         University name of the course to be edited.
     * @param partnerCode            Partner code of the course to be edited.
     * @param partnerCourseAttribute Attribute of the course to be edited.
     * @param updatedValue           Edited value of the specified attribute.
     */
    public PartnerCourseUpdateCommand(UniversityName universityName, PartnerCode partnerCode,
                                      PartnerCourseAttribute partnerCourseAttribute, String updatedValue) {
        super();
        requireAllNonNull(universityName, partnerCode, partnerCourseAttribute, updatedValue);
        this.universityName = universityName;
        this.partnerCode = partnerCode;
        this.partnerCourseAttribute = partnerCourseAttribute;
        this.updatedValue = updatedValue;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        Optional<PartnerCourse> partnerCourseToUpdateOptional =
                seplendidModel.getPartnerCourseIfExists(partnerCode, universityName);
        if (partnerCourseToUpdateOptional.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_PARTNER_COURSE);
        }

        partnerCourseToUpdate = partnerCourseToUpdateOptional.get();

        universityName = partnerCourseToUpdate.getPartnerUniversity().getUniversityName();
        partnerCode = partnerCourseToUpdate.getPartnerCode();
        partnerName = partnerCourseToUpdate.getPartnerName();
        partnerUnit = partnerCourseToUpdate.getPartnerUnit();
        partnerDescription = partnerCourseToUpdate.getPartnerDescription();

        switch (partnerCourseAttribute) {
        case PARTNERCODE:
            partnerCode = new PartnerCode(updatedValue);
            break;
        case PARTNERNAME:
            partnerName = new PartnerName(updatedValue);
            break;
        case PARTNERUNIT:
            partnerUnit = new PartnerUnit(updatedValue);
            break;
        case PARTNERDESCRIPTION:
            partnerDescription = new PartnerDescription(updatedValue);
            break;
        default:
            //do nothing
        }

        updatedPartnerCourse = new PartnerCourse(
                new University(universityName), partnerCode, partnerName, partnerUnit, partnerDescription);

        if (!seplendidModel.hasPartnerCourse(partnerCourseToUpdate)) {
            throw new CommandException(MESSAGE_NONEXISTENT_PARTNER_COURSE);
        }

        if (seplendidModel.hasPartnerCourse(updatedPartnerCourse)
                && !partnerCourseToUpdate.isSamePartnerCourse(updatedPartnerCourse)) {
            throw new CommandException(MESSAGE_DUPLICATE_PARTNER_COURSE);
        }

        seplendidModel.setPartnerCourse(partnerCourseToUpdate, updatedPartnerCourse);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS,
                        Messages.format(partnerCourseToUpdate), Messages.format(updatedPartnerCourse)),
                UiUtil.ListViewModel.PARTNER_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseUpdateCommand)) {
            return false;
        }

        PartnerCourseUpdateCommand otherPartnerCourseUpdateCommand = (PartnerCourseUpdateCommand) other;
        return universityName.equals(otherPartnerCourseUpdateCommand.universityName)
                && partnerCode.equals(otherPartnerCourseUpdateCommand.partnerCode)
                && partnerCourseAttribute.equals(otherPartnerCourseUpdateCommand.partnerCourseAttribute)
                && updatedValue.equals(otherPartnerCourseUpdateCommand.updatedValue);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("partnerCourseToEdit", partnerCourseToUpdate)
                .add("editedPartnerCourse", updatedPartnerCourse).toString();
    }
}
