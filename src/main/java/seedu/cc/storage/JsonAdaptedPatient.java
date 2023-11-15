package seedu.cc.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.patient.Nric;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.person.Age;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Person;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String nric;
    private final String phone;
    private final String email;
    private final String age;
    private final List<JsonAdaptedMedicalHistoryEvent> medicalHistoryEvents = new ArrayList<>();
    private final List<JsonAdaptedAppointmentEvent> appointmentEvents = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("nric") String nric,
                              @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                              @JsonProperty("age") String age,
                              @JsonProperty("medicalHistory") List<JsonAdaptedMedicalHistoryEvent> medicalHistoryEvents,
                              @JsonProperty("appointment") List<JsonAdaptedAppointmentEvent> appointmentEvents,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.nric = nric;
        this.phone = phone;
        this.email = email;
        this.age = age;
        if (medicalHistoryEvents != null) {
            this.medicalHistoryEvents.addAll(medicalHistoryEvents);
        }
        if (appointmentEvents != null) {
            this.appointmentEvents.addAll(appointmentEvents);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        nric = source.getNric().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        age = source.getAge().value;
        medicalHistoryEvents.addAll(source.getMedicalHistory().getMedicalHistoryEvents().stream()
                .map(JsonAdaptedMedicalHistoryEvent::new)
                .collect(Collectors.toList()));
        appointmentEvents.addAll(source.getAppointmentList().getAppointmentList().stream()
                .map(JsonAdaptedAppointmentEvent::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> patientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            patientTags.add(tag.toModelType());
        }

        final PatientMedicalHistory modelPatientMedicalHistory = new PatientMedicalHistory();
        for (JsonAdaptedMedicalHistoryEvent event : medicalHistoryEvents) {
            modelPatientMedicalHistory.addMedicalHistoryEvent(event.toModelType());
        }

        final PatientAppointmentList modelPatientAppointmentList = new PatientAppointmentList();
        for (JsonAdaptedAppointmentEvent event : appointmentEvents) {
            modelPatientAppointmentList.addAppointmentList(event.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }

        final Age modelAge = new Age(age);
        final Set<Tag> modelTags = new HashSet<>(patientTags);
        return new Patient(modelName, modelNric, modelPhone, modelEmail,
                modelAge, modelPatientAppointmentList, modelPatientMedicalHistory, modelTags);
    }

}
