package networkbook.logic.commands.edit;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;

/**
 * Represents an action to edit a course of a person.
 */
public class EditCourseAction implements EditAction {
    private final Course course;
    private final Index index;

    /**
     * Constructs a new action to edit course.
     * @param index The index of the course in the course list of the person.
     * @param course The new value of the course.
     */
    public EditCourseAction(Index index, Course course) {
        this.index = index;
        this.course = course;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor, Index indexOfPerson) throws CommandException {
        assert editPersonDescriptor != null : "editPersonDescriptor should not be null";
        editPersonDescriptor.setCourse(index, course, indexOfPerson);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditCourseAction)) {
            return false;
        }

        EditCourseAction otherAction = (EditCourseAction) object;
        return this.course.equals(otherAction.course)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("course", this.course);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
