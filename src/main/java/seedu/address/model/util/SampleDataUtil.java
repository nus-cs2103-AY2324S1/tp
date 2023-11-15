package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static final Set<Appointment> EMPTY_APPOINTMENTS = new HashSet<>();
    public static final Appointment APPOINTMENT_1 = new Appointment(new Ic("S8811111Z"),
            new Ic("S1111111Z"), new AppointmentTime("2023-10-31 14:00"));
    public static final Appointment APPOINTMENT_2 = new Appointment(new Ic("S8811112Z"),
            new Ic("S1111111Z"), new AppointmentTime("2023-10-31 15:00"));
    public static final Appointment APPOINTMENT_3 = new Appointment(new Ic("S8811112Z"),
            new Ic("S1111112Z"), new AppointmentTime("2023-10-31 16:00"));

    // persons should not be used anymore
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Phone("99272758"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    EMPTY_REMARK, new Gender("M"), new Ic("S1111111Z"), new Condition("Unknown"),
                    new BloodType("O+"), getAppointmentSet(APPOINTMENT_1, APPOINTMENT_2),
                    getTagSet("priority: LOW")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Phone("87438807"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    EMPTY_REMARK, new Gender("F"), new Ic("S1111112Z"), new Condition("Unknown"),
                    new BloodType("O+"), getAppointmentSet(APPOINTMENT_3),
                    getTagSet("priority: LOW")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Phone("87438807"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    EMPTY_REMARK, new Gender("F"), new Ic("S1111113Z"), new Condition("Unknown"),
                    new BloodType("O+"), EMPTY_APPOINTMENTS,
                    getTagSet("priority: MEDIUM")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Phone("87438807"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    EMPTY_REMARK, new Gender("M"), new Ic("S1111114Z"), new Condition("Unknown"),
                    new BloodType("O+"), EMPTY_APPOINTMENTS,
                    getTagSet("priority: MEDIUM")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Phone("87438807"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                    new Gender("M"), new Ic("S1111115Z"), new Condition("Unknown"), new BloodType("O+"),
                    EMPTY_APPOINTMENTS, getTagSet("priority: LOW")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Phone("87438807"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                    new Gender("M"), new Ic("S1111116Z"), new Condition("Unknown"), new BloodType("O+"),
                    EMPTY_APPOINTMENTS, getTagSet("priority: HIGH"))
        };
    }

    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(new Name("Adam Lim"), new Phone("87438000"), new Email("adamlim@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK, new Gender("M"),
                    new Ic("S8811111Z"), getAppointmentSet(APPOINTMENT_1), getTagSet("SURGEON")),
            new Doctor(new Name("Bernard Tan"), new Phone("99272000"), new Email("bernardtan@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK, new Gender("M"),
                    new Ic("S8811112Z"), getAppointmentSet(APPOINTMENT_2, APPOINTMENT_3),
                    getTagSet("CARDIOLOGIST"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }

        for (Doctor sampleDoctor : getSampleDoctors()) {
            sampleAb.addDoctor(sampleDoctor);
        }
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    public static ReadOnlyAddressBook getSamplePatientAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    public static ReadOnlyAddressBook getSampleDoctorsAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Doctor sampleDoctor : getSampleDoctors()) {
            sampleAb.addDoctor(sampleDoctor);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a appointment set containing the list of strings given.
     */
    public static Set<Appointment> getAppointmentSet(Appointment... appointments) {
        return Arrays.stream(appointments)
                .collect(Collectors.toSet());
    }

}
