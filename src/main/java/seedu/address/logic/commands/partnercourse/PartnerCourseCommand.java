package seedu.address.logic.commands.partnercourse;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;

/**
 * Abstract class for PartnerCourseCommands.
 */
public abstract class PartnerCourseCommand extends Command {
    public static final String COMMAND_WORD = "partnercourse";
    protected PartnerCourseCommand() {
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
