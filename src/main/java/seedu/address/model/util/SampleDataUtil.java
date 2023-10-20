package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.risklevel.RiskLevel;
import seedu.address.model.student.Address;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("high")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("medium")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("low")),
            new Student(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("high")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("medium")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("low"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(new DateTime("2023-10-14 15:30:45"), new Name("Alex Yeoh"),
                    new Description("First Session")),
            new Appointment(new DateTime("2023-10-15 10:00:00"), new Name("Bernice Yu"),
                    new Description("Third Session")),
            new Appointment(new DateTime("2023-10-16 14:45:30"), new Name("David Li"),
                    new Description("Check-up")),
            new Appointment(new DateTime("2023-10-17 11:20:15"), new Name("Irfan Ibrahim"),
                    new Description("Follow-up")),
            new Appointment(new DateTime("2023-10-18 09:30:00"), new Name("Roy Balakrishnan"),
                    new Description("Check-up")),
            new Appointment(new DateTime("2023-10-19 16:15:45"), new Name("Alex Yeoh"),
                    new Description("Second Session")),
            new Appointment(new DateTime("2023-10-20 13:00:30"), new Name("Charlotte Oliveiro"),
                    new Description("Check-up")),
        };
    }
    public static ReadOnlyWellNus getSampleAddressBook() {
        WellNus sampleAb = new WellNus();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<RiskLevel> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(RiskLevel::new)
                .collect(Collectors.toSet());
    }

}
