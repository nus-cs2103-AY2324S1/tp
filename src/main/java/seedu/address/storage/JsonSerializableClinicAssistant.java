package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.ReadOnlyClinicAssistant;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableClinicAssistant {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableClinicAssistant(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableClinicAssistant(ReadOnlyClinicAssistant source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        doctors.addAll(source.getDoctorList().stream().map(JsonAdaptedDoctor::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClinicAssistant toModelType() throws IllegalValueException {
        ClinicAssistant clinicAssistant = new ClinicAssistant();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType(); // creates the person

            // creates the appointments of the person
            ArrayList<Appointment> appointments = jsonAdaptedPerson.toModelTypeAppointments(person);

            // adds the appointments to the patient
            person.setAppointments(appointments);
            if (clinicAssistant.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            clinicAssistant.addPerson(person);


            // ADDS ENTIRE LIST TO BE APPENDED TO THE MAIN APPOINTMENT LIST.
            clinicAssistant.addAppointmentAsList(appointments);
        }
        for (JsonAdaptedDoctor jsonAdaptedDoctor: doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            for (Appointment appointment: clinicAssistant.getAppointmentList()) {
                if (doctor.getName().equals(new Name(appointment.getName()))) {
                    doctor.addAppointment(appointment);
                }
            }
            clinicAssistant.addDoctor((doctor));
        }
        return clinicAssistant;
    }

}
