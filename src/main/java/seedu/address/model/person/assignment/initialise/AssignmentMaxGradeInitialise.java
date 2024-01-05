package seedu.address.model.person.assignment.initialise;

import java.util.ArrayList;

import seedu.address.model.person.assignment.Grade;

/**
 * Represents an initializer to create grades for each assignment.
 */
public class AssignmentMaxGradeInitialise {
    private static ArrayList<Grade> maxGrades = new ArrayList<>();

    /**
     * Initialises mission grades.
     */
    public void missionGrades() {
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("600"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("800"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("700"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("600"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("800"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("950"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1200"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1300"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("720"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("950"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("800"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1200"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1200"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1500"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1500"));
    }

    /**
     * Initialises quest grades.
     */
    public void questGrades() {
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("400"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("600"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("500"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("500"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("600"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("800"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("670"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("750"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("600"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("1000"));
        AssignmentMaxGradeInitialise.maxGrades.add(new Grade("800"));
    }

    public Grade getGrade(int index) {
        return this.maxGrades.get(index);
    }
}
