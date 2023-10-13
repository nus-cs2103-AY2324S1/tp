package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Consultation objects.
 */
public class ConsultationBuilder {
    public static final String DEFAULT_DATE = "11/11/2023";
    public static final String DEFAULT_TIME = "11:11";
    public static final Set<Person> DEFAULT_STUDENTS = SampleDataUtil.getStudentSet(
            TypicalPersons.AMY,
            TypicalPersons.BOB,
            TypicalPersons.CARL
    );
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDate date;
    private LocalTime time;
    private Set<Person> students;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        this.date = LocalDate.parse(DEFAULT_DATE, DATE_FORMATTER);
        this.time = LocalTime.parse(DEFAULT_TIME, TIME_FORMATTER);
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
        this.date = LocalDate.parse(date, DATE_FORMATTER);;
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTime(String time) {
        this.time = LocalTime.parse(time, TIME_FORMATTER);;
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withStudents(Person ... students) {
        this.students = SampleDataUtil.getStudentSet(students);
        return this;
    }

    public Consultation build() {
        return new Consultation(date, time, students);
    }
}
