package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;

/**
 * Edits the details of an existing lesson in the schedule.
 */
public class EditLessonCommand extends AbstractEditCommand<Lesson> {
    public static final String COMMAND_WORD = "editLesson";
    public EditLessonCommand(int index, Lesson editDescriptor) {
        super(index, editDescriptor);
    }

    public EditLessonCommand(Lesson editDescriptor) {
        super(editDescriptor);
    }

    public EditLessonCommand(Lesson editDescriptor, Lesson originalLesson) {
        super(editDescriptor, originalLesson);
    }

    @Override
    protected void initModelMethods() {
        currentShownEntry = model.getCurrentlyDisplayedLesson();
        list = model.getFilteredScheduleList();
        hasClashWith = model::hasLessonClashWith;
        deleteMethod = model::deleteLesson;
        addMethod = model::addLesson;
        getClashingEntry = model::getLessonClashWith;
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
    String editableFieldsInfo() {
        return "name, start, end, subject, day, remark";
    }

    @Override
    String className() {
        return "Lesson";
    }

    @Override
    String getUsageInfo() {
        return "Usage: editLesson [INDEX] -[flagName] [Value]. For example, editLesson 1 -start 14:30"
                + " If you currently displayed list is Lesson, you could use edit inplace of editLesson. "
                + "Editable Fields: " + editableFieldsInfo() + "."
                + "Note that start must be before end.";
    }
}
