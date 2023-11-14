package seedu.address.logic.commands.mapping;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;

/**
 * Abstract class for MappingCommands.
 */
public abstract class MappingCommand extends Command {
    public static final String COMMAND_WORD = "mapping";

    protected MappingCommand() {
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
