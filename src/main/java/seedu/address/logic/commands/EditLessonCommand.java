package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditLessonCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;

/**
 * Edits the details of an existing lesson in the schedule.
 */
public class EditLessonCommand extends AbstractEditCommand<Lesson> {
    public static final String COMMAND_WORD = "editlesson";
    public EditLessonCommand(Integer index, Lesson editDescriptor) {
        super(index, editDescriptor);
    }

    @Override
    protected void initModelMethods() {
        currentShownEntry = model.getCurrentlyDisplayedLesson();
        list = model.getFilteredScheduleList();
        hasClashWith = model::hasLessonClashWith;
        deleteMethod = model::deleteLessonForEdit;
        addMethod = model::addLesson;
        getClashingEntry = model::getLessonClashWith;
        showMethod = model::showLesson;
    }

    @Override
    protected void setNonDefaultFields() throws CommandException {
        edited.setDayIfNotDefault(editDescriptor.getDay());
        try {
            edited.updateStartAndEnd(editDescriptor.getStart(), editDescriptor.getEnd());
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
        edited.setSubjectIfNotDefault(editDescriptor.getSubject());
    }

    @Override
    protected void updatePersonLessonMap() {
        model.getPersonLessonMap().updateReverse(original, edited);
    }

    @Override
    String editableFieldsInfo() {
        return "name, start, end, subject, day";
    }

    @Override
    String listName() {
        return "schedule";
    }

    @Override
    public String getUsageInfo() {
        return EditLessonCommandParser.getUsageInfo();
    }
    public Lesson getEditDescriptor() {
        return editDescriptor;
    }
    public Integer getIndex() {
        return index;
    }
}
