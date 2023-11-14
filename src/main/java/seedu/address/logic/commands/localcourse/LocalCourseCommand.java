package seedu.address.logic.commands.localcourse;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;

/**
 * Abstract class for LocalCourseCommands.
 */
public abstract class LocalCourseCommand extends Command {

    public static final String COMMAND_WORD = "localcourse";
    protected LocalCourseCommand() {
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
