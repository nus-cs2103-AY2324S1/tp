package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.FIELD_COMMENT;
import static seedu.address.logic.parser.CliSyntax.FIELD_EMAIL;
import static seedu.address.logic.parser.CliSyntax.FIELD_GPA;
import static seedu.address.logic.parser.CliSyntax.FIELD_INTERVIEW_SCORE;
import static seedu.address.logic.parser.CliSyntax.FIELD_NAME;
import static seedu.address.logic.parser.CliSyntax.FIELD_PHONE;
import static seedu.address.logic.parser.CliSyntax.FIELD_PREVIOUS_GRADE;
import static seedu.address.logic.parser.CliSyntax.FIELD_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.FIELD_TAGS;

import java.util.Comparator;

/**
 * Compares two persons based on the specified field.
 */
public class SortComparator implements Comparator<Person> {
    private final String fieldName;

    public SortComparator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int compare(Person p1, Person p2) {
        switch (fieldName) {
        case FIELD_NAME:
            return p1.getName().fullName.compareTo(p2.getName().fullName);
        case FIELD_STUDENT_NUMBER:
            return p1.getStudentNumber().value.compareTo(p2.getStudentNumber().value);
        case FIELD_GPA:
            return (int) ((p2.getGpa().value - p1.getGpa().value) * 100);
        case FIELD_PREVIOUS_GRADE:
            return p1.getPreviousGrade().grade.compareTo(p2.getPreviousGrade().grade);
        case FIELD_INTERVIEW_SCORE:
            if (p1.getInterviewScore().isPresent() && p2.getInterviewScore().isPresent()) {
                return Double.compare(p2.getInterviewScore().get().value, p1.getInterviewScore().get().value);
            } else if (p1.getInterviewScore().isPresent()) {
                return -1;
            } else if (p2.getInterviewScore().isPresent()) {
                return 1;
            } else {
                return 0;
            }
        case FIELD_COMMENT:
            if (p1.getComment().isPresent() && p2.getComment().isPresent()) {
                return p2.getComment().get().comment.length() - p1.getComment().get().comment.length();
            } else if (p1.getComment().isPresent()) {
                return -1;
            } else if (p2.getComment().isPresent()) {
                return 1;
            } else {
                return 0;
            }
        case FIELD_PHONE:
            return p1.getPhone().value.compareTo(p2.getPhone().value);
        case FIELD_EMAIL:
            return p1.getEmail().value.compareTo(p2.getEmail().value);
        case FIELD_TAGS:
            return p2.getTags().size() - p1.getTags().size();
        default:
            return 0;
        }
    }
}
