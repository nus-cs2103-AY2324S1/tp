package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final List<JsonAdaptedMedicalHistory> medicalHistory = new ArrayList<>();
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,

                             @JsonProperty("email") String email,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("age") String age,
                              @JsonProperty("MedicalHistory") List<JsonAdaptedMedicalHistory> medicalHistory) {
        super(name, phone, email, tags);
        this.age = age;
        if (medicalHistory != null) {
            this.medicalHistory.addAll(medicalHistory);
        }
    }

    public JsonAdaptedPatient(Patient source) {
        super(source);
        this.age = source.getAge().value;
        medicalHistory.addAll(source.getMedicalHistory().stream()
                .map(JsonAdaptedMedicalHistory::new)
                .collect(Collectors.toList()));
    }

    public List<JsonAdaptedMedicalHistory> getMedicalHistory() throws IllegalValueException {
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


    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    @Override
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }
        final List<MedicalHistory> patientMedicalHistory = new ArrayList<>();
        for (JsonAdaptedMedicalHistory medicalHistory: getMedicalHistory()) {
            patientMedicalHistory.add(medicalHistory.toModelType());
        }

        final Name modelName = new Name(getName());
        final Phone modelPhone = new Phone(getPhone());
        final Email modelEmail = new Email(getEmail());
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Age age = new Age(getAge());
        final Set<MedicalHistory> medicalHistory = new HashSet<>(patientMedicalHistory);

        return new Patient(modelName, modelPhone, modelEmail, modelTags, age, medicalHistory);

    }
}
