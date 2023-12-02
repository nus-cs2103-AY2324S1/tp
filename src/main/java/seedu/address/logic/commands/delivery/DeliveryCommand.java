package seedu.address.logic.commands.delivery;

import seedu.address.logic.commands.Command;

/**
 * Represents a delivery command with hidden internal logic and the ability to be executed.
 */
public abstract class DeliveryCommand extends Command {
    /**
     * Command Prefix for all Delivery Commands
     */
    public static final String COMMAND_WORD = "delivery";
}
