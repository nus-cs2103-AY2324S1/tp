package seedu.address.model.student.grades;

/**
 * Represents the class participation of a specific tutorial slot.
 */
public class ClassParticipation {

    private boolean isParticipated;

    public ClassParticipation() {
        isParticipated = false;
    }

    /**
     * Constructs a {@code ClassParticipation} with the given isParticipated value.
     */
    public ClassParticipation(boolean isParticipated) {
        this.isParticipated = isParticipated;
    }

    public void mark() {
        isParticipated = true;
    }

    public void unmark() {
        isParticipated = false;
    }

    public boolean getParticipated() {
        return isParticipated;
    }

    @Override
    public String toString() {
        return isParticipated ? "Participated" : "Did not Participate";
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
        return isParticipated == otherClassParticipation.isParticipated;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isParticipated);
    }
}
