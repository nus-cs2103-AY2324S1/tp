package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lessons.Lesson;

/**
 * Edits the details of an existing lesson in the schedule.
 */
public class EditLessonCommand extends AbstractEditCommand<Lesson> {
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
    void init() throws CommandException {
        init(model.getCurrentlyDisplayedLesson(), model.getFilteredScheduleList());
    }

    @Override
    void setNonDefaultFields() throws CommandException {
        setNameRemarkTags();
        edited.setDayIfNotDefault(editDescriptor.getDay());
        edited.setStartIfNotDefault(editDescriptor.getStart());
        edited.setEndIfNotDefault(editDescriptor.getEnd());
        edited.setSubjectIfNotDefault(editDescriptor.getSubject());
    }

    @Override
    void validateEdited() throws CommandException {
        validateEdited(model::hasLessonClashWith);
    }
    @Override
    void writeBack() throws CommandException {
        model.setLesson(original, edited);
    }
}
