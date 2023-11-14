package seedu.address.logic.commands.note;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;

/**
 * Abstract class for NoteCommands.
 */
public abstract class NoteCommand extends Command {
    public static final String COMMAND_WORD = "note";

    protected NoteCommand() {
    }

    // Ensure that this method is overridden in all subclasses
    @Override
    public boolean equals(Object other) {
        return other == this;
    }

    // Ensure that this method is overridden in all subclasses
    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
