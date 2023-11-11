package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;


/**
 * Jackson-friendly version of {@link Person}.
 */
public abstract class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT_TIME =
            "Person has more than 1 appointment at the same timing!";
    public static final String MESSAGE_INVALID_APPOINTMENT =
            "Person contains an appointment that does not belong to him!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String remark;
    private final String gender;
    private final String ic;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("remark") String remark, @JsonProperty("gender") String gender,
                             @JsonProperty("ic") String ic,
                             @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.gender = gender;
        this.ic = ic;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        gender = source.getGender().value;
        ic = source.getIc().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointments().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Gives the list of tags of the Person.
     *
     * @return a List of JsonAdaptedTags
     */
    public List<JsonAdaptedTag> getTags() {
        return this.tags;
    }

    public List<JsonAdaptedAppointment> getAppointments() {
        return this.appointments;
    }

    /**
     * Checks the name given by storage.
     *
     * @return a valid name object.
     * @throws IllegalValueException if name is not valid.
     */
    public Name checkName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Checks the phone given by storage.
     *
     * @return a valid phone object.
     * @throws IllegalValueException if phone is not valid.
     */
    public Phone checkPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    /**
     * Checks the email given by storage.
     *
     * @return a valid email object.
     * @throws IllegalValueException if email is not valid.
     */
    public Email checkEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    /**
     * Checks the address given by storage.
     *
     * @return a valid address
     * @throws IllegalValueException if address is not valid.
     */
    public Address checkAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    /**
     * Checks the remark given by storage.
     *
     * @return a valid remark
     * @throws IllegalValueException if remark is not valid.
     */
    public Remark checkRemark() throws IllegalValueException {
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        return new Remark(remark);
    }

    /**
     * Checks the gender given by storage.
     *
     * @return a valid gender
     * @throws IllegalValueException if gender is not valid.
     */
    public Gender checkGender() throws IllegalValueException {
        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(gender);
    }

    /**
     * Checks the ic given by storage.
     *
     * @return a valid ic
     * @throws IllegalValueException if ic is not valid.
     */
    public Ic checkIc() throws IllegalValueException {
        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(ic);
    }

    /**
     * Gives the list of appointments of the Person.
     * Please ensure that this method is called only after the Ic has been added to the person.
     * This checks for any appointments happening at the same time and any appointments not belonging to this person.
     *
     * @return a List of JsonAdaptedAppointments
     */
    public List<Appointment> checkAppointments() throws IllegalValueException {
        final List<Appointment> listOfAppointments = new ArrayList<>();
        for (JsonAdaptedAppointment appointment : appointments) {
            listOfAppointments.add(appointment.toModelType());
        }
        return listOfAppointments;
    }
}



