package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Adds, deletes or clears filters.
 */
public class CourseCommand extends Command {
    public static final String COMMAND_WORD = "course";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Create, delete or switch address books\n"
            + "Parameters: OPERATION (either create, delete or switch) \n"
            + PREFIX_COURSETUTORIAL + "COURSE_CODE \n"
            + "Example: " + COMMAND_WORD + " "
            + "add "
            + PREFIX_COURSETUTORIAL + "CS2103T ";

    public static final String MESSAGE_CREATE_SUCCESS = "Created %1$s address book.";
    public static final String MESSAGE_DELETE_SUCCESS = "Deleted %1$s address book.";
    public static final String MESSAGE_SWITCH_SUCCESS = "Switched to %1$s address book.";

    public static final String MESSAGE_INVALID_OPERATION_FAILURE = "Invalid course operation";
    public static final String MESSAGE_CREATE_DUPLICATE_FAILURE = "%1$s address book exists";
    public static final String MESSAGE_NO_EXIST_FAILURE = "%1$s address book does not exist";

    private final CourseOperation operation;
    private final String courseCode;

    /**
     * Creates an FilterCommand to add, delete or clear the filter based on {@code FilterOperation}
     * and {@code Optional<String> course} and {@code Optional<String> tutorial}
     */
    public CourseCommand(CourseOperation operation, String courseCode) {
        requireNonNull(operation);
        requireNonNull(courseCode);

        this.operation = operation;
        this.courseCode = courseCode;
    }

    private CommandResult addAddressBookHelper(Model model) throws CommandException {
        if (model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_CREATE_DUPLICATE_FAILURE, courseCode));
        }

        model.addAddressBook(new AddressBook(courseCode));
        return new CommandResult(String.format(MESSAGE_CREATE_SUCCESS, courseCode));
    }

    private CommandResult deleteAddressBookHelper(Model model) throws CommandException {
        if (!model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_NO_EXIST_FAILURE, courseCode));
        }

        model.deleteAddressBook(courseCode);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, courseCode));
    }

    private CommandResult changeAddressBookHelper(Model model) throws CommandException {
        if (!model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_NO_EXIST_FAILURE, courseCode));
        }

        model.setActiveAddressBook(courseCode);
        return new CommandResult(String.format(MESSAGE_SWITCH_SUCCESS, courseCode));
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (operation == null) {
            throw new CommandException(MESSAGE_INVALID_OPERATION_FAILURE);
        }

        switch (operation) {
        case CREATE:
            return addAddressBookHelper(model);
        case DELETE:
            return deleteAddressBookHelper(model);
        case SWITCH:
            return changeAddressBookHelper(model);
        default:
            throw new CommandException(MESSAGE_INVALID_OPERATION_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseCommand)) {
            return false;
        }

        CourseCommand e = (CourseCommand) other;
        return operation.equals(e.operation)
                && courseCode.equals(e.courseCode);
    }
}
