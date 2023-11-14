package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unfavourites a person identified using it's displayed index from the address book.
 */
public class UnfavouriteCommand extends Command {

    /**
     * Keyword to trigger the unfavourite command.
     */
    public static final String COMMAND_WORD = "unfav";

    /**
     * Usage instructions for the 'unfav' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unfavourites the person identified by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + " Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNFAVOURITE_PERSON_SUCCESS = "Unfavourited Person: %1$s";

    /**
     * The index of the person to be unfavourited.
     */
    private final Index targetIndex;

    /**
     * Constructor for the UnfavouriteCommand
     * @param targetIndex INdex of the person to be unfavourited.
     */
    public UnfavouriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the UnfavouriteCommand to remove the favourite status of a person in the address book.
     *
     * @param model The model this command should operate on.
     * @return The CommandResult displaying the success message after unfavouriting a person.
     * @throws CommandException If the index provided is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnfavourite = lastShownList.get(targetIndex.getZeroBased());
        model.unfavouritePerson(personToUnfavourite);
        model.setPerson(personToUnfavourite, personToUnfavourite);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNFAVOURITE_PERSON_SUCCESS,
                Messages.format(personToUnfavourite)));
    }

    /**
     * Checks equality of the UnfavouriteCommand with another object.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, otherwise returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnfavouriteCommand)) {
            return false;
        }

        UnfavouriteCommand otherUnfavouriteCommand = (UnfavouriteCommand) other;
        return targetIndex.equals(otherUnfavouriteCommand.targetIndex);
    }

    /**
     * Returns a string representation of the UnfavouriteCommand.
     *
     * @return A string representing the UnfavouriteCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
