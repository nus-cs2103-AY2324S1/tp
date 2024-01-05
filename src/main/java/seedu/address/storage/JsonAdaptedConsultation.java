package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;

/**
 * Jackson-friendly version of {@link Consultation}.
 */
public class JsonAdaptedConsultation {

    private final String date;
    private final String time;
    private final List<JsonAdaptedPerson> students = new ArrayList<>();

    /**
     * * Constructs a {@code JsonAdaptedConsultation} with the given consultation details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("date") String date, @JsonProperty("time") String time,
                                   @JsonProperty("students") List<JsonAdaptedPerson> students) {
        this.date = date;
        this.time = time;
        this.students.addAll(students);
    }

    /**
    * Converts a given {@code Consultation} into this class for Jackson use.
    */
    public JsonAdaptedConsultation(Consultation source) {
        date = source.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        time = source.getTime().toString();
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted consultation object into the model's {@code Consultation} object.
     *
     * @throws DateTimeParseException if there were date and time format constraints violated in the adapted
     *      consultation.
     */
    public Consultation toModelType() throws DateTimeParseException, IllegalValueException {
        // TODO check for invalid values of date and time
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedDate = LocalDate.parse(date, dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime formattedTime = LocalTime.parse(time, timeFormatter);

        final List<Person> studentsList = new ArrayList<>();
        StudentSet studentSet = new StudentSet();
        if (students != null) {
            for (JsonAdaptedPerson student : students) {
                studentsList.add(student.toModelType());
            }
            final Set<Person> students = new HashSet<>(studentsList);
            studentSet = new StudentSet(students);
        }
        return new Consultation(formattedDate, formattedTime, studentSet);
    }

}
