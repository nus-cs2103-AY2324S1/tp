package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Age;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Patient;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder extends PersonBuilder {
    public static final String DEFAULT_AGE = "30";
    private Age age;
    private Set<MedicalHistory> medicalHistory;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        super();
        age = new Age(DEFAULT_AGE);
        medicalHistory = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        super(patientToCopy);
        age = patientToCopy.getAge();
        medicalHistory = patientToCopy.getMedicalHistory();
    }

    public Age getAge() {
        return age;
    }
    public Set<MedicalHistory> getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the {@code Age} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code MedicalHistory} of the {@code Patient} that we are building.
     */
    public PatientBuilder withMedicalHistory(String ... medicalHistory) {
        this.medicalHistory = SampleDataUtil.getMedicalHistorySet(medicalHistory);
        return this;
    }

    @Override
    public Patient build() {
        return new Patient(getName(), getPhone(), getEmail(), getTags(), getAge(), getMedicalHistory());
    }

}
