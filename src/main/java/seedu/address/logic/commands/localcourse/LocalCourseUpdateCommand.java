package seedu.address.logic.commands.localcourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;
import seedu.address.seplendidui.UiUtil;

/**
 * Parses input arguments and create a new LocalCourseUpdateCommand object.
 */
public class LocalCourseUpdateCommand extends LocalCourseCommand {
    public static final String ACTION_WORD = "update";
    public static final String MESSAGE_SUCCESS = "Updated this local course: %1$s to this: %2$s.";
    public static final String MESSAGE_NONEXISTENT_LOCAL_COURSE = "This local course does not exist in SEPlendid.";
    public static final String MESSAGE_DUPLICATE_LOCAL_COURSE =
            "This updated partner course already exists in SEPlendid.";
    public static final String MESSAGE_MAPPING_DEPENDENT_ON_LOCAL_COURSE = "This local course is mapped to a partner "
            + "course. Please delete the mapping first.";
    private LocalCourse localCourseToUpdate;
    private LocalCourse updatedLocalCourse;
    private LocalCode localCode;
    private LocalName localName;
    private LocalUnit localUnit;
    private LocalDescription localDescription;
    private LocalCourseAttribute localCourseAttribute;
    private String updatedValue;

    /**
     * Creates a LocalCourseUpdateCommand to edit the given local course.
     *
     * @param localCode            Local code of the course to be updated.
     * @param localCourseAttribute Attribute of the course to be updated.
     * @param updatedValue         Edited value of the specified attribute.
     */
    public LocalCourseUpdateCommand(LocalCode localCode,
                                    LocalCourseAttribute localCourseAttribute, String updatedValue) {
        super();
        requireAllNonNull(localCode, localCourseAttribute, updatedValue);
        this.localCode = localCode;
        this.localCourseAttribute = localCourseAttribute;
        this.updatedValue = updatedValue;
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);

        Optional<LocalCourse> localCourseToUpdateOptional =
                seplendidModel.getLocalCourseIfExists(localCode);
        if (localCourseToUpdateOptional.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_LOCAL_COURSE);
        }

        localCourseToUpdate = localCourseToUpdateOptional.get();

        localCode = localCourseToUpdate.getLocalCode();
        localName = localCourseToUpdate.getLocalName();
        localUnit = localCourseToUpdate.getLocalUnit();
        localDescription = localCourseToUpdate.getLocalDescription();

        switch (localCourseAttribute) {
        case LOCALCODE:
            localCode = new LocalCode(updatedValue);
            break;
        case LOCALNAME:
            localName = new LocalName(updatedValue);
            break;
        case LOCALUNIT:
            localUnit = new LocalUnit(updatedValue);
            break;
        case LOCALDESCRIPTION:
            localDescription = new LocalDescription(updatedValue);
            break;
        default:
            //do nothing
        }

        updatedLocalCourse = new LocalCourse(localCode, localName, localUnit, localDescription);
        if (!seplendidModel.hasLocalCourse(localCourseToUpdate)) {
            throw new CommandException(MESSAGE_NONEXISTENT_LOCAL_COURSE);
        }

        if (seplendidModel.hasLocalCourse(updatedLocalCourse)
                && !localCourseToUpdate.isSameLocalCourse(updatedLocalCourse)) {
            throw new CommandException(MESSAGE_DUPLICATE_LOCAL_COURSE);
        }

        seplendidModel.setLocalCourse(localCourseToUpdate, updatedLocalCourse);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS,
                        Messages.format(localCourseToUpdate), Messages.format(updatedLocalCourse)),
                UiUtil.ListViewModel.LOCAL_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LocalCourseUpdateCommand)) {
            return false;
        }

        LocalCourseUpdateCommand otherLocalCourseUpdateCommand = (LocalCourseUpdateCommand) other;
        return localCode.equals(otherLocalCourseUpdateCommand.localCode)
                && localCourseAttribute.equals(otherLocalCourseUpdateCommand.localCourseAttribute)
                && updatedValue.equals(otherLocalCourseUpdateCommand.updatedValue);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("localCourseToUpdate", localCourseToUpdate)
                .add("updatedLocalCourse", updatedLocalCourse).toString();
    }
}

