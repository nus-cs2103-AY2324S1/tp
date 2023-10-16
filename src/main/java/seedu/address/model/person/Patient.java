package seedu.address.model.person;


import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final MedicalHistory medicalHistory;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email,
                   Location location, Set<Tag> tags, MedicalHistory medicalHistory) {
        super(name, phone, email, location, tags);
        this.medicalHistory = medicalHistory;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }
    @Override
    public String toString() {
        String stringToAdd = ", medical history=" + medicalHistory;
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
        return super.getName().equals(otherPatient.getName())
                && super.getPhone().equals(otherPatient.getPhone())
                && super.getEmail().equals(otherPatient.getEmail())
                && super.getLocation().equals(otherPatient.getLocation())
                && super.getTags().equals(otherPatient.getTags())
                && medicalHistory.equals(otherPatient.medicalHistory);
    }
}
