package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds a student to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Import a csv file with students details to the address book. "
            + "Parameters: File Path\n"
            + "Example: " + COMMAND_WORD + " StudentData.csv\n"
            + "Note that subjects must be at the last column";

    public static final String MESSAGE_SUCCESS = "All students data imported";
    public static final String MESSAGE_DUPLICATE_PERSON = " are duplicated";


    private final String filePath;
    private final List<Student> students;
    private final List<Name> duplicateStudents;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public ImportCommand(List<Student> students, String filePath) {
        requireNonNull(students);
        this.students = students;
        this.filePath = filePath;
        this.duplicateStudents = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Student s : students) {
            if (!model.hasPerson(s)) {
                model.addPerson(s);
            } else if (model.hasPerson(s) && !duplicateStudents.contains(s.getName())) {
                duplicateStudents.add(s.getName());
            }
        }

        if (!duplicateStudents.isEmpty()) {
            throw new CommandException(duplicateStudents + MESSAGE_DUPLICATE_PERSON);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherAddCommand = (ImportCommand) other;
        return filePath.equals(otherAddCommand.filePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Import from ", filePath)
                .toString();
    }
}
