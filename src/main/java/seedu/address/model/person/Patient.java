package seedu.address.model.person;


import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final Age age;
    private final Set<MedicalHistory> medicalHistory;

    /**
     * Every field must be present and not null.
     */


    public Patient(Name name, Phone phone, Email email, Set<Tag> tags, Age age,
                   Set<MedicalHistory> medicalHistory) {
        super(name, phone, email, tags);
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    public Age getAge() {
        return age;
    }

    public Set<MedicalHistory> getMedicalHistory() {
        return medicalHistory;
    }
    @Override
    public String toString() {
        String stringToAdd = ", age=" + age + ", medical history=" + medicalHistory;
        return StringUtil.addFieldToPersonToString(stringToAdd, super.toString());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return super.equals(other)
                && age.equals(otherPatient.getAge())
                && medicalHistory.equals(otherPatient.medicalHistory);
    }
}
