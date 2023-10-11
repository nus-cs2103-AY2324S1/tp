package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTCOME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Interaction;


/**
 * Represents a command that creates an interaction with the client.
 */
public class InteractionCommand extends Command {
    public static final String COMMAND_WORD = "interaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an interaction with the client "
            + "specified by clientID and a note and a outcome.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_OUTCOME + "OUTCOME] "
            + "[" + "NOTE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OUTCOME + "INTERESTED "
            + "I am interested in this product";

    public static final String MESSAGE_INTERACTION_SUCCESS = "Created client interaction";
    public static final String MESSAGE_NOT_ADDED = "At least one field (outcome or note) must be provided.";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "This command is not implemented yet";

    private final Index index;
    private final Interaction interaction;

    /**
     * Creates an InteractionCommand to add the specified {@code Interaction}
     */
    public InteractionCommand(Index index, Interaction interaction) {
        requireNonNull(index);

        this.index = index;
        this.interaction = interaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
