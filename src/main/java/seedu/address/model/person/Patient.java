package seedu.address.model.person;


import java.util.HashSet;
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

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two patients.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        // instanceof handles nulls
        if (otherPerson instanceof Patient) {
            return otherPerson.getName().equals(getName());
        }

        return false;
    }

    /**
     * creates another instance of Patient with the same field values
     * @return
     */
    @Override
    public Person getCopy() {
        Set<MedicalHistory> medicalHistoriesCopy = new HashSet<MedicalHistory>();
        for (MedicalHistory medicalHistoryOriginal : this.medicalHistory) {
            MedicalHistory copy = medicalHistoryOriginal.getCopy();
            medicalHistoriesCopy.add(copy);
        }

        Set<Tag> tagCopies = new HashSet<Tag>();
        for (Tag originalTag : super.getTags()) {
            Tag copy = originalTag.getCopy();
            tagCopies.add(copy);
        }
        return new Patient(
                super.getName().getCopy(),
                super.getPhone().getCopy(),
                super.getEmail().getCopy(),
                tagCopies,
                this.age.getCopy(),
                medicalHistoriesCopy
        );
    }
}
