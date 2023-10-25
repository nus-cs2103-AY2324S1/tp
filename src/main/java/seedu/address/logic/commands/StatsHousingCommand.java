package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Calculates the fosterers' housing statistics from the currently displayed list.
 */
public class StatsHousingCommand extends StatsCommand {
    public static final String COMMAND_WORD = "housing";
    public static final String MESSAGE_HOUSING_SUCCESS = "Out of %1$d listed fosterers,\n"
            + "- %2$d live in HDB (%3$.1f%%)\n"
            + "- %4$d live in Condo (%5$.1f%%)\n"
            + "- %6$d live in Landed (%7$.1f%%)\n"
            + "- %8$d unknown (%9$.1f%%)";

    /**
     * Returns the number of fosterers who stay in HDB.
     */
    public int getHdbCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing().equals(Housing.HDB))
                .count();
    }

    /**
     * Returns the number of fosterers who stay in Condos.
     */
    public int getCondoCount(List<Person> fosterers) {
        Housing condo = new Housing("Condo");
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing().equals(Housing.CONDO))
                .count();
    }

    /**
     * Returns the number of fosterers who stay in Landed.
     */
    public int getLandedCount(List<Person> fosterers) {
        Housing landed = new Housing("Landed");
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getHousing().equals(Housing.LANDED))
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

        float hdbPercent = calculatePercentage(hdbCount, total);
        float condoPercent = calculatePercentage(condoCount, total);
        float landedPercent = calculatePercentage(landedCount, total);
        float unknownPercent = calculatePercentage(unknownCount, total);

        String result = String.format(MESSAGE_HOUSING_SUCCESS, total, hdbCount, hdbPercent,
                condoCount, condoPercent, landedCount, landedPercent, unknownCount, unknownPercent);

        return new CommandResult(result);
    }
}
