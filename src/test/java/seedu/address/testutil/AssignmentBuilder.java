package seedu.address.testutil;

import seedu.address.model.person.assignment.Assignment;
import seedu.address.model.person.assignment.AssignmentName;
import seedu.address.model.person.assignment.Grade;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_NAME = "Finding ELDRIC";
    public static final String DEFAULT_MAX_GRADE = "1200";
    public static final String DEFAULT_ACTUAL_GRADE = "UNGRADED";

    private AssignmentName name;
    private Grade grade;

    /**
     * Creates an {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = new AssignmentName(DEFAULT_NAME);
        grade = new Grade(DEFAULT_ACTUAL_GRADE, DEFAULT_MAX_GRADE);
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignment}.
     */
    public AssignmentBuilder(Assignment assignment) {
        name = assignment.getName();
        grade = assignment.getGrade();
    }

    /**
     * Sets the {@code AssignmentName} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = new AssignmentName(name);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withGrade(String actualGrade, String maxGrade) {
        this.grade = new Grade(actualGrade, maxGrade);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Assignment} that we are building with one String input.
     */
    public AssignmentBuilder withGrade(String grade) {
        String[] gradeArray = grade.split("/");
        this.grade = new Grade(gradeArray[0], gradeArray[1]);
        return this;
    }

    public Assignment build() {
        return new Assignment(name, grade);
    }
}
