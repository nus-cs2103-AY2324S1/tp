package seedu.address.model.student.grades;

/**
 * Represents the class participation of a specific tutorial slot.
 */
public class ClassParticipation {

    private boolean participated;

    public ClassParticipation() {
        participated = false;
    }

    public void mark() {
        participated = true;
    }

    public void unmark() {
        participated = false;
    }

    public boolean getParticipated() {
        return participated;
    }

    @Override
    public String toString() {
        return participated ? "Participated" : "Did not Participate";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassParticipation)) {
            return false;
        }

        ClassParticipation otherClassParticipation = (ClassParticipation) other;
        return participated == otherClassParticipation.participated;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(participated);
    }
}
