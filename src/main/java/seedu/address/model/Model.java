package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' wellnus storage file path.
     */
    Path getWellNusFilePath();

    /**
     * Sets the user prefs' wellnus storage file path.
     */
    void setWellNusFilePath(Path wellNusFilePath);

    /**
     * Replaces WellNus data with the data in {@code wellNus}.
     */
    void setWellNusData(ReadOnlyWellNus wellNus);

    /** Returns the WellNus */
    ReadOnlyWellNus getWellNusData();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the wellnus storage.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the wellnus storage.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the wellnus storage.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the wellnus storage.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * wellnus storage.
     */
    void setStudent(Student target, Student editedStudent);

    boolean hasAppointment(Appointment appointment);

    boolean hasOverlapsWithAppointments(Appointment appointment);

    boolean hasNoStudentForAppointment(Appointment appointment);

    void addAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the wellnus storage.
     */
    void deleteAppointment(Appointment target);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
