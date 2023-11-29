package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_DENTIST = "Dentists list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TREATMENT = "Treatments list contains duplicate treatment(s).";
    public static final String MESSAGE_CLASHING_APPOINTMENTS = "Dentist is not free for this time slot";

    private final List<JsonAdaptedDentist> dentists = new ArrayList<>();
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedTreatment> treatments = new ArrayList<>();

    private String patientId;
    private String dentistId;
    private String appointmentId;


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and dentists.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("dentists") List<JsonAdaptedDentist> dentists) {
        this.persons.addAll(persons);
        this.dentists.addAll(dentists);
    }


    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        dentists.addAll(source.getDentistList().stream().map(JsonAdaptedDentist::new)
            .collect(Collectors.toList()));
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new)
            .collect(Collectors.toList()));
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new)
            .collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream()
            .map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
        treatments.addAll(source.getTreatmentList().stream().map(JsonAdaptedTreatment::new)
            .collect(Collectors.toList()));

        patientId = String.valueOf(source.getPatientId());
        dentistId = String.valueOf(source.getDentistId());
        appointmentId = String.valueOf(source.getAppointmentId());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedPatient adaptedPatient : patients) {
            Patient patient = adaptedPatient.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            addressBook.addPatient(patient);
        }

        for (JsonAdaptedDentist jsonAdaptedDentist : dentists) {
            Dentist dentist = jsonAdaptedDentist.toModelType();
            if (addressBook.hasDentist(dentist)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DENTIST);
            }
            addressBook.addDentist(dentist);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            /*if (addressBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_CLASHING_APPOINTMENTS);
            }*/
            addressBook.addAppointment(appointment);
        }

        for (JsonAdaptedTreatment jsonAdaptedTreatment : treatments) {
            Treatment treatment = jsonAdaptedTreatment.toModelType();
            if (addressBook.hasTreatment(treatment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TREATMENT);
            }
            addressBook.addTreatment(treatment);
        }

        addressBook.setDentistId(Long.parseLong(dentistId));
        addressBook.setPatientId(Long.parseLong(patientId));
        addressBook.setAppointmentId(Long.parseLong(appointmentId));
        return addressBook;
    }

}
