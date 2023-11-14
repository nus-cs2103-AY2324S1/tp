package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

import seedu.address.model.ListEntryField;


/**
 * Represents a student's subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject extends ListEntryField {
    public static final Subject DEFAULT_SUBJECT = new Subject();
    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only be Mathematics, Physics, Biology, Chemistry or English";

    public final Subjects subjectName;

    private String colour;
    // useful resource: https://www.w3schools.com/tags/ref_colornames.asp for colours
    private HashMap<String, String> subjectToColourMap = new HashMap<String, String>() {{
            put("MATHEMATICS", "FireBrick");
            put("PHYSICS", "Chocolate");
            put("BIOLOGY", "ForestGreen");
            put("CHEMISTRY", "DarkCyan");
            put("ENGLISH", "SaddleBrown");
            put("NONE", "invalid");
        }};


    /**
     * Subjects that tutor teaches.
     */
    public enum Subjects {
        MATHEMATICS,
        PHYSICS,
        BIOLOGY,
        CHEMISTRY,
        ENGLISH,
        NONE
    }
    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject.toUpperCase().trim()), MESSAGE_CONSTRAINTS);
        if (isValidSubject(subject.toUpperCase().trim())) {
            subjectName = Subjects.valueOf(subject.toUpperCase().trim());
            colour = subjectToColourMap.get(subject.toUpperCase().trim());
        } else {
            subjectName = Subjects.NONE;
        }
    }

    private Subject() {
        subjectName = Subjects.NONE;
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        try {
            Subjects subject = Subjects.valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    /**
     * Returns the subject parsed from a string.
     */
    public static Subject of(String test) throws IllegalArgumentException {
        return new Subject(test);
    }

    /**
     * Returns an empty subject.
     */
    public static Subject getNone() {
        return new Subject("NONE");
    }

    /**
     * Format subject as text for viewing.
     */
    public String toString() {
        if (subjectName == Subjects.NONE) {
            return "To be added";
        }
        return subjectName.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return subjectName.equals(otherSubject.subjectName);
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    public String getColour() {
        return colour;
    }

    public Subject clone() {
        return new Subject(subjectName.toString());
    }
}
