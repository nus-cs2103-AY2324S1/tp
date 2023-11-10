package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Person;

/**
 * Calculates the fosterers' housing statistics from the currently displayed list.
 */
public class StatsHousingCommand extends StatsCommand {
    public static final String COMMAND_WORD = "housing";
    public static final String MESSAGE_HOUSING_SUCCESS = "Out of %1$d listed,\n"
            + "- %2$d live in HDB (%3$.2f%%)\n"
            + "- %4$d live in Condo (%5$.2f%%)\n"
            + "- %6$d live in Landed (%7$.2f%%)\n"
            + "- %8$d unknown (%9$.2f%%)";

    /**
     * Returns the number of fosterers who stay in HDB.
     */
    protected int getHdbCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing()
                                .equals(Housing.HDB))
                .count();
    }

    /**
     * Returns the number of fosterers who stay in Condos.
     */
    protected int getCondoCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing()
                                .equals(Housing.CONDO))
                .count();
    }

    /**
     * Returns the number of fosterers who stay in Landed.
     */
    protected int getLandedCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing()
                                .equals(Housing.LANDED))
                .count();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int total = lastShownList.size();

        if (total == 0) {
            throw new CommandException(StatsCommand.MESSAGE_NO_FOSTERERS);
        }

        int hdbCount = getHdbCount(lastShownList);
        int condoCount = getCondoCount(lastShownList);
        int landedCount = getLandedCount(lastShownList);
        int unknownCount = total - hdbCount - condoCount - landedCount;

        double hdbPercent = calculatePercentage(hdbCount, total);
        double condoPercent = calculatePercentage(condoCount, total);
        double landedPercent = calculatePercentage(landedCount, total);
        double unknownPercent = 100.0 - hdbPercent - condoPercent - landedPercent;

        String result = String.format(MESSAGE_HOUSING_SUCCESS, total, hdbCount, hdbPercent,
                condoCount, condoPercent, landedCount, landedPercent, unknownCount, unknownPercent);

        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatsHousingCommand)) {
            return false;
        }

        return true;
    }
}
