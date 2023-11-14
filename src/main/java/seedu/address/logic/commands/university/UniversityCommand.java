package seedu.address.logic.commands.university;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;

/**
 * Abstract class for UniversityCommands.
 */
public abstract class UniversityCommand extends Command {
    public static final String COMMAND_WORD = "university";

    protected UniversityCommand() {
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
