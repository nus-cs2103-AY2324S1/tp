package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import seedu.address.model.person.ContainsTutorialGroupPredicate;
import seedu.address.model.person.TutorialGroup;

/**
 * Lists all persons in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists either students or attendance.\n"
            + "Parameters: "
            + "LIST_TYPE (must be either 'students' or 'attendance') "
            + PREFIX_TUTORIALGROUP + " TUTORIALGROUPID "
            + PREFIX_TUTORIALNUMBER + " TUTORIALNUMBER (must be a positive integer) "
            + "[applicable for list attendance only] \n"
            + "Example: " + COMMAND_WORD + " students" + " tg/ G01";
    final TutorialGroup tg;
    final ContainsTutorialGroupPredicate tutorialGroupPredicate;

    /**
     * @param tg Tutorial group to list
     * @param tutorialGroupPredicate Predicate used to filter for students in the tutorial group
     */
    public ListCommand(TutorialGroup tg, ContainsTutorialGroupPredicate tutorialGroupPredicate) {
        this.tg = tg;
        this.tutorialGroupPredicate = tutorialGroupPredicate;
    }
}
