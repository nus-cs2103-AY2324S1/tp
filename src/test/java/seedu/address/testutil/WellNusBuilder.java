package seedu.address.testutil;

import seedu.address.model.WellNus;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building WellNus objects.
 * Example usage: <br>
 *     {@code WellNus wn = new WellNusBuilder().withStudent("John", "Doe").build();}
 */
public class WellNusBuilder {

    private WellNus wellNus;

    public WellNusBuilder() {
        wellNus = new WellNus();
    }

    public WellNusBuilder(WellNus wellNus) {
        this.wellNus = wellNus;
    }

    /**
     * Adds a new {@code Student} to the {@code WellNus} that we are building.
     */
    public WellNusBuilder withStudent(Student student) {
        wellNus.addStudent(student);
        return this;
    }

    public WellNus build() {
        return wellNus;
    }
}
