package seedu.address.logic.commands;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

/**
 * Lists all interviews in the address book to the user. 
 * Essentially used to reset the filters applied to the interview
 * Does not affect the display of ApplicantList at all
 * (Can potentially be updated to display only the Applicants with Interviews if that is desired)
 * Adapted from AB3's ListCommand class
 */
public class ListInterviewCommand extends Command {

    public static final String COMMAND_WORD = "list-i";

    public static final String MESSAGE_SUCCESS = "Listed all interviews";
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);


    @Override
    public CommandResult execute(Model model) {
        logger.fine("Executing listing all interviews");
        requireNonNull(model);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
