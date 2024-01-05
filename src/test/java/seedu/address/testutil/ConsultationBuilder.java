package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Consultation objects.
 */
public class ConsultationBuilder {
    public static final String DEFAULT_DATE = "11/11/2023";
    public static final String DEFAULT_TIME = "11:11";
    public static final StudentSet DEFAULT_STUDENTS = TypicalPersons.DEFAULT_PERSONS;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDate date;
    private LocalTime time;
    private StudentSet students;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        this.date = LocalDate.parse(DEFAULT_DATE, dateFormatter);
        this.time = LocalTime.parse(DEFAULT_TIME, timeFormatter);
        this.students = DEFAULT_STUDENTS;
    }

    /**
     * Initializes the ConsultationBuilder with the data of {@code consultation}.
     */
    public ConsultationBuilder(Consultation consultation) {
        this.date = consultation.getDate();
        this.time = consultation.getTime();
        this.students = consultation.getStudents();
    }

    /**
     * Sets the {@code Date} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDate(String date) {
        this.date = LocalDate.parse(date.trim(), dateFormatter);;
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTime(String time) {
        this.time = LocalTime.parse(time.trim(), timeFormatter);;
        return this;
    }

    /**
     * Get the set of students for the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withStudents(Person ... students) {
        this.students = SampleDataUtil.getStudentSet(students);
        return this;
    }

    public Consultation build() {
        return new Consultation(date, time, students);
    }
}
