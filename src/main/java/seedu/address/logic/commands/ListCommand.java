package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists either students or attendance.\n"
            + "Parameters: "
            + "LIST_TYPE (must be either 'students' or 'attendance') "
            + PREFIX_TUTORIALGROUP + " TUTORIALGROUPID "
            + "Example: " + COMMAND_WORD + " students" + " /tg G01";
    public static final String MESSAGE_SUCCESS_STUDENTS = "Listed all specified students";
    public static final String MESSAGE_SUCCESS_ATTENDANCE = "Listed all absent students";

    private final String type;
    private final String tg;


    public ListCommand(String type, String tg) {
        requireNonNull(type);
        this.type = type;
        this.tg = tg;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (type.equals("students")) {
            if (tg.isBlank()) {
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            }
            return new CommandResult(MESSAGE_SUCCESS_STUDENTS);
        } else {
            if (tg.isBlank()) {
            }
            return new CommandResult(MESSAGE_SUCCESS_ATTENDANCE);
        }

    }
}
