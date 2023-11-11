package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Person;

/**
 * Calculates the statistics of available fosterers from the currently displayed list.
 */
public class StatsAvailCommand extends StatsCommand {
    public static final String COMMAND_WORD = "avail";
    public static final String MESSAGE_AVAIL_SUMMARY = "%1$d out of %2$d listed are available (%3$.2f%%)!";

    public static final String MESSAGE_AVAIL_DETAILS = "Out of those available, \n"
            + "- %1$d can foster dogs (%2$.2f%%)\n"
            + "- %3$d can foster cats (%4$.2f%%)\n"
            + "- %5$d unknown (%6$.2f%%)";

    /**
     * Returns a list of available fosterers.
     */
    protected List<Person> getAvailableFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(Person::isAvailableFosterer)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of fosterers from the given list who can foster dogs.
     */
    protected int getAbleDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.ABLE_DOG))
                .count();
    }

    /**
     * Returns the number of fosterers from the given list who can foster cats.
     */
    protected int getAbleCatCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.ABLE_CAT))
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

        List<Person> availableFosterers = getAvailableFosterers(lastShownList);
        int availableCount = availableFosterers.size();
        double availPercent = calculatePercentage(availableCount, total);
        String resultSummary = String.format(MESSAGE_AVAIL_SUMMARY, availableCount, total, availPercent);

        if (availableCount == 0) {
            return new CommandResult(resultSummary);
        }

        int canFosterDogsCount = getAbleDogCount(availableFosterers);
        int canFosterCatsCount = getAbleCatCount(availableFosterers);
        int unknownCount = availableCount - canFosterDogsCount - canFosterCatsCount;

        double canFosterDogsPercent = calculatePercentage(canFosterDogsCount, availableCount);
        double canFosterCatsPercent = calculatePercentage(canFosterCatsCount, availableCount);
        double unknownPercent = 100.0 - canFosterCatsPercent - canFosterDogsPercent;

        String resultDetails = String.format(MESSAGE_AVAIL_DETAILS, canFosterDogsCount, canFosterDogsPercent,
                canFosterCatsCount, canFosterCatsPercent, unknownCount, unknownPercent);

        return new CommandResult(resultSummary + "\n" + resultDetails);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatsAvailCommand)) {
            return false;
        }

        return true;
    }
}
