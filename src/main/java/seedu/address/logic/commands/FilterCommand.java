package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil.FilterOperation;
import seedu.address.model.Model;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Adds, deletes or clears filters.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add, delete or clear any filters\n"
            + "Parameters: OPERATION (either add, delete or clear) \n"
            + PREFIX_COURSETUTORIAL + "COURSE_CODE "
            + PREFIX_TUTORIALNUMBER + "TUTORIAL_NUMBER (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + "add"
            + PREFIX_COURSETUTORIAL + "CS2103T"
            + PREFIX_TUTORIALNUMBER + "1";
    
    public static final String MESSAGE_ADD_SUCCESS = "Added %1$s";
    public static final String MESSAGE_DELETE_SUCCESS = "Removed %1$s";
    public static final String MESSAGE_CLEAR_SUCCESS = "Cleared all filters";
    public static final String MESSAGE_INVALID_OPERATION_FAILURE = "Invalid operation";


    private final FilterOperation operation;
    private final ContainsTagPredicate predicate;

    /**
     * Creates an FilterCommand to add, delete or clear the filter based on {@code FilterOperation}
     * and {@code Optional<String> course} and {@code Optional<String> tutorial}
     */
    public FilterCommand(FilterOperation operation, Optional<String> course, Optional<String> tutorial) {
        // TODO: Improve flow of this
        if (tutorial.isPresent()) {
            this.operation = operation;
            this.predicate = new ContainsTagPredicate(new Tag(String.join(" ", course.get(), tutorial.get())));
        } else {
            this.operation = operation;
            this.predicate = new ContainsTagPredicate(new Tag(course.orElse("PLACEHOLDER")));
        }
    }

    /**
     * Creates an FilterCommand to add, delete or clear the filter based on {@code FilterOperation}
     * and {@code Tag tag}
     */
    public FilterCommand(FilterOperation operation, Tag tag) {
        this.operation = operation;
        this.predicate = new ContainsTagPredicate(tag);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (operation == null) {
            throw new CommandException(MESSAGE_INVALID_OPERATION_FAILURE);
        }

        switch (operation) {
        case ADD:
            model.addFilter(predicate);
            return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, predicate));
        case DELETE:
            model.deleteFilter(predicate);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, predicate));
        case CLEAR:
            model.clearFilters();
            return new CommandResult(MESSAGE_CLEAR_SUCCESS);
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
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand e = (FilterCommand) other;
        return operation.equals(e.operation)
                && predicate.equals(e.predicate);
    }
}
