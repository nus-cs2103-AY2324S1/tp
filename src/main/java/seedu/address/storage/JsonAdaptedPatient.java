package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

class JsonAdaptedPatient extends JsonAdaptedPerson {
    private final String age;
    private final String medicalHistory;
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,

                             @JsonProperty("email") String email,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("age") String age,
                              @JsonProperty("MedicalHistory") String medicalHistory) {
        super(name, phone, email, tags);
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    public JsonAdaptedPatient(Patient source) {
        super(source);
        this.age = source.getAge().value;
        this.medicalHistory = source.getMedicalHistory().value;
    }

    public String getMedicalHistory() throws IllegalValueException {
        if (medicalHistory == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalHistory.class.getSimpleName()));
        }
        if (!MedicalHistory.isValidMedicalHistory(medicalHistory)) {
            throw new IllegalValueException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        return medicalHistory;
    }

    public String getAge() throws IllegalValueException {
        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        return age;
    }

    @Override
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = new Name(getName());
        final Phone modelPhone = new Phone(getPhone());
        final Email modelEmail = new Email(getEmail());
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Age age = new Age(getAge());
        final MedicalHistory medicalHistory = new MedicalHistory(getMedicalHistory());

        return new Patient(modelName, modelPhone, modelEmail, modelTags, age, medicalHistory);

    }
}
