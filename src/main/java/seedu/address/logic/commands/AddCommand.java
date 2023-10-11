package seedu.address.logic.commands;

import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import jdk.jshell.spi.ExecutionControl;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
}
