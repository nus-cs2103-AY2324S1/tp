package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the Wellnus data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final WellNus wellNus;
    private final UserPrefs userPrefs;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given Wellnus instance and userPrefs.
     */
    public ModelManager(ReadOnlyWellNus wellNus, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(wellNus, userPrefs);

        logger.fine("Initializing with WellNus instance: " + wellNus + " and user prefs " + userPrefs);

        this.wellNus = new WellNus(wellNus);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredStudents = new FilteredList<>(this.wellNus.getStudentList());
        this.filteredAppointments = new FilteredList<>(this.wellNus.getAppointmentList());
    }

    public ModelManager() {
        this(new WellNus(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getWellNusFilePath() {
        return userPrefs.getWellNusFilePath();
    }

    @Override
    public void setWellNusFilePath(Path wellNusFilePath) {
        requireNonNull(wellNusFilePath);
        userPrefs.setWellNusFilePath(wellNusFilePath);
    }

    //=========== WellNus ================================================================================

    @Override
    public void setWellNusData(ReadOnlyWellNus wellNusData) {
        this.wellNus.resetData(wellNusData);
    }

    @Override
    public ReadOnlyWellNus getWellNusData() {
        return wellNus;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return wellNus.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        wellNus.removeStudent(target);
        logger.fine("Deleting appointments which contain the name: " + target.getName());
        wellNus.removeRelatedAppointments(target.getName());
    }

    @Override
    public void addStudent(Student student) {
        wellNus.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        wellNus.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return wellNus.hasAppointment(appointment);
    }

    @Override
    public boolean hasOverlapsWithAppointments(Appointment appointment) {
        requireNonNull(appointment);
        return wellNus.hasOverlapsWithAppointments(appointment);
    }

    @Override
    public boolean hasNoStudentForAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return wellNus.hasNoStudentForAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        wellNus.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        wellNus.removeAppointment(target);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Appointment}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return wellNus.equals(otherModelManager.wellNus)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

}
