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
 * Favourites a person identified using it's displayed index from the address book.
 */
public class FavouriteCommand extends Command {

    /**
     * Keyword to trigger the favourite command.
     */
    public static final String COMMAND_WORD = "fav";

    /**
     * Usage instructions for the 'fav' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the person identified by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + " Example: " + COMMAND_WORD + " 1";

    /**
     * Message displayed upon successfully favouriting a person from the address book.
     */
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS = "Favourited Person: %1$s";

    /**
     * The index of the person who is favourited in the address book.
     */
    private final Index targetIndex;

    /**
     * Constructor for the FavouriteCommand.
     *
     * @param targetIndex The index of the person to be favorited in the address book.
     */
    public FavouriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the FavouriteCommand to favorite the person identified by the index in the address book.
     *
     * @param model The current model.
     * @return The CommandResult containing a message indicating the successful favoriting of the person.
     * @throws CommandException If the index is invalid or the person is already favorited.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavourite = lastShownList.get(targetIndex.getZeroBased());
        model.favouritePerson(personToFavourite);
        model.setPerson(personToFavourite, personToFavourite);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_PERSON_SUCCESS,
                Messages.format(personToFavourite)));
    }

    /**
     * Checks equality of the FavouriteCommand with another object.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FavouriteCommand)) {
            return false;
        }

        FavouriteCommand otherFavouriteCommand = (FavouriteCommand) other;
        return targetIndex.equals(otherFavouriteCommand.targetIndex);
    }

    /**
     * Returns a string representation of the FavouriteCommand.
     *
     * @return A string representing the FavouriteCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
