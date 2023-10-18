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
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate doctor(s).";
    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";


    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();
    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                       @JsonProperty("doctors") List<JsonAdaptedDoctor> doctors) {
        this.doctors.addAll(doctors);
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        doctors.addAll(source.getDoctorList().stream().map(JsonAdaptedDoctor::new).collect(Collectors.toList()));
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            addressBook.addPatient(patient);
        }
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (addressBook.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            addressBook.addDoctor(doctor);
        }
        return addressBook;
    }

}
