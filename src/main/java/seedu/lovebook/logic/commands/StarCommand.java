package seedu.lovebook.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.lovebook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Star;


/**
 * Star a Date identified using it's displayed index from the LoveBook.
 */
public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": stars the date identified by the index number used in the displayed Date list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_STAR_PERSON_SUCCESS = "Starred Date: %1$s";

    private final Index targetIndex;

    public StarCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Date> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Date dateToStar = lastShownList.get(targetIndex.getZeroBased());
        if (dateToStar.getStar().isStarred.equals("true")) {
            throw new CommandException("Date has already been starred");
        }
        Star star = new Star("true");
        Date starredDate = new Date(dateToStar.getName(), dateToStar.getAge(), dateToStar.getGender(),
                dateToStar.getHeight(), dateToStar.getIncome(),
                dateToStar.getHoroscope(), star, dateToStar.getAvatar());
        model.setDate(dateToStar, starredDate);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_STAR_PERSON_SUCCESS, Messages.format(dateToStar)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarCommand)) {
            return false;
        }

        StarCommand otherStarCommand = (StarCommand) other;
        return targetIndex.equals(otherStarCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
