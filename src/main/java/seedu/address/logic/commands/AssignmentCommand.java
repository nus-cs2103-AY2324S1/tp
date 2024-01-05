package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

/**
 * Represents an assignment command that is able to return the index of the person whose assignments are being used.
 */
public abstract class AssignmentCommand extends Command {

    /**
     * Returns the index used in the assignment related command.
     */
    public abstract Index getIndex();
}
