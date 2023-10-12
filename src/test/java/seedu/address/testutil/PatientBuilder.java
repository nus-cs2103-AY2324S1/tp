package seedu.address.testutil;

import seedu.address.model.person.Patient;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder extends PersonBuilder {
    public PatientBuilder() {
        super();
    }

    public PatientBuilder(Patient patientToCopy) {
        super(patientToCopy);
    }

    @Override
    public Patient build() {
        return new Patient(getName(), getPhone(), getEmail(), getAddress(), getTags());
    }

}
