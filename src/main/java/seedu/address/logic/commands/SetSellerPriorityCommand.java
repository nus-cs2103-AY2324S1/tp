package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.seller.Seller;



/**
 * Sets a seller's priority based on displayed index in the address book.
 */
public class SetSellerPriorityCommand extends Command {

    public static final String COMMAND_WORD = "sprio";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a priority level for the seller, identified by index in the displayed seller list. "
            + "INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.\n"
            + "Parameters: "
            + "INDEX "
            + "PRIORITY\n"
            + "Example: " + COMMAND_WORD + " 1" + " high";

    public static final String MESSAGE_SUCCESS = "The seller's priority level has been set:\n%1$s";
    private final Index targetIndex;
    private final Priority priority;
    private final CommandWarnings commandWarnings;

    /**
     * Constructs a SetSellerPriorityCommand to set the priority level of a specified seller.
     *
     * @param targetIndex the index of the seller to set the priority of.
     * @param priority the priority that the seller is to be set to.
     */
    public SetSellerPriorityCommand(Index targetIndex, Priority priority) {
        this.targetIndex = targetIndex;
        this.priority = priority;
        this.commandWarnings = new CommandWarnings();
    }
    /**
     * Constructs a SetSellerPriorityCommand to set the priority level of a specified seller with
     * pre-existing warnings.
     *
     * @param targetIndex the index of the seller to set the priority of.
     * @param priority the priority that the seller is to be set to.
     * @param commandWarnings A container for warnings that may occur.
     */
    public SetSellerPriorityCommand(Index targetIndex, Priority priority, CommandWarnings commandWarnings) {
        this.targetIndex = targetIndex;
        this.priority = priority;
        this.commandWarnings = commandWarnings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller targetSeller = lastShownList.get(targetIndex.getZeroBased());
        Seller sellerWithPriority = getSellerWithPriority(targetSeller, this.priority);

        model.setSeller(targetSeller, sellerWithPriority);
        model.commitAddressBook();

        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(sellerWithPriority)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Seller getSellerWithPriority(Seller targetSeller, Priority priority) {
        assert targetSeller != null;

        return new Seller(
                targetSeller.getName(),
                targetSeller.getPhone(),
                targetSeller.getEmail(),
                targetSeller.getAddress(),
                targetSeller.getSellingAddress(),
                targetSeller.getHouseInfo(),
                targetSeller.getTags(),
                priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetSellerPriorityCommand)) {
            return false;
        }

        SetSellerPriorityCommand otherPriorityCommand = (SetSellerPriorityCommand) other;
        return targetIndex.equals(otherPriorityCommand.targetIndex)
                && priority.equals(otherPriorityCommand.priority)
                && commandWarnings.equals(otherPriorityCommand.commandWarnings);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("priority", priority)
                .add("warnings", commandWarnings)
                .toString();
    }
}
