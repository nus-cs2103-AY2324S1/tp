package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.ContainsTutorialGroupPredicate;
import seedu.address.model.person.TutorialGroup;

/**
 * Lists all persons in the address book to the user.
 */
public class ListStudentsCommand extends ListCommand {

    public static final String COMMAND_WORD = "students";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists students.\n"
            + "Parameters: "
            + PREFIX_TUTORIALGROUP + " TUTORIALGROUPID "
            + "Example: list " + COMMAND_WORD + " tg/ G01";
    public static final String MESSAGE_SUCCESS = "Listed all specified students";


    /**
     * @param tg Tutorial group to list
     * @param tutorialGroupPredicate Predicate used to filter for students in the tutorial group
     */
    public ListStudentsCommand(TutorialGroup tg, ContainsTutorialGroupPredicate tutorialGroupPredicate) {
        super(tg, tutorialGroupPredicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        TutorialGroup placeholder = new TutorialGroup("PLACEHOLDER");

        if (this.tg.equals(placeholder)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            model.updateFilteredPersonList(tutorialGroupPredicate);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
