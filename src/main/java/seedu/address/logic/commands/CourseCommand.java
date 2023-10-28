package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Adds, deletes or clears filters.
 */
public class CourseCommand extends Command {
    public static final String COMMAND_WORD = "course";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add, delete or clear any filters\n"
            + "Parameters: OPERATION (either add, delete or clear) \n"
            + PREFIX_COURSETUTORIAL + "COURSE_CODE "
            + PREFIX_TUTORIALGROUP + "TUTORIAL_GROUP_ID \n"
            + "Example: " + COMMAND_WORD + " "
            + "add "
            + PREFIX_COURSETUTORIAL + "CS2103T "
            + PREFIX_TUTORIALGROUP + "G01";

    public static final String MESSAGE_ADD_SUCCESS = "Added %1$s";
    public static final String MESSAGE_DELETE_SUCCESS = "Removed %1$s";
    public static final String MESSAGE_CLEAR_SUCCESS = "Cleared all filters";
    public static final String MESSAGE_INVALID_OPERATION_FAILURE = "Invalid operation";
    public static final String MESSAGE_ADD_DUPLICATE_FAILURE = "Filter already exists";
    public static final String MESSAGE_DELETE_NOTHING_FAILURE = "Filter does not exist";
    public static final String MESSAGE_CHANGE_SUCCESS = "Changed to %1$s";
    public static final String MESSAGE_CHANGE_NOTHING_FAILURE = "Filter does not exist";

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
            throw new CommandException(String.format(MESSAGE_ADD_DUPLICATE_FAILURE, courseCode));
        }

        model.addAddressBook(new AddressBook(courseCode));
        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, courseCode));
    }

    private CommandResult deleteAddressBookHelper(Model model) throws CommandException {
        if (!model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_DELETE_NOTHING_FAILURE, courseCode));
        }

        model.deleteAddressBook(courseCode);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, courseCode));
    }

    private CommandResult changeAddressBookHelper(Model model) throws CommandException {
        if (!model.hasAddressBook(courseCode)) {
            throw new CommandException(String.format(MESSAGE_CHANGE_NOTHING_FAILURE, courseCode));
        }

        model.setActiveAddressBook(courseCode);
        return new CommandResult(String.format(MESSAGE_CHANGE_SUCCESS, courseCode));
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (operation == null) {
            throw new CommandException(MESSAGE_INVALID_OPERATION_FAILURE);
        }

        switch (operation) {
        case ADD:
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
