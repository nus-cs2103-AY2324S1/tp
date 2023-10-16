package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.ContainsTutorialGroupPredicate;
import seedu.address.model.person.TutorialGroup;

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
            + "Example: " + COMMAND_WORD + " students" + " tg/ G01";
    public static final String MESSAGE_SUCCESS_STUDENTS = "Listed all specified students";
    public static final String MESSAGE_SUCCESS_ATTENDANCE = "Listed attendance records \n a\n a\n a\n a\n a\n a\n a";
    private final String type;
    private final TutorialGroup tg;
    private final ContainsTutorialGroupPredicate predicate;


    public ListCommand(String type, TutorialGroup tg, ContainsTutorialGroupPredicate predicate) {
        requireNonNull(type);
        this.type = type;
        this.tg = tg;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        TutorialGroup placeholder = new TutorialGroup("PLACEHOLDER");

        if (type.equals("students")) {
            if (tg.equals(placeholder)) {
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            } else {
                model.updateFilteredPersonList(predicate);
            }
            return new CommandResult(MESSAGE_SUCCESS_STUDENTS);
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_ATTENDANCE);
        }
    }
}
