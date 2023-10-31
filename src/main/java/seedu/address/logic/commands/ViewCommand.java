package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views the profile of the fosterer using their displayed index in the addressbook.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the profile of the user.\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String VIEWING_PROFILE_SUCCESS = "Viewing Person: %1$s";
    public static final String VIEWING_NEW_PROFILE_SUCCESS = "Viewing New Fosterer Profile";
    private final Index indexOfTheFostererToView;

    public ViewCommand(Index index) {
        this.indexOfTheFostererToView = index;
    }
    public ViewCommand() { this.indexOfTheFostererToView = null; }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Viewing empty profile for adding new fosterer
        if (indexOfTheFostererToView == null) {
            return new CommandResult(
                    VIEWING_NEW_PROFILE_SUCCESS,
                    CommandType.VIEW
            );
        }

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexOfTheFostererToView.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(indexOfTheFostererToView.getZeroBased());
        return new CommandResult(
                String.format(VIEWING_PROFILE_SUCCESS, Messages.format(personToView)),
                personToView,
                indexOfTheFostererToView,
                CommandType.VIEW,
                false
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return indexOfTheFostererToView.equals(e.indexOfTheFostererToView);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("indexOfTheFostererToView", indexOfTheFostererToView)
                .toString();
    }
}
