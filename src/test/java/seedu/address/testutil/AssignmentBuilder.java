package seedu.address.testutil;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Title;

/**
* Creates a {@code AssignmentBuilder} with the default details.
*/
public class AssignmentBuilder {
    private static Title title;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        title = new Title("Assignment 1");
    }

    public AssignmentBuilder(Assignment assignment) {
        this.title = assignment.getTitle();
    }

    public Assignment build() {
        return new Assignment(title);
    }

    public Assignment build(String title) {
        return new Assignment(new Title(title));
    }
}
