
package seedu.address.logic.commands.findcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.model.Model;
/**
 * Find a person or a company in the address book.
 */
public abstract class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_SUCCESS = "Success message for the find command!";

    public static final String PERSON_INTRO = "Parameters for Person: \n"
        + "p - find a person's contact \n"
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " p "
        + PREFIX_NAME + "john "
        + PREFIX_TAG + "friend \n";
    public static final String COMPANY_INTRO = "Parameters for Company \n"
        + "c - find a company's contact \n"
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " c "
        + PREFIX_NAME + "apple "
        + PREFIX_TAG + "tech ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds all persons or companies whose names contain any of "
        + "the specified optional names/tags (case-insensitive) and displays them as a list with index numbers.\n"
        + PERSON_INTRO + COMPANY_INTRO;

    @Override
    public abstract CommandResult execute(Model model);

}
