package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title {

    public static final String MESSAGE_CONSTRAINTS =
            "Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String meetingTitle;
    public Title(String meetingTitle) {
        requireNonNull(meetingTitle);
        checkArgument(isValidTitle(meetingTitle), MESSAGE_CONSTRAINTS);
        this.meetingTitle = meetingTitle;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return meetingTitle;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Title)) {
            return false;
        }

        Title otherTitle = (Title) other;
        return meetingTitle.equals(otherTitle.meetingTitle);
    }

    @Override
    public int hashCode() {
        return meetingTitle.hashCode();
    }
}
