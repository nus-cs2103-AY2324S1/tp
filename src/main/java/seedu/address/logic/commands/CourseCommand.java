package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Create, delete, switch or edit address books.
 */
public class CourseCommand extends Command {
    public static final String COMMAND_WORD = "course";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Create, delete, switch or edit address books\n"
            + "Parameters: OPERATION (create, delete, switch or edit) \n"
            + PREFIX_COURSETUTORIAL + "COURSE_CODE \n"
            + "Example: " + COMMAND_WORD + " "
            + "add "
            + PREFIX_COURSETUTORIAL + "CS2103T ";

    public static final String MESSAGE_CREATE_SUCCESS = "Created %1$s address book.";
    public static final String MESSAGE_DELETE_SUCCESS = "Deleted %1$s address book.";
    public static final String MESSAGE_SWITCH_SUCCESS = "Switched to %1$s address book.";
    public static final String MESSAGE_EDIT_SUCCESS = "Edited address book course code to %1$s.";

    public static final String MESSAGE_INVALID_OPERATION_FAILURE = "Invalid course operation";
    public static final String MESSAGE_DUPLICATE_ADDRESS_BOOK_FAILURE = "%1$s address book exists";
    public static final String MESSAGE_NO_EXIST_FAILURE = "%1$s address book does not exist";
    public static final String MESSAGE_NO_ADDRESS_BOOK_FAILURE = "No address book selected";

    private final CourseOperation operation;
    private final String courseCode;

    /**
     * Creates an CourseCommand to create, delete or switch the address book based on {@code CourseOperation}
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
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ADDRESS_BOOK_FAILURE, courseCode));
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
        model.clearFilters();
        return new CommandResult(String.format(MESSAGE_SWITCH_SUCCESS, courseCode));
    }

    private CommandResult editAddressBookHelper(Model model) throws CommandException {
        if (model.getAddressBook() == null) {
            throw new CommandException(String.format(MESSAGE_NO_ADDRESS_BOOK_FAILURE));
        }

        if (model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ADDRESS_BOOK_FAILURE, courseCode));
        }

        model.setAddressBook(new AddressBook(courseCode, model.getAddressBook()));
        model.setActiveAddressBook(courseCode);
        return new CommandResult(String.format(MESSAGE_EDIT_SUCCESS, courseCode));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (operation) {
        case CREATE:
            return addAddressBookHelper(model);
        case DELETE:
            return deleteAddressBookHelper(model);
        case SWITCH:
            return changeAddressBookHelper(model);
        case EDIT:
            return editAddressBookHelper(model);
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
