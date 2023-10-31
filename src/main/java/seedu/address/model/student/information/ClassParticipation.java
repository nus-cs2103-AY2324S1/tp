package seedu.address.model.student.information;

/**
 * Represents the class participation of a specific tutorial slot.
 */
public class ClassParticipation {

    private boolean hasParticipated;

    public ClassParticipation() {
        hasParticipated = false;
    }

    /**
     * Constructs a {@code ClassParticipation} with the given hasParticipated value.
     */
    public ClassParticipation(boolean hasParticipated) {
        this.hasParticipated = hasParticipated;
    }

    public void mark() {
        hasParticipated = true;
    }

    public void unmark() {
        hasParticipated = false;
    }

    public boolean getParticipation() {
        return hasParticipated;
    }

    @Override
    public String toString() {
        return hasParticipated ? "Participated" : "Did not Participate";
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
        return hasParticipated == otherClassParticipation.hasParticipated;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(hasParticipated);
    }
}
