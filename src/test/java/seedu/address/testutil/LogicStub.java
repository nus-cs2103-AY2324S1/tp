package seedu.address.testutil;

import java.nio.file.Path;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * A LogicStub class used for unit testing for MainWindow
 */
public class LogicStub implements Logic {
    private final Model model = new ModelStub(); // Replace with your actual ModelStub

    @Override
    public CommandResult execute(String commandText) {
        // Implement logic stub behavior for command execution
        return null; // Replace with your desired behavior
    }

    @Override
    public ReadOnlyWellNus getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return new FilteredList<Student>(FXCollections.observableArrayList());
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return new FilteredList<Appointment>(FXCollections.observableArrayList());
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }
}
