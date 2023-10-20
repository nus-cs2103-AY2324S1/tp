package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * An Immutable WellNus that is serializable to JSON format.
 */
@JsonRootName(value = "wellnus")
class JsonSerializableWellNus {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWellNus} with the given students.
     */
    @JsonCreator
    public JsonSerializableWellNus(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.students.addAll(students);
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyWellNus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWellNus}.
     */
    public JsonSerializableWellNus(ReadOnlyWellNus source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList()
                .stream().map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this WellNus book into the model's {@code WellNus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public WellNus toModelType() throws IllegalValueException {
        WellNus wellNus = new WellNus();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (wellNus.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            wellNus.addStudent(student);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (wellNus.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            wellNus.addAppointment(appointment);
        }
        return wellNus;
    }

}
